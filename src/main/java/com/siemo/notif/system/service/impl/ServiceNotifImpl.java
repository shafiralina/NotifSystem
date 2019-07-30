package com.siemo.notif.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemo.notif.system.aspect.AuditTrail;
import com.siemo.notif.system.base.service.BaseBackendService;
import com.siemo.notif.system.base.util.service.RestUtil;
import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.ManageDataUserRequest;
import com.siemo.notif.system.message.ManageDataUserResponse;
import com.siemo.notif.system.message.ResponseCode;
import com.siemo.notif.system.message.BatchMessage;
import com.siemo.notif.system.message.BatchRecipients;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendAllRequest;
import com.siemo.notif.system.message.SendBatchRequest;
import com.siemo.notif.system.message.SendBatchResponse;
import com.siemo.notif.system.message.SendGroupRequest;
import com.siemo.notif.system.message.SendOneRequest;
import com.siemo.notif.system.message.SendResponse;
import com.siemo.notif.system.model.CredentialToken;
import com.siemo.notif.system.model.CredentialUser;
import com.siemo.notif.system.model.Group;
import com.siemo.notif.system.model.HistoryNotificationExecution;
import com.siemo.notif.system.model.HistoryNotificationExecution.action;
import com.siemo.notif.system.model.MasterData;
import com.siemo.notif.system.repository.RepositoryAuthToken;
import com.siemo.notif.system.repository.RepositoryAuthUser;
import com.siemo.notif.system.repository.RepositoryGroup;
import com.siemo.notif.system.repository.RepositoryHistory;
import com.siemo.notif.system.repository.RepositoryNotif;
import com.siemo.notif.system.service.ServiceNotif;
import com.siemo.notif.system.specification.SearchCriteria;
import com.siemo.notif.system.specification.UserSpecification;

@Service
@PropertySource(value = "classpath:/config/path.properties")
@PropertySource(value = "classpath:/config/batch.properties")
@Configuration
public class ServiceNotifImpl implements ServiceNotif {

	@Autowired
	@Qualifier("batchrest")
	private BaseBackendService batchrest;

	@Autowired
	Environment env;

	@Autowired
	private RestUtil restUtil;

	@Autowired
	private AuditTrail audit;

	@Autowired
	private RepositoryNotif repositoryNotif;

	@Autowired
	private RepositoryGroup repositoryGroup;

	@Autowired
	private RepositoryHistory repositoryHistory;

	@Autowired
	private RepositoryAuthToken repositoryAuthToken;

	@Autowired
	private RepositoryAuthUser repositoryAuthUser;

	// SAVE DATA TO DATABASE
	@Override
	public BaseResponse saveData(SaveRequest request) {
		Group idGroup = repositoryGroup.findByCategoryAndDetail(request.getCategory(), request.getDetail());
		Date time = new Date();

		// Save to MasterData
		MasterData masterData = new MasterData(request.getUserId(), request.getTokenDevice(), request.getChannel(),
				request.getStatus(), request.getVersi(), idGroup, time);
		masterData = repositoryNotif.save(masterData);

		// Save userId to Credential Token
		CredentialToken authToken = new CredentialToken();
		authToken.setUserId(request.getUserId());
		authToken = repositoryAuthToken.save(authToken);

		// Save userId, password and role to UserId
		CredentialUser authUser = new CredentialUser();
		authUser.setUserId(request.getUserId());
		authUser.setPassword("12345");
		authUser.setRole("USER");
		authUser = repositoryAuthUser.save(authUser);

		BaseResponse response = new BaseResponse();
		response.setRc(ResponseCode.VALID_RESPONSE);
		response.setMessage("Success");
		return response;
	}

	// GET ALL DATA FROM DATABASE
	@Override
	public GetAllDataResponse getAllData(GetDataRequest request) {
		GetAllDataResponse response = new GetAllDataResponse();

		if (audit.getToken()) { // interceptor
			List<MasterData> listData = (List<MasterData>) repositoryNotif.findAll();
			response.setRc(ResponseCode.VALID_RESPONSE);
			response.setMessage("Success");
			response.setListData(listData);
		} else {
			response.setRc(ResponseCode.WRONG_TOKEN);
			response.setMessage("Wrong Token");
		}
		return response;
	}

	// GET ONE DATA FROM DATABASE
	@Override
	public GetAllDataResponse getData(GetDataRequest request) {
		GetAllDataResponse response = new GetAllDataResponse();

		if (audit.getToken()) { // interceptor
			List<MasterData> listData = repositoryNotif.findByUserId(request.getUserId());
			response.setRc(ResponseCode.VALID_RESPONSE);
			response.setMessage("Success");
			response.setListData(listData);
		} else {
			response.setRc(ResponseCode.WRONG_TOKEN);
			response.setMessage("Wrong Token");
		}
		return response;
	}

	// UPDATE DATA IN DATABASE
	@Override
	public ManageDataUserResponse manageDataUser(ManageDataUserRequest request) {
		ManageDataUserResponse response = new ManageDataUserResponse();

		if (audit.getToken()) { // interceptor
			Date date = new Date();
			MasterData data = repositoryNotif.findAllById(request.getMasterDataId());
			data.setStatus(request.getStatus());
			data.setUpdateDated(date);
			data = repositoryNotif.save(data);
			response.setRc(ResponseCode.VALID_RESPONSE);
			response.setMessage("Success");
		} else {
			response.setRc(ResponseCode.WRONG_TOKEN);
			response.setMessage("Wrong Token");
		}
		return response;
	}

	// SEND ONE CUSTOMER NOTIFICATION
	@Override
	public SendResponse sendOne(SendOneRequest request) {
		SendResponse response = new SendResponse();

		if (audit.getToken()==true) { // interceptor
			String uri = env.getProperty("batch.send.notification");
			String inqUri = restUtil.generateURI(uri);

			SendBatchRequest inqRequest = new SendBatchRequest();
			BatchMessage body = new BatchMessage();
			body.setBody(request.getBody());

			ArrayList<String> listToken = new ArrayList<>();
			ArrayList<MasterData> listData = repositoryNotif.findTokensAndStatusByUserId(request.getUserId());
			for (int i = 0; i < listData.size(); i++) {
				MasterData data = listData.get(i);
				if (data.getStatus().equals("ACTIVE")) {
					listToken.add(data.getTokenDevice());
				}
			}
			BatchRecipients data = new BatchRecipients();
			data.setTokens(listToken);

			String groupID = env.getProperty("batch.group.id");
			String custom = env.getProperty("batch.custom.payload");
			String deeplink = env.getProperty("batch.deeplink");
			String pushtime = env.getProperty("batch.push.time");
			String sandbox = env.getProperty("batch.sandbox");
			boolean sand = Boolean.valueOf(sandbox);

			inqRequest.setRecipients(data);
			inqRequest.setGroup_id(groupID);
			inqRequest.setCustom_payload(custom);
			inqRequest.setDeeplink(deeplink);
			inqRequest.setMessage(body);
			inqRequest.setPush_time(pushtime);
			inqRequest.setSandbox(sand);

			ResponseEntity<SendBatchResponse> inqBatch_response = batchrest.postForEntity(inqUri, inqRequest,
					SendBatchResponse.class);
			HttpStatus inqHttpStatus = inqBatch_response.getStatusCode();
			SendBatchResponse bodyBatchResponse = inqBatch_response.getBody();
			response.setRc(ResponseCode.VALID_RESPONSE);
			response.setMessage("Success");
			response.setToken(bodyBatchResponse.getToken());

			// Prepare for HistoryNotificationExecution storage
			List<String> listId = new ArrayList<>();
			List<MasterData> listAllMasterDataId = repositoryNotif.findMasterDataIdByUserId(request.getUserId());
			for (int i = 0; i < listAllMasterDataId.size(); i++) {
				MasterData MDId = listAllMasterDataId.get(i);
				if (MDId.getStatus().equals("ACTIVE")) {
					listId.add(MDId.getId());
				}
			}

			String listMasterDataId = null;
			listMasterDataId = listId.toString();

			ObjectMapper mapper = new ObjectMapper();
			String reqHistory = null;
			try {
				reqHistory = mapper.writeValueAsString(inqRequest);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			String resHistory = null;
			try {
				resHistory = mapper.writeValueAsString(bodyBatchResponse);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			Date date = new Date();
			HistoryNotificationExecution history = new HistoryNotificationExecution(action.SEND_ONE.toString(), date,
					reqHistory, resHistory, listMasterDataId, response.getRc());
			history = repositoryHistory.save(history);
		} else {
			response.setRc(ResponseCode.WRONG_TOKEN);
			response.setMessage("Wrong Token");
		}
		return response;
	}

	// SNED ALL CUSTOMER NOTIFICATION
	@Override
	public SendResponse sendAll(SendAllRequest request) {
		SendResponse response = new SendResponse();

		if (audit.getToken()==true) { // interceptor
			String uri = env.getProperty("batch.send.notification");
			String inqUri = restUtil.generateURI(uri);

			SendBatchRequest inqRequest = new SendBatchRequest();
			BatchMessage body = new BatchMessage();
			body.setBody(request.getBody());

			ArrayList<String> listToken = new ArrayList<>();
			ArrayList<MasterData> listData = (ArrayList<MasterData>) repositoryNotif.findAll();
			for (int i = 0; i < listData.size(); i++) {
				MasterData data = listData.get(i);
				if (data.getStatus().equals("ACTIVE")) {
					listToken.add(data.getTokenDevice());
				}
			}
			BatchRecipients data = new BatchRecipients();
			data.setTokens(listToken);

			String groupID = env.getProperty("batch.group.id");
			String custom = env.getProperty("batch.custom.payload");
			String deeplink = env.getProperty("batch.deeplink");
			String pushtime = env.getProperty("batch.push.time");
			String sandbox = env.getProperty("batch.sandbox");
			boolean sand = Boolean.valueOf(sandbox);

			inqRequest.setRecipients(data);
			inqRequest.setGroup_id(groupID);
			inqRequest.setCustom_payload(custom);
			inqRequest.setDeeplink(deeplink);
			inqRequest.setMessage(body);
			inqRequest.setPush_time(pushtime);
			inqRequest.setSandbox(sand);

			ResponseEntity<SendBatchResponse> inqBatch_response = batchrest.postForEntity(inqUri, inqRequest,
					SendBatchResponse.class);
			HttpStatus inqHttpStatus = inqBatch_response.getStatusCode();
			SendBatchResponse bodyBatchResponse = inqBatch_response.getBody();
			response.setRc(ResponseCode.VALID_RESPONSE);
			response.setMessage("Success");
			response.setToken(bodyBatchResponse.getToken());

			// Prepare for HistoryNotificationExecution storage
			ObjectMapper mapper = new ObjectMapper();
			String reqHistory = null;
			try {
				reqHistory = mapper.writeValueAsString(inqRequest);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			String resHistory = null;
			try {
				resHistory = mapper.writeValueAsString(bodyBatchResponse);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			List<String> listId = new ArrayList<>();
			List<MasterData> listDataMaster = (List<MasterData>) repositoryNotif.findAll();
			for (int i = 0; i < listData.size(); i++) {
				MasterData dataMaster = listData.get(i);
				if (dataMaster.getStatus().equals("ACTIVE")) {
					listId.add(dataMaster.getId());
				}
			}
			String listMasterDataId = null;
			listMasterDataId = listId.toString();

			Date date = new Date();
			HistoryNotificationExecution history = new HistoryNotificationExecution(action.SEND_ALL.toString(), date,
					reqHistory, resHistory, listMasterDataId, response.getRc());
			history = repositoryHistory.save(history);
		} else {
			response.setRc(ResponseCode.WRONG_TOKEN);
			response.setMessage("Wrong Token");
		}
		return response;
	}

	// SEND GROUP CUSTOMER NOTIFICATION
	@Override
	public SendResponse sendGroup(SendGroupRequest request) {
		SendResponse response = new SendResponse();

		if (audit.getToken()) { // interceptor
			String uri = env.getProperty("batch.send.notification");
			String inqUri = restUtil.generateURI(uri);

			SendBatchRequest inqRequest = new SendBatchRequest();
			BatchMessage body = new BatchMessage();
			body.setBody(request.getBody());

			String category = null;
			String detail = null;
			// specification or criteria
			if (request.getGroup().containsKey("category") == false
					&& request.getGroup().containsKey("detail") == true) {
				category = "";
				detail = request.getGroup().get("detail").toString();
			} else if (request.getGroup().containsKey("category") == true
					&& request.getGroup().containsKey("detail") == false) {
				category = request.getGroup().get("category").toString();
				detail = "";
			} else if (request.getGroup().containsKey("category") == false
					&& request.getGroup().containsKey("detail") == false) {
				category = "";
				detail = "";
			} else {
				category = request.getGroup().get("category").toString();
				detail = request.getGroup().get("detail").toString();
			}

			UserSpecification spec1 = new UserSpecification(new SearchCriteria("category", ":", category));
			UserSpecification spec2 = new UserSpecification(new SearchCriteria("detail", ":", detail));

			List<Group> results = repositoryGroup.findAll(Specification.where(spec1).and(spec2));

			List<MasterData> listId = null;
			List<MasterData> listCollect = new ArrayList<MasterData>();
			for (int i = 0; i < results.size(); i++) {
				int id = results.get(i).getId();
				listId = repositoryNotif.findTokensByGroupId(id);
				listCollect.addAll(listId);
			}

			ArrayList<String> listToken = new ArrayList<>();
			for (int i = 0; i < listCollect.size(); i++) {
				MasterData data = listCollect.get(i);
				if (data.getStatus().equals("ACTIVE")) {
					listToken.add(listCollect.get(i).getTokenDevice());
				}
			}
			BatchRecipients data = new BatchRecipients();
			data.setTokens(listToken);

			String groupID = env.getProperty("batch.group.id");
			String custom = env.getProperty("batch.custom.payload");
			String deeplink = env.getProperty("batch.deeplink");
			String pushtime = env.getProperty("batch.push.time");
			String sandbox = env.getProperty("batch.sandbox");
			boolean sand = Boolean.valueOf(sandbox);

			inqRequest.setRecipients(data);
			inqRequest.setGroup_id(groupID);
			inqRequest.setCustom_payload(custom);
			inqRequest.setDeeplink(deeplink);
			inqRequest.setMessage(body);
			inqRequest.setPush_time(pushtime);
			inqRequest.setSandbox(sand);

			ResponseEntity<SendBatchResponse> inqBatch_response = batchrest.postForEntity(inqUri, inqRequest,
					SendBatchResponse.class);
			HttpStatus inqHttpStatus = inqBatch_response.getStatusCode();
			SendBatchResponse bodyBatchResponse = inqBatch_response.getBody();
			response.setRc(ResponseCode.VALID_RESPONSE);
			response.setMessage("Success");
			response.setToken(bodyBatchResponse.getToken());

			// Prepare for HistoryNotificationExecution storage
			ObjectMapper mapper = new ObjectMapper();
			String reqHistory = null;
			try {
				reqHistory = mapper.writeValueAsString(inqRequest);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			String resHistory = null;
			try {
				resHistory = mapper.writeValueAsString(bodyBatchResponse);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			List<MasterData> listMDId = null;
			List<MasterData> listCollectMDId = new ArrayList<MasterData>();
			for (int i = 0; i < results.size(); i++) {
				int Id = results.get(i).getId();
				listMDId = repositoryNotif.findMasterDataIdByGroupId(Id);
				listCollectMDId.addAll(listMDId);
			}

			List<String> listMasterDataId = new ArrayList<>();
			for (int i = 0; i < listCollect.size(); i++) {
				MasterData dataMD = listCollect.get(i);
				if (dataMD.getStatus().equals("ACTIVE")) {
					listMasterDataId.add(listCollectMDId.get(i).getId());
				}
			}
			Date date = new Date();
			HistoryNotificationExecution history = new HistoryNotificationExecution(action.SEND_GROUP.toString(), date,
					reqHistory, resHistory, listMasterDataId.toString(), response.getRc());
			history = repositoryHistory.save(history);
		} else {
			response.setRc(ResponseCode.WRONG_TOKEN);
			response.setMessage("Wrong Token");
		}

		return response;
	}
}

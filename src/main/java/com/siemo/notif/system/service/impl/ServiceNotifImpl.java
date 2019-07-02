package com.siemo.notif.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;

import com.siemo.notif.system.base.service.BaseBackendService;
import com.siemo.notif.system.base.util.service.RestUtil;
import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.Message;
import com.siemo.notif.system.message.Recipients;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendAllRequest;
import com.siemo.notif.system.message.SendBatchRequest;
import com.siemo.notif.system.message.SendBatchResponse;
import com.siemo.notif.system.message.SendGroupRequest;
import com.siemo.notif.system.message.SendOneRequest;
import com.siemo.notif.system.message.SpecRequest;
import com.siemo.notif.system.message.SpecResponse;
import com.siemo.notif.system.message.Tokens;
import com.siemo.notif.system.model.MasterData;
import com.siemo.notif.system.repository.BaseCriteriaService;
import com.siemo.notif.system.repository.RepositoryNotif;
import com.siemo.notif.system.service.ServiceNotif;
import com.siemo.notif.system.specification.SearchCriteria;
import com.siemo.notif.system.specification.UserSpecification;

import javassist.expr.NewExpr;

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
	private RepositoryNotif repositoryNotif;
	
	@Autowired
	@Qualifier("criteria")
	private BaseCriteriaService criteria;

	@Override
	public BaseResponse saveData(SaveRequest request) {
		MasterData masterData = new MasterData();
		masterData = repositoryNotif.save(masterData);
		BaseResponse response = new BaseResponse();
		response.setMessage("simpan");
		response.setStatus("berhasil");
		return response;
	}

	@Override
	public GetAllDataResponse getAllData() {
		GetAllDataResponse response = new GetAllDataResponse();
		List<MasterData> listData = (List<MasterData>) repositoryNotif.findAll();
		response.setListData(listData);
		return response;
	}

	@Override
	public GetAllDataResponse getData(GetDataRequest request) {
		GetAllDataResponse response = new GetAllDataResponse();
		List<MasterData> listData = repositoryNotif.findByUserId(request.getUserId());
		response.setListData(listData);
		return response;
	}

	@Override
	public BaseResponse sendOne(SendOneRequest request) {
		BaseResponse response = new BaseResponse();
		String uri = env.getProperty("batch.send.one.notification");
		String inqUri = restUtil.generateURI(uri);
		
		SendBatchRequest inqRequest = new SendBatchRequest();
		Message body = new Message();
		body.setBody(request.getBody());

		ArrayList<String> listToken = new ArrayList<>();
		ArrayList<MasterData> listData = repositoryNotif.findTokensByUserId(request.getUserId());
		for (int i = 0; i < listData.size(); i++) {
			MasterData data = listData.get(i);
			listToken.add(data.getTokenDevice());
		}
		Recipients data = new Recipients();
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
		
		ResponseEntity<SendBatchResponse> inqOmni_response = batchrest.postForEntity(inqUri, inqRequest,
				SendBatchResponse.class);
		HttpStatus inqHttpStatus = inqOmni_response.getStatusCode();
		SendBatchResponse bodyOmniResponse = inqOmni_response.getBody();
		response.setMessage(bodyOmniResponse.getMessage());
		response.setStatus(bodyOmniResponse.getStatus());
		return response;
	}

	@Override
	public BaseResponse sendGroup(SendGroupRequest request) {

		return null;
	}

	@Override
	public BaseResponse sendAll(SendAllRequest request) {
		BaseResponse response = new BaseResponse();
		String uri = env.getProperty("batch.send.all.notification");
		String inqUri = restUtil.generateURI(uri);
		
		SendBatchRequest inqRequest = new SendBatchRequest();
		Message body = new Message();
		body.setBody(request.getBody());

		ArrayList<String> listToken = new ArrayList<>();
		ArrayList<MasterData> listData = (ArrayList<MasterData>) repositoryNotif.findAll();
		for (int i = 0; i < listData.size(); i++) {
			MasterData data = listData.get(i);
			listToken.add(data.getTokenDevice());
		}
		Recipients data = new Recipients();
		data.setTokens(listToken);
		
		inqRequest.setRecipients(data);
		inqRequest.setGroup_id("Mb01234567");
		inqRequest.setCustom_payload("{}");
		inqRequest.setDeeplink("www.com");
		inqRequest.setMessage(body);
		inqRequest.setPush_time("now");
		inqRequest.setSandbox(false);
		
		ResponseEntity<SendBatchResponse> inqOmni_response = batchrest.postForEntity(inqUri, inqRequest,
				SendBatchResponse.class);
		HttpStatus inqHttpStatus = inqOmni_response.getStatusCode();
		SendBatchResponse bodyOmniResponse = inqOmni_response.getBody();
		response.setMessage(bodyOmniResponse.getMessage());
		response.setStatus(bodyOmniResponse.getStatus());
		return response;
	}
	
	
	@Override
	public SpecResponse spec(SpecRequest request) {
		HashMap<String, Object> map = new HashMap<>();
        map.put("channel", "baru");
        map.put("systemOperasi", "ios");
        
        SpecResponse response = new SpecResponse();
       
        
//        List<MasterData> a = repositoryNotif.findSearchHistoryRib(map);
//        response.setChannel(a);
//        response.setSystemOperasi(a);
		return response;
	}

}

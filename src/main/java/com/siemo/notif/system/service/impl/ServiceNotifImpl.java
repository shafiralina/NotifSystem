package com.siemo.notif.system.service.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendGroupRequest;
import com.siemo.notif.system.message.SendRequest;
import com.siemo.notif.system.model.MasterData;
import com.siemo.notif.system.repository.RepositoryNotif;
import com.siemo.notif.system.service.ServiceNotif;
import com.siemo.notif.system.util.service.BackendService;

@Service("ServiceNotif")
public class ServiceNotifImpl implements ServiceNotif {

//	@Value("#{restprop['notif.url']}")
//	private String url;
//
//	@Value("#{restprop['notif.uri']}")
//	private String uri;

	@Autowired
	private RepositoryNotif repositoryNotif;
	
	@Autowired
	private BackendService backendService;

	@Override
	public Object saveData(SaveRequest request) throws IOException {
		MasterData masterData = new MasterData(request.getUserId(), request.getTokenDevice(), request.getChannel(),
				request.getSystemOperasi());
		masterData = repositoryNotif.save(masterData);
//		BaseResponse response = new BaseResponse();
//		response.setMessage("simpan");
//		response.setStatus("berhasil");
//		return response;

		String url = "http://localhost:8002";
		String uri = "/response/savedata";
		PostMethod post = new PostMethod(url + uri);
		// panggil object
		HttpClient client = new HttpClient();
		Object messageSend = backendService.executeCallSilotFeedbackSave(client, post, request);
		return messageSend;
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
	public BaseResponse sendOne(SendRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse sendGroup(SendGroupRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse sendAll() {
		// TODO Auto-generated method stub
		return null;
	}

//	private Object executeCallSilotFeedbackSave(HttpClient client, PostMethod post, SaveRequest request)
//			throws IOException {
//		String response = null;
//		ObjectMapper mapper = new ObjectMapper();
//
//		// TODO: Make String Json to pretty
//		String jsonRequest = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
//
//		System.out.println("==================== REQUEST FEEDBACK ====================");
//		System.out.println(jsonRequest);
//		System.out.println("==========================================================");
//
//		StringRequestEntity entity = new StringRequestEntity(jsonRequest, "application/json", null);
//		post.setRequestEntity(entity);
//		BaseResponse modelResponse = new BaseResponse();
//
//		try {
//			client.executeMethod(post);
//			int statusCodeResponse = post.getStatusCode();
//			// JIKA HTTP 200
//			if (statusCodeResponse == HttpStatus.OK.value()) {
//				BaseResponse modelResponseSuccess = new BaseResponse();
//
//				response = post.getResponseBodyAsString();
//				modelResponseSuccess = mapper.readValue(response, BaseResponse.class);
//
//				return modelResponseSuccess;
//
//			} else {
//				// TODO SET ERROR HTTP
//				return null;
//			}
//
//		} catch (SocketTimeoutException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		return modelResponse;
//
//	}

}

package com.siemo.notif.system.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendAllRequest;
import com.siemo.notif.system.message.SendGroupRequest;
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
	public BaseResponse saveData(SaveRequest request) {
		MasterData masterData = new MasterData(request.getUserId(), request.getTokenDevice(), request.getChannel(),
				request.getSystemOperasi());
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
	public String sendOne(String request) {
		String messageSend = backendService.HttpClientGet("http://localhost:8002/response/send/one");
		return messageSend;
	}

	@Override
	public BaseResponse sendGroup(SendGroupRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object sendAll(SendAllRequest request) throws IOException {
		String url = "http://localhost:8002";
		String uri = "/response/send/all";
		PostMethod post = new PostMethod(url + uri);
		HttpClient client = new HttpClient();
		Object messageSend = backendService.HttpClientPost(client, post, request);
		return messageSend;
	}

}

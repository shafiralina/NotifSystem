package com.siemo.notif.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendGroupRequest;
import com.siemo.notif.system.message.SendRequest;
import com.siemo.notif.system.model.MasterData;
import com.siemo.notif.system.repository.RepositoryNotif;
import com.siemo.notif.system.service.ServiceNotif;


@Service
public class ServiceNotifImpl implements ServiceNotif {

	@Autowired
	private RepositoryNotif repositoryNotif;
	
	@Override
	public BaseResponse saveData(SaveRequest request) {
		MasterData masterData = new MasterData(request.getUserId(), request.getTokenDevice(), request.getChannel(), request.getSystemOperasi());
		masterData = repositoryNotif.save(masterData);	
		BaseResponse response = new BaseResponse();
		response.setMessage("simpan");
		response.setStatus("berhasil");
		return  response;
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


}

package com.siemo.notif.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.model.MasterData;
import com.siemo.notif.system.repository.RepositoryNotif;
import com.siemo.notif.system.service.ServiceNotif;


@Service
public class ServiceNotifImpl implements ServiceNotif {

	@Autowired
	private RepositoryNotif repositoryNotif;
	
	@Override
	public String saveData(SaveRequest request) {
		MasterData masterData = new MasterData(request.getUserId(), request.getTokenDevice(), request.getChannel(), request.getSystemOperasi());
		masterData = repositoryNotif.save(masterData);	
		return "ok";
	}

	@Override
	public GetAllDataResponse getAllData() {
		GetAllDataResponse getAllData = new GetAllDataResponse();
		List<MasterData> listData = (List<MasterData>) repositoryNotif.findAll();
		getAllData.setListData(listData);
		return getAllData;
	}

	@Override
	public GetAllDataResponse getData(GetDataRequest request) {
		GetAllDataResponse getData = new GetAllDataResponse();
		List<MasterData> listData = repositoryNotif.findByUserId(request.getUserId());
		getData.setListData(listData);
		return getData;
	}


}

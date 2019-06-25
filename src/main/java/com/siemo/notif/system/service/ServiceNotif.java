package com.siemo.notif.system.service;


import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;

public interface ServiceNotif {
	public String saveData(SaveRequest request);
	public GetAllDataResponse getAllData();
	public GetAllDataResponse getData(GetDataRequest request);

}

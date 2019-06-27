package com.siemo.notif.system.service;

import java.io.IOException;

import com.siemo.notif.system.message.BaseResponse;
//import com.siemo.notif.system.message.DataPegawaiRequest;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendGroupRequest;
import com.siemo.notif.system.message.SendRequest;

public interface ServiceNotif {
	public Object saveData(SaveRequest request) throws IOException;
	public GetAllDataResponse getAllData();
	public GetAllDataResponse getData(GetDataRequest request);
	public BaseResponse sendOne(SendRequest request);
	public BaseResponse sendGroup(SendGroupRequest request);
	public BaseResponse sendAll();
	
	
}

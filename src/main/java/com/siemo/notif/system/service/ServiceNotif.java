package com.siemo.notif.system.service;

import java.io.IOException;

import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendAllRequest;
import com.siemo.notif.system.message.SendGroupRequest;
//import com.siemo.notif.system.message.SendOneRequest;

public interface ServiceNotif {
	public BaseResponse saveData(SaveRequest request);
	public GetAllDataResponse getAllData();
	public GetAllDataResponse getData(GetDataRequest request);
	public String sendOne(String request);
	public BaseResponse sendGroup(SendGroupRequest request);
	public Object sendAll(SendAllRequest request) throws IOException;
	
	
//	public Object saveData(SaveRequest request) throws IOException;
//	public String getSaja(String data);
}

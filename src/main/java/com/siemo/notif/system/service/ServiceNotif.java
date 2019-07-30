package com.siemo.notif.system.service;

import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.CredentialUserResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.ManageDataUserRequest;
import com.siemo.notif.system.message.ManageDataUserResponse;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendAllRequest;
import com.siemo.notif.system.message.SendGroupRequest;
import com.siemo.notif.system.message.SendOneRequest;
import com.siemo.notif.system.message.SendResponse;

public interface ServiceNotif {
	public BaseResponse saveData(SaveRequest request);

	public GetAllDataResponse getAllData(GetDataRequest request);

	public GetAllDataResponse getData(GetDataRequest request);
	
	public ManageDataUserResponse manageDataUser(ManageDataUserRequest request);

	public SendResponse sendOne(SendOneRequest request);

	public SendResponse sendAll(SendAllRequest request);
	
	public SendResponse sendGroup(SendGroupRequest request);
	
}

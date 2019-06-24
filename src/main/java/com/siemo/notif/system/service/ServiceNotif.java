package com.siemo.notif.system.service;

import com.siemo.notif.system.message.SendReq;

public interface ServiceNotif {
	public String oneSave(SendReq request);
	public String oneUpdate(SendReq request);
	public String oneCreate(SendReq request);
	public String allSave(SendReq request);
	public String allUpdate(SendReq request);
	public String allCreate(SendReq request);
	public String groupSave(SendReq request);
	public String groupUpdate(SendReq request);
	public String groupCreate(SendReq request);

}

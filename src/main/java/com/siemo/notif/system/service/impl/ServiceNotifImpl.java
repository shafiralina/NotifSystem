package com.siemo.notif.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.siemo.notif.system.message.SendReq;
import com.siemo.notif.system.repository.RepositoryNotif;
import com.siemo.notif.system.service.ServiceNotif;

public class ServiceNotifImpl implements ServiceNotif {

	@Autowired
	private RepositoryNotif repositoryNotif;
	
	@Override
	public String oneSave(SendReq request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String oneUpdate(SendReq request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String oneCreate(SendReq request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String allSave(SendReq request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String allUpdate(SendReq request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String allCreate(SendReq request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String groupSave(SendReq request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String groupUpdate(SendReq request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String groupCreate(SendReq request) {
		// TODO Auto-generated method stub
		return null;
	}

}

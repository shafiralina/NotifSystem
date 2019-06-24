package com.siemo.notif.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siemo.notif.system.repository.RepositoryNotif;
import com.siemo.notif.system.service.ServiceNotif;

@RestController

public class ControllerNotif {
	@Autowired
	private RepositoryNotif repositoryNotif;
	
	private ServiceNotif serviceNotif;
	private String apagitu;
	
}

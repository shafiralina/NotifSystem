package com.siemo.notif.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.service.ServiceNotif;

@RestController
@RequestMapping(value="/api/notifsystem")
public class ControllerNotif {
	
	@Autowired
	private ServiceNotif serviceNotif;
	
	
	@PostMapping("/save/device")
	public String simpan(@RequestBody SaveRequest request){
		
		String response = serviceNotif.saveData(request);
		return response;
	}
	
	@GetMapping("get/all/data")
	public GetAllDataResponse getAllData() {
		GetAllDataResponse response = serviceNotif.getAllData();
		return response;
	}
	
	@PostMapping("get/data")
	public GetAllDataResponse getData(@RequestBody GetDataRequest request) {
		GetAllDataResponse response = serviceNotif.getData(request);
		return response;
		}
	}

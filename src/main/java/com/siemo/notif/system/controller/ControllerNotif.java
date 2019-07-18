package com.siemo.notif.system.controller;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.CredentialUserResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.ManageDataUserRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendAllRequest;
import com.siemo.notif.system.message.SendGroupRequest;
import com.siemo.notif.system.message.SendOneRequest;
import com.siemo.notif.system.service.ServiceNotif;

@RestController
//@RequestMapping(value = "api/notifsystem")
public class ControllerNotif {

	@Autowired
	private ServiceNotif serviceNotif;
	
	
	@PostMapping("/api/notifsystem/save/device")
	public BaseResponse saveData(@RequestBody SaveRequest request) {
		BaseResponse response = serviceNotif.saveData(request);
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
	
	@RequestMapping("update/device")
	public BaseResponse manageDataUser(@RequestBody ManageDataUserRequest request) {
		BaseResponse response = serviceNotif.manageDataUser(request);
		return response;
	}
	
	@RequestMapping("send/one/customer")
	public BaseResponse sendOne(ServletRequest requestz, HttpServletRequest requests, @RequestBody SendOneRequest request) {
		BaseResponse response = serviceNotif.sendOne(request);
		HttpServletRequest httpRequest = (HttpServletRequest) requestz;
		String headerNames = httpRequest.getHeader("Authorization");
        System.out.println("AUTHORI = "+headerNames);
		
//		Enumeration headerNames = requests.getHeaders("Authorization");
//            String key = (String) headerNames.nextElement();
//            System.out.println(key);
		return response;
	}

	@RequestMapping("send/group/customer")
	public BaseResponse sendGroup(@RequestBody SendGroupRequest request) {
		BaseResponse response = serviceNotif.sendGroup(request);
		return response;
	}

	@RequestMapping("send/all/customer")
	public BaseResponse sendAll(@RequestBody SendAllRequest request) {
		BaseResponse response = serviceNotif.sendAll(request);
		return response;
	}

}

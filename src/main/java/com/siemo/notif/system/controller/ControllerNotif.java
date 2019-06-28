package com.siemo.notif.system.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendAllRequest;
import com.siemo.notif.system.message.SendGroupRequest;
import com.siemo.notif.system.service.ServiceNotif;

@RestController
@RequestMapping(value = "/api/notifsystem")
public class ControllerNotif {

	@Autowired
	private ServiceNotif serviceNotif;

	@PostMapping("/save/data")
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

	@GetMapping("/send/one")
	public String sendOne(HttpServletRequest request) {
		String response = serviceNotif.sendOne("Berhasil");
		return response;
	}

	@PostMapping("send/group/customer")
	public BaseResponse sendGroup(@RequestBody SendGroupRequest request) {
		BaseResponse response = serviceNotif.sendGroup(request);
		return response;
	}

	@RequestMapping(value = { "/send/all" }, method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse sendAll(HttpServletRequest request, @RequestBody @Valid SendAllRequest objectRequest)
			throws IllegalAccessException, InvocationTargetException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(objectRequest);

		Object responses = serviceNotif.sendAll(objectRequest);
		BaseResponse response = new BaseResponse();
		BeanUtils.copyProperties(responses, response);

		return response;

	}

//	@PostMapping("send/all/customer")
//	public BaseResponse sendAll() {
//		BaseResponse response = serviceNotif.sendAll();
//		return response;
//	}


}

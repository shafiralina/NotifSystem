package com.siemo.notif.system.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.DataPegawaiRequest;
import com.siemo.notif.system.message.DataPegawaiResponse;
import com.siemo.notif.system.message.GetAllDataResponse;
import com.siemo.notif.system.message.GetDataRequest;
import com.siemo.notif.system.message.SaveRequest;
import com.siemo.notif.system.message.SendGroupRequest;
import com.siemo.notif.system.message.SendRequest;
import com.siemo.notif.system.service.ServiceNotif;

@RestController
//@RequestMapping(value = "/api/notifsystem")
public class ControllerNotif {

	@Autowired
	private ServiceNotif serviceNotif;

	@PostMapping("/save/device")
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

	@PostMapping("send/one/customer")
	public BaseResponse sendOne(@RequestBody SendRequest request) {
		BaseResponse response = serviceNotif.sendOne(request);
		return response;
	}

	@PostMapping("send/group/customer")
	public BaseResponse sendGroup(@RequestBody SendGroupRequest request) {
		BaseResponse response = serviceNotif.sendGroup(request);
		return response;
	}

	@PostMapping("send/all/customer")
	public BaseResponse sendAll() {
		BaseResponse response = serviceNotif.sendAll();
		return response;
	}

//	@PostMapping("response/magang")
//	@ResponseBody
//	public DataPegawaiResponse data(@RequestBody DataPegawaiRequest request) {
//		DataPegawaiResponse response = serviceNotif.data(request);
//		return response;
//	}

	@RequestMapping(value = { "/response/magang" }, method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataPegawaiResponse simpanData(HttpServletRequest request,
			@RequestBody @Valid DataPegawaiRequest objectRequest)
			throws IllegalAccessException, InvocationTargetException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		// Object to JSON in String
		String jsonInString = mapper.writeValueAsString(objectRequest);

//		log.debug("object Req :" + jsonInString);

		Object responses = serviceNotif.data(objectRequest);
		DataPegawaiResponse response = new DataPegawaiResponse();
		BeanUtils.copyProperties(responses, response);
//		log.debug("object Resp :" + response);

		return response;
	}
}

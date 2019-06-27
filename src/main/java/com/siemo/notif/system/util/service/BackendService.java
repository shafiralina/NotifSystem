package com.siemo.notif.system.util.service;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.SaveRequest;

public class BackendService {
	public Object executeCallSilotFeedback(HttpClient client, PostMethod post, SaveRequest request)
			throws IOException {
		String response = null;
		ObjectMapper mapper = new ObjectMapper();

		// TODO: Make String Json to pretty
		String jsonRequest = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);

		System.out.println("==================== REQUEST FEEDBACK ====================");
		System.out.println(jsonRequest);
		System.out.println("==========================================================");

		StringRequestEntity entity = new StringRequestEntity(jsonRequest, "application/json", null);
		post.setRequestEntity(entity);
		BaseResponse modelResponse = new BaseResponse();

		try {
			client.executeMethod(post);
			int statusCodeResponse = post.getStatusCode();
			// JIKA HTTP 200
			if (statusCodeResponse == HttpStatus.OK.value()) {
				BaseResponse modelResponseSuccess = new BaseResponse();

				response = post.getResponseBodyAsString();
				modelResponseSuccess = mapper.readValue(response, BaseResponse.class);

				return modelResponseSuccess;

			} else {
				// TODO SET ERROR HTTP
				return null;
			}

		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return modelResponse;

	}
}

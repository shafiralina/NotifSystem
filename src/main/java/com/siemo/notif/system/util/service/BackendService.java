package com.siemo.notif.system.util.service;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemo.notif.system.message.BaseResponse;
import com.siemo.notif.system.message.SendAllRequest;

@Service
public class BackendService {
	public Object HttpClientPost(HttpClient client, PostMethod post, SendAllRequest request)
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
	
	public String HttpClientGet(String url) {
		String result = "";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		try {
			client.executeMethod(getMethod);
			result = getMethod.getResponseBodyAsString();
		} catch (Exception e) {
			
		} finally {
			getMethod.releaseConnection();
		}
		return result;
	}
	 

//	public Object executeCallSilotFeedbackGet(HttpClient client, GetMethod get, GetSajaRequest request)
//			throws IOException {
//		String response = null;
//		ObjectMapper mapper = new ObjectMapper();
//
//		// TODO: Make String Json to pretty
//		String jsonRequest = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
//
//		System.out.println("==================== REQUEST FEEDBACK ====================");
//		System.out.println(jsonRequest);
//		System.out.println("==========================================================");
//
//		
//		GetSajaResponse modelResponse = new GetSajaResponse();
//
//		try {
//			client.executeMethod(get);
//			int statusCodeResponse = get.getStatusCode();
//			// JIKA HTTP 200
//			if (statusCodeResponse == HttpStatus.OK.value()) {
//				GetSajaResponse modelResponseSuccess = new GetSajaResponse();
//
//				response = get.getResponseBodyAsString();
//				modelResponseSuccess = mapper.readValue(response, GetSajaResponse.class);
//
//				return modelResponseSuccess;
//
//			} else {
//				// TODO SET ERROR HTTP
//				return null;
//			}
//
//		} catch (SocketTimeoutException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		return modelResponse;
//
//	}

	
	
}

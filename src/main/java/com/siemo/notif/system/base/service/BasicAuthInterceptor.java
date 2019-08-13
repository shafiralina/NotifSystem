package com.siemo.notif.system.base.service;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class BasicAuthInterceptor implements ClientHttpRequestInterceptor {
	
	private String username;
	private String password;

	public BasicAuthInterceptor(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution)
			throws IOException {
		HttpHeaders headers = httpRequest.getHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, encodeCredentialsForBasicAuth(username, password));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-Authorization", "2fced45abfd67f398e8bd2400c46df64"); //REST API BATCH
		headers.add("User-Agent", "BatchPushSender 1.0");
		return clientHttpRequestExecution.execute(httpRequest, bytes);
		
	}
	
	public static String encodeCredentialsForBasicAuth(String username, String password) {
		return "Basic " + new Base64().encodeToString((username + ":" + password).getBytes());
	}
}

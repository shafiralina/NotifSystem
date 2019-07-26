package com.siemo.notif.system.message;

import java.util.Map;

public class SendGroupRequest {
	private String body;
	private String userId;
	private Map<String, Object> group;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, Object> getGroup() {
		return group;
	}

	public void setGroup(Map<String, Object> group) {
		this.group = group;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}

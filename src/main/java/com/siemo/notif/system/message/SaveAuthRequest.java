package com.siemo.notif.system.message;

public class SaveAuthRequest {
	private String userId;
	private String tokenAuth;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTokenAuth() {
		return tokenAuth;
	}

	public void setTokenAuth(String tokenAuth) {
		this.tokenAuth = tokenAuth;
	}

}

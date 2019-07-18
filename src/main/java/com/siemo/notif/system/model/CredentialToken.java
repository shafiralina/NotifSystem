package com.siemo.notif.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credential_token")
public class CredentialToken {
	@Id
	private int id;
	
	@Column
	private String userId;

	@Column
	private String tokenAuth;
	
	@Column
	private int count;
	
	public CredentialToken() {
		
	}
	public CredentialToken(String userId, String tokenAuth, int count) {
		this.userId = userId;
		this.tokenAuth = tokenAuth;
		this.count = count;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return tokenAuth;
	}

	public void setToken(String token) {
		this.tokenAuth = token;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

package com.siemo.notif.system.model;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "master_data")
public class MasterData {
	@Id
	private String id;
	
	private String userId;
	
	private String tokenDevice;
	
	private String createdBy;
	
	private Date createdDated;
	
	private String updateBy;
	
	private Date updateDated;
	
	private String channel;
	
	private String versi;
	
	private String status;
	
	private String groupId;

	
	public MasterData() {
		
	}
	
	public MasterData(String userId) {
		this.userId = userId;
	}
	
	public MasterData(String userId, String tokenDevice, String channel, String status, String versi, String groupId, Date createdDated) {
		this.userId = userId;
		this.tokenDevice = tokenDevice;
		this.channel = channel;
		this.status = status;
		this.versi = versi;
		this.setGroupId(groupId);
		this.createdDated = createdDated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTokenDevice() {
		return tokenDevice;
	}

	public void setTokenDevice(String tokenDevice) {
		this.tokenDevice = tokenDevice;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDated() {
		return createdDated;
	}

	public void setCreatedDated(Date createdDated) {
		this.createdDated = createdDated;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDated() {
		return updateDated;
	}

	public void setUpdateDated(Date updateDated) {
		this.updateDated = updateDated;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getVersi() {
		return versi;
	}

	public void setVersi(String versi) {
		this.versi = versi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
			
}

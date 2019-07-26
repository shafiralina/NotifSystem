package com.siemo.notif.system.message;

public class ManageDataUserRequest {
	private String status;
	private String masterDataId;
	private String userId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMasterDataId() {
		return masterDataId;
	}

	public void setMasterDataId(String masterDataId) {
		this.masterDataId = masterDataId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}

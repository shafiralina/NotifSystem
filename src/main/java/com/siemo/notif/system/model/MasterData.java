package com.siemo.notif.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "master_data")
public class MasterData {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	
	@Column
	@NotNull
	private String userId;
	
	@Column
	@NotNull
	private String tokenDevice;
	
	@Column
	@NotNull
	private String createdBy;
	
	@Column
	@NotNull
	private Date createdDated;
	
	@Column
	@NotNull
	private String updateBy;
	
	@Column
	@NotNull
	private Date updateDated;
	
	@Column
	@NotNull
	private String channel;
	
	@Column
	@NotNull
	private String systemOperasi;

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

	public String getSystemOperasi() {
		return systemOperasi;
	}

	public void setSystemOperasi(String systemOperasi) {
		this.systemOperasi = systemOperasi;
	}
	
	
			
}

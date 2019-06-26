package com.siemo.notif.system.message;

import java.io.Serializable;

public class DataPegawaiResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5042127527477809422L;
	private String status;
	private String message;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
}

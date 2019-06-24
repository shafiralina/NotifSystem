package com.siemo.notif.system.message;

public class SendReq {
	public String id;
	public String data;
	
	public SendReq(String id, String data){
		this.id=id;
		this.data=data;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}

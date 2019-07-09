package com.siemo.notif.system.model;


import javax.persistence.Id;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "group_notif")
public class Group {
	
	@Id
	private String id;
	
	private String category;
	
	private String detail;
	
	public Group(String category, String detail) {
		this.category = category;
		this.detail = detail;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}

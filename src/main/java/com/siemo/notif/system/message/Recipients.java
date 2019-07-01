package com.siemo.notif.system.message;

import java.util.ArrayList;


public class Recipients {
	private ArrayList<String> tokens;
	
	public Recipients() {
		
	}
	
	public Recipients(ArrayList<String> tokens) {
		this.tokens = tokens;
	}

	public ArrayList<String> getTokens() {
		return tokens;
	}

	public void setTokens(ArrayList<String> tokens) {
		this.tokens = tokens;
	}  
}

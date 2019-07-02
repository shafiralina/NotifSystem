package com.siemo.notif.system.message;

import java.util.List;

import com.siemo.notif.system.model.MasterData;

public class SpecResponse {
	
	private List<MasterData> channel;
	private List<MasterData> systemOperasi;
	public List<MasterData> getChannel() {
		return channel;
	}
	public void setChannel(List<MasterData> channel) {
		this.channel = channel;
	}
	public List<MasterData> getSystemOperasi() {
		return systemOperasi;
	}
	public void setSystemOperasi(List<MasterData> systemOperasi) {
		this.systemOperasi = systemOperasi;
	}

}

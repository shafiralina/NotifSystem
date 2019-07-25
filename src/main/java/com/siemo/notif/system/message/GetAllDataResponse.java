package com.siemo.notif.system.message;

import java.util.List;

import com.siemo.notif.system.model.MasterData;

public class GetAllDataResponse extends BaseResponse {
	private List<MasterData> listData;

	public List<MasterData> getListData() {
		return listData;
	}

	public void setListData(List<MasterData> listData) {
		this.listData = listData;
	}

}

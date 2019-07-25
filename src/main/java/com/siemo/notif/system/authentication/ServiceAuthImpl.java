package com.siemo.notif.system.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siemo.notif.system.message.CredentialUserResponse;
import com.siemo.notif.system.model.CredentialToken;
import com.siemo.notif.system.model.CredentialUser;
import com.siemo.notif.system.repository.RepositoryAuthToken;
import com.siemo.notif.system.repository.RepositoryAuthUser;

@Service
public class ServiceAuthImpl {
	@Autowired
	private RepositoryAuthToken repositoryAuthToken;
	
	@Autowired
	private RepositoryAuthUser repositoryAuthUser;


	public CredentialUserResponse listCredential() {
		List<CredentialUser> data = repositoryAuthUser.findAll();
		CredentialUserResponse response = new CredentialUserResponse();
		response.setData(data);
		return response;
	}
	
	//save token to CredentialToken
	public String saveAuth(String userId, String token) {
		CredentialToken data = repositoryAuthToken.findByUserId(userId);
		data.setToken(token);
		data = repositoryAuthToken.save(data);
		return "ini token di notif "+token;
	}
}

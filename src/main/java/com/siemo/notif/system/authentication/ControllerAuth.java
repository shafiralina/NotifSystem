package com.siemo.notif.system.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.siemo.notif.system.message.CredentialUserResponse;

@RestController
public class ControllerAuth {
	
	@Autowired
	private ServiceAuthImpl serviceAuth;

	@GetMapping("/credential/user")
	public CredentialUserResponse listCredential() {
		CredentialUserResponse response = serviceAuth.listCredential();
		return response;
	}
	
	@GetMapping("/{userId}/{token}")
	public String saveAuth(@PathVariable String userId, @PathVariable String token) {
		String response = serviceAuth.saveAuth(userId, token);
		return response;
	}
}

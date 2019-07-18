package com.siemo.notif.system.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siemo.notif.system.model.CredentialToken;

@Repository
public interface RepositoryAuthToken extends CrudRepository<CredentialToken, String>{
	CredentialToken findByUserId(String userId);
}

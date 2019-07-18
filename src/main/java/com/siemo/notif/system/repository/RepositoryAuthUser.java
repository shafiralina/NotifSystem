package com.siemo.notif.system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siemo.notif.system.model.CredentialUser;

@Repository
public interface RepositoryAuthUser extends CrudRepository<CredentialUser, String>{
	List<CredentialUser> findAll();
}

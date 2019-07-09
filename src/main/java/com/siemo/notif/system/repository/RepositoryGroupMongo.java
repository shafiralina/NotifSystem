package com.siemo.notif.system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.siemo.notif.system.model.Group;

@Repository
public interface RepositoryGroupMongo extends MongoRepository<Group, String>{
	Group findByCategoryAndDetail(String category, String detail);
}

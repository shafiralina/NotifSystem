package com.siemo.notif.system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siemo.notif.system.model.HistoryNotificationExecution;

@Repository
public interface RepositoryHistory extends MongoRepository<HistoryNotificationExecution, String>{

}

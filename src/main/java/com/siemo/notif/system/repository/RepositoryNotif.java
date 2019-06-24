package com.siemo.notif.system.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siemo.notif.system.model.MasterData;

@Repository

public interface RepositoryNotif extends CrudRepository<MasterData, String>{

}

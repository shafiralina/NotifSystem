package com.siemo.notif.system.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.siemo.notif.system.model.MasterData;

@Repository
public interface RepositoryNotif extends MongoRepository<MasterData, String>{
	List<MasterData> findByUserId(String userId);
	List<MasterData> findMasterDataIdByUserId(String userId);
	ArrayList<MasterData> findTokensAndStatusByUserId(String userId);
	ArrayList<MasterData> findTokensByGroupId(String groupId);
	List<MasterData> findMasterDataIdByGroupId(String groupId);
	MasterData findAllById(String masterDataId);
}

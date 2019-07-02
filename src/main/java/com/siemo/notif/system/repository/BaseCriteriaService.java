package com.siemo.notif.system.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.siemo.notif.system.model.MasterData;

import net.kaczmarzyk.spring.data.jpa.domain.Conjunction;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.JoinFetch;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.utils.Converter;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;


public abstract class BaseCriteriaService {

	@Autowired
	private RepositoryNotif repositoryNotif;
	
	public BaseCriteriaService() {	
	}
	
	protected abstract RepositoryNotif getRepositoryNotif();



	public List<MasterData> findSearchHistoryRib(Map<String, Object> criteria) {
		List<Specification<MasterData>> specs = new ArrayList<Specification<MasterData>>();

		List<MasterData> users = null;
		Page<MasterData> pages = null;

		for (Map.Entry<String, Object> map : criteria.entrySet()) {
			// specs.add(new CustomEqual<MBHistoryDataUser>(map.getKey(),
			// map.getValue().toString()));
			if (map.getKey().equals("reason")) {
				if (map.getValue().equals("UNBLOCK_USER")) {
					specs.add(new CustomEqual<MasterData>(map.getKey(), map.getValue().toString()));
				} else if (map.getValue().equals("BLOCK_USER")) {
					// specs.add(new
					// CustomEqual<MBHistoryDataUser>(map.getKey(),
					// map.getValue().toString()));

					specs.add(new CustomEqual<MasterData>(map.getKey(), map.getValue().toString()));
					users = getRepositoryNotif().findAll(new Conjunction<MasterData>(specs));
					if (users.size() == 0) {
						specs = new ArrayList<Specification<MasterData>>();
						specs.add(new Like<MasterData>(map.getKey(), map.getValue().toString() + "_"));
					}
				}
			} else {
				specs.add(new CustomEqual<MasterData>(map.getKey(), map.getValue().toString()));
			}
		}

		users = null;
		if (specs != null && !specs.isEmpty()) {
			users = getRepositoryNotif().findAll(new Conjunction<MasterData>(specs));
		} else {
			users = (List<MasterData>) getRepositoryNotif().findAll();
		}
		return users;
	}
}

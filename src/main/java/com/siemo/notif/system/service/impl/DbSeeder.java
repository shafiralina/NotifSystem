package com.siemo.notif.system.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.siemo.notif.system.model.Group;
import com.siemo.notif.system.repository.RepositoryGroupMongo;
import com.siemo.notif.system.repository.RepositoryNotif;

@Component
public class DbSeeder implements CommandLineRunner{
	private RepositoryNotif repositoryNotif;
	private RepositoryGroupMongo repositoryGroup;
	
	public DbSeeder(RepositoryNotif repositoryNotif, RepositoryGroupMongo repositoryGroup) {
		this.repositoryNotif = repositoryNotif;
		this.repositoryGroup = repositoryGroup;
	}
	
	@Override
	public void run(String... args) throws Exception {
//		Group group1 = new Group(
//				"SistemOperasi",
//				"Android");
//		Group group2 = new Group(
//				"SistemOperasi",
//				"IOS");
//		Group group3 = new Group(
//				"Wilayah",
//				"Jakarta");
//		
//		List<Group> group = Arrays.asList(group1, group2, group3);
//		this.repositoryGroup.saveAll(group);	
		}
	}
	

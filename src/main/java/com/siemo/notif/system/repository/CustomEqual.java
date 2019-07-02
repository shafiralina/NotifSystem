package com.siemo.notif.system.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.kaczmarzyk.spring.data.jpa.domain.PathSpecification;

public class CustomEqual<T> extends PathSpecification<T>{
protected Object expectedValue;
	
	
	public CustomEqual(String path, Object value) {
		super(path);
		this.expectedValue = value;
	}
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.equal(path(root), expectedValue);
	}
}

package com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.entities.Rent;

public class RentRepositoryImpl implements RentRepositoryCustom {

	 @Autowired
	 private MongoTemplate mongoTemplate;
	
	@Override
	public List<Rent> findAll(Query qry) {
		// TODO Auto-generated method stub
		return mongoTemplate.find(qry, Rent.class);
	}

	




}

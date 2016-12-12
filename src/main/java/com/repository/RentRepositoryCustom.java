package com.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.entities.Rent;

public interface RentRepositoryCustom {
	List<Rent> findAll(Query qry);
}

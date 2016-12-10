package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.entities.Rent;

@Component("RentRepository")
public interface RentRepository extends MongoRepository<Rent, String> {
	Rent findById(String RentId);
}

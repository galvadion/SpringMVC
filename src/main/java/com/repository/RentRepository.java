package com.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.entities.Rent;

@Component("RentRepository")
public interface RentRepository extends MongoRepository<Rent, String>,RentRepositoryCustom {
	Rent findById(String RentId);
	List<Rent> findByClientId(int client_id);
}

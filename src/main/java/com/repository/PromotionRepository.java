package com.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.entities.Promotion;

@Component("PromotionRepository")
public interface PromotionRepository extends MongoRepository<Promotion, String> {
	Promotion findById(String promotionId);
	Promotion findByPromotionCode(String promotionCode);
	List<Promotion> findByCreationDate(LocalDate now);
}

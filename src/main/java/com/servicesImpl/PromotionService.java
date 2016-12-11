package com.servicesImpl;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Client;
import com.entities.Promotion;
import com.repository.PromotionRepository;

@Service
@Transactional
public class PromotionService{

	@Resource(name="PromotionRepository")
	 PromotionRepository promotionRepository;
	
	
	public Promotion create(Promotion promo){
		promo.setId(UUID.randomUUID().toString());
		promo.setCreationDate(LocalDate.now());
		promotionRepository.save(promo);
		return promo;
		
	}
	
	public Promotion getPromotionById(String id){
		return promotionRepository.findById(id);
	}
	
	public Promotion getPromotionByCode(String id){
		return promotionRepository.findByPromotionCode(id);
	}
	
	public boolean checkIfUsedPromotion(String id,int client_id){
		Promotion promo=getPromotionByCode(id);
		for(Client cli : promo.getClients()){
			if(cli.getId().equals(client_id)){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIfValid(String id){
		Promotion promo=getPromotionByCode(id);
		if(promo.getBeginPromotionDate().isBefore(LocalDate.now()) && promo.getLastPromotionDate().isAfter(LocalDate.now())){
			return true;
		}else{
			return false;
		}
	}

	public List<Promotion> getAll() {
		// TODO Auto-generated method stub
		return promotionRepository.findAll();
	}
	
	public List<Promotion> getCreatedToday(){
		return promotionRepository.findByCreationDate(LocalDate.now());
	}
	
}

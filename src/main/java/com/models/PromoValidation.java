package com.models;

import java.time.LocalDate;

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.entities.Model;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PromoValidation {
	private String promotionCode;
	private int origin;
	private int destiny;
	private Model model;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate originDate;
	

	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public int getDestiny() {
		return destiny;
	}
	public void setDestiny(int destiny) {
		this.destiny = destiny;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public LocalDate getOriginDate() {
		return originDate;
	}
	public void setOriginDate(LocalDate originDate) {
		this.originDate = originDate;
	}

	
}

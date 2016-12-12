package com.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.mongodb.core.mapping.Document;

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document
public class Promotion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "begin_promotion_date")
	private LocalDate beginPromotionDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "last_promoton_date")
	private LocalDate lastPromotionDate;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	private LocalDate creationDate;

	private String promotionCode;
	
	private float percentage;
	
	@OneToMany(mappedBy="promotionCode")
	private List<Booked> bookedList;
	
	private String descriptionText;

	public String getPromotionCode() {
		return promotionCode;
	}
	
	
	private List<Model> models;
	
	
	private List<BranchOffice> offices;
	
	
	private List<Client> clients;

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float porcentage) {
		this.percentage = porcentage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getBeginPromotionDate() {
		return beginPromotionDate;
	}

	public void setBeginPromotionDate(LocalDate beginPromotionDate) {
		this.beginPromotionDate = beginPromotionDate;
	}

	public LocalDate getLastPromotionDate() {
		return lastPromotionDate;
	}

	public void setLastPromotionDate(LocalDate lastPromotionDate) {
		this.lastPromotionDate = lastPromotionDate;
	}

	public List<Booked> getBookedList() {
		return bookedList;
	}

	public void setBookedList(List<Booked> bookedList) {
		this.bookedList = bookedList;
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	public List<BranchOffice> getOffices() {
		return offices;
	}

	public void setOffices(List<BranchOffice> offices) {
		this.offices = offices;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
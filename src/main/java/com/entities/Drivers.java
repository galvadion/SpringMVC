package com.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Drivers implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	private String driversName;
	
	private String driversDocument;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "birth_date")
	private LocalDate birthDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "license_expiration_date")
	private LocalDate licenseExpirationDate;

	public String getDriversName() {
		return driversName;
	}

	public void setDriversName(String driversName) {
		this.driversName = driversName;
	}

	public String getDriversDocument() {
		return driversDocument;
	}

	public void setDriversDocument(String driversDocument) {
		this.driversDocument = driversDocument;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getLicenseExpirationDate() {
		return licenseExpirationDate;
	}

	public void setLicenseExpirationDate(LocalDate licenseExpirationDate) {
		this.licenseExpirationDate = licenseExpirationDate;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}

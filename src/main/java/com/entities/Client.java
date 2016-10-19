package com.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
public class Client extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "birth_date")
	private LocalDate birthDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "license_expiration_date")
	private LocalDate licenseExpirationDate;
	
	@OneToMany(mappedBy="client")
	private List<Booked> booked;
	
	public LocalDate getBirthDay() {
		return birthDate;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDate = birthDay;
	}
	public LocalDate getLicenseExpirationDate() {
		return licenseExpirationDate;
	}
	public void setLicenseExpirationDate(LocalDate licenceExpirationDate) {
		this.licenseExpirationDate = licenceExpirationDate;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

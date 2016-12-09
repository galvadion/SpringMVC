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
	
	private String document;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "birth_date")
	private LocalDate birthDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "license_expiration_date")
	private LocalDate licenseExpirationDate;
	
	private boolean allowNotification;
	
	@OneToMany(mappedBy="client")
	private List<Booked> booked;
	
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
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public List<Booked> getBooked() {
		return booked;
	}
	public void setBooked(List<Booked> booked) {
		this.booked = booked;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Client [birthDate=" + birthDate + ", licenseExpirationDate=" + licenseExpirationDate + ", booked="
				+ booked + "]";
	}
	public boolean isAllowNotification() {
		return allowNotification;
	}
	public void setAllowNotification(boolean allowNotification) {
		this.allowNotification = allowNotification;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	
	
	
}

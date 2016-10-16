package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Client extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name= "birth_date")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	@Column(name= "license_expiration_date")
	@Temporal(TemporalType.DATE)
	private Date licenseExpirationDate;
	
	
	public Date getBirthDay() {
		return birthDate;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDate = birthDay;
	}
	public Date getLicenseExpirationDate() {
		return licenseExpirationDate;
	}
	public void setLicenseExpirationDate(Date licenceExpirationDate) {
		this.licenseExpirationDate = licenceExpirationDate;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

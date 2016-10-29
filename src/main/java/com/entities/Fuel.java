package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Fuel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	private boolean unavailable=false;
	
	@Column(name="fuel_type")
	private String fuelType;
	
	@Column(name="fuel_price")
	private Float fuelPrice;
	
	@JsonIgnore
	@OneToMany(mappedBy="fuel")
	private List<Model> model;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Float getFuelPrice() {
		return fuelPrice;
	}

	public void setFuelPrice(Float fuelPrice) {
		this.fuelPrice = fuelPrice;
	}

	public List<Model> getModel() {
		return model;
	}

	public void setModel(List<Model> vehicules) {
		this.model = vehicules;
	}

	public boolean isUnavailable() {
		return unavailable;
	}

	public void setUnavailable(boolean unavailable) {
		this.unavailable = unavailable;
	}
	
	
}

package com.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Fuel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="fuel_type")
	private String fuelType;
	
	@Column(name="fuel_price")
	private Float fuelPrice;
	
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
	
	
}

package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", nullable=false)
	private String modelName;
	
	 
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	 
	@ManyToOne
	@JoinColumn(name="fuel_id")
	private Fuel fuel;
	
	 
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	private int year;
	
	private int passangers;
	
	private int luggage;
	
	private int cylinders;
	
	private boolean airConditioner;
	
	@OneToMany(mappedBy="model")
	private List<Vehicule> vehicules;
	
	private String transmission;
	
	private Float insurance;
	
	@Column(name= "full_tank")
	private Float fullTank;
	
    public Brand getBrand() { return brand; }

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int ano) {
		this.year = ano;
	}
	public int getPassangers() {
		return passangers;
	}
	public void setPassangers(int passangers) {
		this.passangers = passangers;
	}
	public int getLuggage() {
		return luggage;
	}
	public void setLuggage(int luggage) {
		this.luggage = luggage;
	}
	public int getCylinders() {
		return cylinders;
	}
	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}
	public boolean isAirConditioner() {
		return airConditioner;
	}
	public void setAirConditioner(boolean airConditioner) {
		this.airConditioner = airConditioner;
	}
	public List<Vehicule> getVehicules() {
		return vehicules;
	}
	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	public Fuel getFuel() {
		return fuel;
	}


	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Float getInsurance() {
		return insurance;
	}


	public void setInsurance(Float insurance) {
		this.insurance = insurance;
	}


	public Float getFullTank() {
		return fullTank;
	}


	public void setFullTank(Float fullTank) {
		this.fullTank = fullTank;
	}
	
	
	
}

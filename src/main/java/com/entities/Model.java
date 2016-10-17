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
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

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

	@NotNull(message="The name can't be null")
	@NotEmpty(message="The name can't be empty")
	@Column(name = "name", nullable=false)
	private String name;
	
	 
	@ManyToOne
	@JoinColumn(name="brand_id", nullable=false)
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
	private List<Vehicle> vehicles;
	
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
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
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
	public List<Vehicle> getVehicules() {
		return vehicles;
	}
	public void setVehicules(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
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

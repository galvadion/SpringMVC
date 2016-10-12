package com.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", nullable=false)
	private String modelName;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;

	private int year;
	
	private int passangers;
	
	private int luggage;
	
	private int cylinders;
	
	private boolean airConditioner;
	
	@OneToMany(mappedBy="model")
	private List<Vehicule> vehicules;
	
	@OneToMany(mappedBy="model")
	private List<Booked> booked;
	
	private String transmission;
	
	@ManyToOne
    @JoinColumn(name="model", nullable=false)
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


	public List<Booked> getBooked() {
		return booked;
	}


	public void setBooked(List<Booked> booked) {
		this.booked = booked;
	}
	
	
	
}

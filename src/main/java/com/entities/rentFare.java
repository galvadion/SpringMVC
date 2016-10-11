package com.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class rentFare {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	private Float gps;
	
	private Float insurance;
	
	@Column(name= "full_tank")
	private Float fullTank;
	
	@OneToMany(mappedBy="rentFare")
	private List<Vehicule> vehicules;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getGps() {
		return gps;
	}

	public void setGps(Float gps) {
		this.gps = gps;
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

	public List<Vehicule> getVehicules() {
		return vehicules;
	}

	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}
	
	
}

package com.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.models.Vehicule_Status;


@Entity
public class Vehicule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "license_plate")
	private String licensePlate;
	
	private String description;
	
	private String observations;
	
	@Column(name = "license_plate_expiration_date")
	private Date licensePlateExpirationDate;
	
	@Enumerated(EnumType.STRING)
	private Vehicule_Status state;
	
	private String color;
	
	@Column(name = "chasis_number")
	private String chasisNr;
	
	@Column(name = "motor_number")
	private String motorNr;

	private Integer kilometers;
	
	@ManyToOne
	@JoinColumn(name="model_id", nullable=false)
	private Model model;
	
	@ManyToOne
	@JoinColumn(name="fuel_id", nullable=false)
	private Fuel fuel;
	
	@ManyToOne
	@JoinColumn(name="branch_office_id", nullable=false)
	private BranchOffice branchOffice;
	
	@OneToMany(mappedBy="vehicule")
	private List<Rent> rent;

	@OneToMany(mappedBy="vehicule")
	private List<StatusBetweenDates> status;
	
	
	
	public List<StatusBetweenDates> getStatus() {
		return status;
	}

	public void setStatus(List<StatusBetweenDates> status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Date getLicensePlateExpirationDate() {
		return licensePlateExpirationDate;
	}

	public void setLicensePlateExpirationDate(Date licensePlateExpirationDate) {
		this.licensePlateExpirationDate = licensePlateExpirationDate;
	}

	public Vehicule_Status getState() {
		return state;
	}

	public void setState(Vehicule_Status state) {
		this.state = state;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getChasisNr() {
		return chasisNr;
	}

	public void setChasisNr(String chasisNr) {
		this.chasisNr = chasisNr;
	}

	public String getMotorNr() {
		return motorNr;
	}

	public void setMotorNr(String motorNr) {
		this.motorNr = motorNr;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}


	public List<Rent> getRent() {
		return rent;
	}

	public void setRent(List<Rent> rent) {
		this.rent = rent;
	}


	public Integer getKilometers() {
		return kilometers;
	}

	public void setKilometers(Integer kilometers) {
		this.kilometers = kilometers;
	}


	public BranchOffice getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	
	
}

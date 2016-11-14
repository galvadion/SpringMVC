package com.entities;

import java.io.Serializable;
import java.time.LocalDate;
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

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.models.Vehicle_Status;


@Entity
public class Vehicle implements Serializable{

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
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name = "license_plate_expiration_date")
	private LocalDate licensePlateExpirationDate;
	
	@Enumerated(EnumType.STRING)
	private Vehicle_Status state;
	
	private String color;
	
	@Column(name = "chasis_number")
	private String chasisNr;
	
	@Column(name = "motor_number")
	private String motorNr;

	private Integer kilometers;
	
	private boolean unavailable=false;
	
	@ManyToOne
	@JoinColumn(name="model_id", nullable=false)
	private Model model;
	
	
	@ManyToOne
	@JoinColumn(name="branch_office_id", nullable=false)
	private BranchOffice branchOffice;
	
	
	@OneToMany(mappedBy="vehicle")
	private List<Booked> booked;

	@OneToMany(mappedBy="vehicle")
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

	public LocalDate getLicensePlateExpirationDate() {
		return licensePlateExpirationDate;
	}

	public void setLicensePlateExpirationDate(LocalDate licensePlateExpirationDate) {
		this.licensePlateExpirationDate = licensePlateExpirationDate;
	}

	public Vehicle_Status getState() {
		return state;
	}

	public void setState(Vehicle_Status state) {
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

	public List<Booked> getBooked() {
		return booked;
	}

	public void setBooked(List<Booked> booked) {
		this.booked = booked;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isUnavailable() {
		return unavailable;
	}

	public void setUnavailable(boolean unavailable) {
		this.unavailable = unavailable;
	}

	
	
}

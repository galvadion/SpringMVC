package com.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Booked implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "begin_booked_date")
	private LocalDate beginbookedDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "last_booked_date")
	private LocalDate lastbookedDate;
	
	@Column(name = "transaction_number")
	private String transactionNr;
	
	@Column(name="initial_amount")
	private Float initialAmount;
	
	@Column(name= "transaction_date")
	private LocalDate transactionDate;
	
	@Column(name="with_gps")
	private boolean withGps;
	
	@Column(name="with_insurance")
	private boolean withInsurance;
	
	@Column(name="with_full_tank")
	private boolean withFullTank;
	
	@OneToOne
	 @JoinColumn(
		        name="rent_id", unique=true, nullable=true, updatable=true)
	private Rent rent;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id", nullable=false)
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name="origin_office_id", nullable=false)
	private BranchOffice originOffice;
	
	@ManyToOne
	@JoinColumn(name="end_office_id", nullable=false)
	private BranchOffice endOffice;
	
	@ManyToOne
	@JoinColumn(name="client_id", nullable=false)
	private Client client;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getBeginbookedDate() {
		return beginbookedDate;
	}

	public void setBeginbookedDate(LocalDate beginbookedDate) {
		this.beginbookedDate = beginbookedDate;
	}

	public LocalDate getLastbookedDate() {
		return lastbookedDate;
	}

	public void setLastbookedDate(LocalDate lastbookedDate) {
		this.lastbookedDate = lastbookedDate;
	}

	public Vehicle getVehicule() {
		return vehicle;
	}

	public void setVehicule(Vehicle model) {
		this.vehicle = model;
	}

	public String getTransactionNr() {
		return transactionNr;
	}

	public void setTransactionNr(String transactionNr) {
		this.transactionNr = transactionNr;
	}

	public Float getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(Float initialAmount) {
		this.initialAmount = initialAmount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isWithGps() {
		return withGps;
	}

	public void setWithGps(boolean withGps) {
		this.withGps = withGps;
	}

	public boolean isWithInsurance() {
		return withInsurance;
	}

	public void setWithInsurance(boolean withInsurance) {
		this.withInsurance = withInsurance;
	}

	public boolean isWithFullTank() {
		return withFullTank;
	}

	public void setWithFullTank(boolean withFullTank) {
		this.withFullTank = withFullTank;
	}

	public BranchOffice getOriginOffice() {
		return originOffice;
	}

	public void setOriginOffice(BranchOffice originOffice) {
		this.originOffice = originOffice;
	}

	public BranchOffice getEndOffice() {
		return endOffice;
	}

	public void setEndOffice(BranchOffice endOffice) {
		this.endOffice = endOffice;
	}

	
}

package com.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.springframework.data.mongodb.core.mapping.Document;

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Document
public class Rent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;
	
	@Column(name = "transaction_number")
	private String transactionNr;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "transaction_date")
	private LocalDate transactionDate;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
	@Column(name= "return_date")
	private LocalDate returnDate;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
	@Column(name= "delivery_date")
	private LocalDate deliveryDate;
	
	private Float initialAmount;
	
	private Float finalAmount;
	
	private List<RentLine> rentLine;
	
	private String statusAtReturn;
	
	private List<Drivers> driversList;
	
    private int booked_id;
    
    private int clientId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactionNr() {
		return transactionNr;
	}

	public void setTransactionNr(String transactionNr) {
		this.transactionNr = transactionNr;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Float getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(Float initialAmount) {
		this.initialAmount = initialAmount;
	}

	public Float getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Float finalAmount) {
		this.finalAmount = finalAmount;
	}

	public List<RentLine> getRentLine() {
		return rentLine;
	}

	public void setRentLine(List<RentLine> rentLine) {
		this.rentLine = rentLine;
	}

	public String getStatusAtReturn() {
		return statusAtReturn;
	}

	public void setStatusAtReturn(String statusAtReturn) {
		this.statusAtReturn = statusAtReturn;
	}

	public int getBooked_id() {
		return booked_id;
	}

	public void setBooked_id(int booked_id) {
		this.booked_id = booked_id;
	}

	@Override
	public String toString() {
		return "Rent [id=" + id + ", transactionNr=" + transactionNr + ", transactionDate=" + transactionDate
				+ ", returnDate=" + returnDate + ", deliveryDate=" + deliveryDate + ", initialAmount=" + initialAmount
				+ ", finalAmount=" + finalAmount + ", rentLine=" + rentLine + ", statusAtReturn=" + statusAtReturn
				+ ", booked_id=" + booked_id + ", driverList="+driversList+"]";
	}

	public List<Drivers> getDriversList() {
		return driversList;
	}

	public void setDriversList(List<Drivers> driversList) {
		this.driversList = driversList;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}



	
	
	
}

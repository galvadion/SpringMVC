package com.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Rent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "transaction_number")
	private String transactionNr;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	@Column(name= "transaction_date")
	private LocalDate transactionDate;
	
	@Column(name= "return_date")
	private LocalDate returnDate;
	
	@Column(name= "delivery_date")
	private LocalDate deliveryDate;
	
	@Column(name="initial_amount")
	private Float initialAmount;
	
	@Column(name ="final_amount")
	private Float finalAmount;
	
	@OneToMany(mappedBy="rent")
	private List<RentLine> rentLine;
	
	@ManyToOne
	@JoinColumn(name ="client_id")
	private Client client;
	
	@Column(name="status_at_return")
	private String statusAtReturn;
	
    @OneToOne(optional=false, mappedBy="rent")
    private Booked booked;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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


	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getStatusAtReturn() {
		return statusAtReturn;
	}

	public void setStatusAtReturn(String statusAtReturn) {
		this.statusAtReturn = statusAtReturn;
	}

	public Booked getBooked() {
		return booked;
	}

	public void setBooked(Booked booked) {
		this.booked = booked;
	}

	
	
	
}

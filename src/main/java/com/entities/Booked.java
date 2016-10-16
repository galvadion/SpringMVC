package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column(name= "begin_booked_date")
	@Temporal(TemporalType.DATE)
	private Date beginbookedDate;
	
	@Column(name= "last_booked_date")
	@Temporal(TemporalType.DATE)
	private Date lastbookedDate;
	
	@Column(name = "transaction_number")
	private String transactionNr;
	
	@Column(name="initial_amount")
	private Float initialAmount;
	
	@Column(name= "transaction_date")
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	
	@OneToOne
	 @JoinColumn(
		        name="rent_id", unique=true, nullable=true, updatable=true)
	private Rent rent;
	
	@ManyToOne
	@JoinColumn(name="model_id", nullable=false)
	private Model model;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBeginbookedDate() {
		return beginbookedDate;
	}

	public void setBeginbookedDate(Date beginbookedDate) {
		this.beginbookedDate = beginbookedDate;
	}

	public Date getLastbookedDate() {
		return lastbookedDate;
	}

	public void setLastbookedDate(Date lastbookedDate) {
		this.lastbookedDate = lastbookedDate;
	}

	public Model getmodel() {
		return model;
	}

	public void setmodel(Model model) {
		this.model = model;
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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	
}

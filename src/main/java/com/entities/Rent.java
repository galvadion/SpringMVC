package com.entities;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "transaction_number")
	private String transactionNr;
	
	@Column(name= "transaction_date")
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	@Column(name= "begin_rent_date")
	@Temporal(TemporalType.DATE)
	private Date beginRentDate;
	
	@Column(name= "last_rent_date")
	@Temporal(TemporalType.DATE)
	private Date lastRentDate;
	
	@Column(name= "delivery_date")
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;
	
	@Column(name="initial_amount")
	private Float initialAmount;
	
	@Column(name ="final_amount")
	private Float finalAmount;
	
	@OneToMany(mappedBy="rent")
	private List<RentLine> rentLine;
	
	@ManyToOne
	@JoinColumn(name="origin_branch_office")
	private BranchOffice originBranchOffice;
	
	@ManyToOne
	@JoinColumn(name="end_branch_office")
	private BranchOffice endBranchOffice;
	
	@ManyToOne
	@JoinColumn(name="vehicule_id")
	private Vehicule vehicule;
	
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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Date getBeginRentDate() {
		return beginRentDate;
	}

	public void setBeginRentDate(Date beginRentDate) {
		this.beginRentDate = beginRentDate;
	}

	public Date getLastRentDate() {
		return lastRentDate;
	}

	public void setLastRentDate(Date lastRentDate) {
		this.lastRentDate = lastRentDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
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

	public BranchOffice getOriginBranchOffice() {
		return originBranchOffice;
	}

	public void setOriginBranchOffice(BranchOffice originBranchOffice) {
		this.originBranchOffice = originBranchOffice;
	}

	public BranchOffice getEndBranchOffice() {
		return endBranchOffice;
	}

	public void setEndBranchOffice(BranchOffice endBranchOffice) {
		this.endBranchOffice = endBranchOffice;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
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

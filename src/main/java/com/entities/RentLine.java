package com.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RentLine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private rentLinePK rentLinePK;
	
	@ManyToOne
	@JoinColumn(name="rent_id",referencedColumnName="id" ,insertable =false, updatable=false)
	private Rent rent;
	
	private Float amount;
	
	private String detail;

	public rentLinePK getRentLinePK() {
		return rentLinePK;
	}

	public void setRentLinePK(rentLinePK rentLinePK) {
		this.rentLinePK = rentLinePK;
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}

@Embeddable
class rentLinePK implements Serializable{
	
	@Basic(optional =false)
	@Column(name="rent_line_id")
	private Integer rentLineId;
	
	@Basic(optional =false)
	@Column(name="rent_id")
	private Integer rentId;

	public Integer getRentLineId() {
		return rentLineId;
	}
	
	public rentLinePK(){}

	public void setRentLineId(Integer rentLineId) {
		this.rentLineId = rentLineId;
	}

	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	public rentLinePK(Integer rentLineId, Integer rentId) {
		super();
		this.rentLineId = rentLineId;
		this.rentId = rentId;
	}
	
	
}

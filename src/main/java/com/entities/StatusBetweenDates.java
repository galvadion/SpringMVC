package com.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.models.Vehicule_Status;

@Entity
@Table(name = "status_in_dates")
public class StatusBetweenDates implements Serializable{

	@Override
	public String toString() {
		return "StatusBetweenDates [id=" + id + ", beginDate=" + beginDate + ", endDate=" + endDate + ", status="
				+ status + ", vehicule=" + vehicule + ", branchOffice=" + branchOffice + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name= "begin_date")
	private LocalDate beginDate;
	
	@Column(name= "end_date")
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	private Vehicule_Status status;
	
	@ManyToOne
	@JoinColumn(name="vehicule_id", nullable=false)
	private Vehicule vehicule;
	
	@ManyToOne
	@JoinColumn(name="branch_office_id", nullable=false)
	private BranchOffice branchOffice;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Vehicule_Status getStatus() {
		return status;
	}

	public void setStatus(Vehicule_Status status) {
		this.status = status;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public BranchOffice getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	
}

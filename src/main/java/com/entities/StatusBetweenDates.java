package com.entities;

import java.io.Serializable;
import java.time.LocalDate;

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

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.models.Vehicle_Status;

@Entity
@Table(name = "status_in_dates")
public class StatusBetweenDates implements Serializable{

	@Override
	public String toString() {
		return "StatusBetweenDates [id=" + id + ", beginDate=" + beginDate + ", endDate=" + endDate + ", status="
				+ status + ", vehicle=" + vehicle + ", branchOffice=" + branchOffice + "]";
	}

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
	@Column(name= "begin_date")
	private LocalDate beginDate;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	@Column(name= "end_date")
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	private Vehicle_Status status;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id", nullable=false)
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name="branch_office_id", nullable=false)
	private BranchOffice branchOffice;
	
	@ManyToOne
	@JoinColumn(name="branch_office_end", nullable=false)
	private BranchOffice branchOfficeEnd;
	


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

	public Vehicle_Status getStatus() {
		return status;
	}

	public void setStatus(Vehicle_Status status) {
		this.status = status;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public BranchOffice getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	public BranchOffice getBranchOfficeEnd() {
		return branchOfficeEnd;
	}

	public void setBranchOfficeEnd(BranchOffice branchOfficeEnd) {
		this.branchOfficeEnd = branchOfficeEnd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

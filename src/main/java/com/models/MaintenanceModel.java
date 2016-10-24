package com.models;

import java.io.Serializable;
import java.time.LocalDate;

public class MaintenanceModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer vehicleId;
	private LocalDate firstDate;
	private LocalDate endDate;
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public LocalDate getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(LocalDate firstDate) {
		this.firstDate = firstDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

package com.models;

import java.io.Serializable;
import java.time.LocalDate;

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class SearchFilter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	private LocalDate beginDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	private LocalDate endDate;
	private boolean airConditioner;
	private int passangers;
	private int luggage;
	private int officeOriginId;
	private int officeEndId;
	
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
	public boolean isAirConditioner() {
		return airConditioner;
	}
	public void setAirConditioner(boolean airConditioner) {
		this.airConditioner = airConditioner;
	}
	public int getPassangers() {
		return passangers;
	}
	public void setPassangers(int passangers) {
		this.passangers = passangers;
	}
	public int getLuggage() {
		return luggage;
	}
	public void setLuggage(int luggage) {
		this.luggage = luggage;
	}

	@Override
	public String toString() {
		return "SearchFilter [beginDate=" + beginDate + ", endDate=" + endDate + ", airConditioner=" + airConditioner
				+ ", passangers=" + passangers + ", luggage=" + luggage + "]";
	}
	public int getOfficeOriginId() {
		return officeOriginId;
	}
	public void setOfficeOriginId(int officeOriginId) {
		this.officeOriginId = officeOriginId;
	}
	public int getOfficeEndId() {
		return officeEndId;
	}
	public void setOfficeEndId(int officeEndId) {
		this.officeEndId = officeEndId;
	}
	
	
}

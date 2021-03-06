package com.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.configuration.LocalDateDeserializer;
import com.configuration.LocalDateSerializer;
import com.entities.Extras;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BookingModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idModel;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate startDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate endDate;
	private Integer originBranchOfficeId;
	private Integer endBranchOfficeId;
	private boolean withInsurance;
	private boolean withFullTank;
	
	public Integer getIdModel() {
		return idModel;
	}
	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Integer getOriginBranchOfficeId() {
		return originBranchOfficeId;
	}
	public void setOriginBranchOfficeId(Integer originBranchOfficeId) {
		this.originBranchOfficeId = originBranchOfficeId;
	}
	public Integer getEndBranchOfficeId() {
		return endBranchOfficeId;
	}
	public void setEndBranchOfficeId(Integer endBranchOfficeId) {
		this.endBranchOfficeId = endBranchOfficeId;
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
	
	
	
}

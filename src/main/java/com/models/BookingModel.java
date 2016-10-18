package com.models;

import java.io.Serializable;
import java.time.LocalDate;

public class BookingModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idModel;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer originBranchOfficeId;
	private Integer endBranchOfficeId;
	private boolean withGps;
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
	public boolean isWithGps() {
		return withGps;
	}
	public void setWithGps(boolean withGps) {
		this.withGps = withGps;
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

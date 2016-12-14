package com.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.entities.BranchOffice;
import com.entities.Vehicle;

public interface VehicleService extends GenericService<Vehicle,Integer> {

	public List<Vehicle> getAvailable();

	public List<Vehicle> getPickedUpToday(BranchOffice bo);
	
	public List<Vehicle> getReturnedToday(BranchOffice bo);

	public void abortNewEvents(Vehicle vehicle,LocalDate firstDate);

}

package com.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.entities.Vehicle;

public interface VehicleService extends GenericService<Vehicle,Integer> {

	public List<Vehicle> getAvailable();

}

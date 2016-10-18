package com.dao;


import com.entities.Vehicle;
import com.models.BookingModel;

public interface VehicleDao extends GenericDao<Vehicle, Integer> {
	public Vehicle getVehiculeAvailable(BookingModel model);
}

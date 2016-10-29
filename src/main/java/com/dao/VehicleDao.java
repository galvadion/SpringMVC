package com.dao;


import java.util.List;

import com.entities.Vehicle;
import com.models.BookingModel;

public interface VehicleDao extends GenericDao<Vehicle, Integer> {
	public Vehicle getVehiculeAvailable(BookingModel model);
	public List<Vehicle> getAvailable();
}

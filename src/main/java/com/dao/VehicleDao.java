package com.dao;

import java.util.List;

import com.entities.Vehicle;
import com.models.BookingModel;
import com.models.SearchFilter;

public interface VehicleDao extends GenericDao<Vehicle, Integer> {
	public Vehicle getVehiculeAvailable(BookingModel model);
}

package com.dao;

import java.util.List;

import com.entities.Vehicule;
import com.models.BookingModel;
import com.models.SearchFilter;

public interface VehiculeDao extends GenericDao<Vehicule, Integer> {
	public Vehicule getVehiculeAvailable(BookingModel model);
}

package com.dao;

import java.util.List;

import com.entities.Vehicule;
import com.models.Filters;

public interface VehiculeDao extends GenericDao<Vehicule, Integer> {
	public List<Vehicule> vehiculesAvailablesInDates(Filters filter);
}

package com.dao;

import java.util.List;

import com.entities.Fuel;
import com.entities.Model;
import com.entities.StatusBetweenDates;
import com.models.Filters;

public interface StatusBetweenDatesDao extends GenericDao<StatusBetweenDates, Integer> {
	public List<Model> modelsInDates(Filters filter,Fuel fuel,boolean byFuel);
}

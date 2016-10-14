package com.dao;

import java.util.List;

import com.entities.Fuel;
import com.entities.Model;
import com.models.Filters;

public interface ModelDao extends GenericDao<Model, Integer> {
	public List<Model> modelInFilter(Filters filter,Fuel fuel,boolean byFuel);
}

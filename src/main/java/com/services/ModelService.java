package com.services;

import java.util.List;

import com.entities.Model;
import com.models.Filters;

public interface ModelService extends GenericService<Model,Integer> {
	public List<Model> getModelsBetweenFilter(Filters filter);
}

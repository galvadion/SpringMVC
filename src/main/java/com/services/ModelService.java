package com.services;

import java.util.List;

import com.entities.Model;
import com.models.SearchFilter;

public interface ModelService extends GenericService<Model,Integer> {
	public List<Model> getModelsBetweenFilter(SearchFilter filter);
}

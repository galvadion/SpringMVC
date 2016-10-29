package com.dao;

import java.util.List;

import com.entities.Model;
import com.models.SearchFilter;

public interface ModelDao extends GenericDao<Model, Integer> {
	public List<Model> modelInFilter(SearchFilter filter);

	public List<Model> getAvailable();

}

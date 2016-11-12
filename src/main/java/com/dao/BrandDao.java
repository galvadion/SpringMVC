package com.dao;


import java.util.List;

import com.entities.Brand;

public interface BrandDao extends GenericDao<Brand, Integer> {

	public List<Brand> getAvailable();

	public List<Brand> getNotEmpty();

}

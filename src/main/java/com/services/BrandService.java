package com.services;

import java.util.List;

import com.entities.Brand;

public interface BrandService extends GenericService<Brand,Integer> {

	public void removeCascade(Brand entity);
    public List<Brand> getAvailable();

}

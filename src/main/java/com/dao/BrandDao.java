package com.dao;

import java.util.List;

import com.entities.Brand;

public interface BrandDao {
	public void saveOrUpdate(Brand brand);
	public List<Brand> list();
	public boolean delete(Brand brand);
	public Brand getBrandById(Integer id);
}

package com.dao;

import java.util.List;

import com.entities.Model;

public interface ModelDao {
	public void saveOrUpdate(Model Model);
	public List<Model> list();
	public boolean delete(Model Model);
}

package com.dao;

import java.util.List;

public interface GenericDao<T,K> {
	public boolean saveOrUpdate(T object);
	public List<T> getAll();
	public boolean delete(T object);
	public T getById(K key);
}

package com.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FuelDao;
import com.entities.Fuel;

@Repository
@Transactional
public class FuelDaoImpl extends GenericDaoImpl<Fuel, Integer> implements FuelDao {

	public FuelDaoImpl(){
		super(null);
	}
	
	public FuelDaoImpl(Class<Fuel> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}

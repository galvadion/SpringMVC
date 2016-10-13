package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FuelDao;
import com.entities.Fuel;

@Repository
@Transactional
public class FueDaoImpl extends GenericDaoImpl<Fuel, Integer> implements FuelDao {

	public FueDaoImpl(){
		super(null);
	}
	
	public FueDaoImpl(Class<Fuel> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}

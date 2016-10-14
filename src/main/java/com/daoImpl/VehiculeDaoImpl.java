package com.daoImpl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.VehiculeDao;
import com.entities.Vehicule;

@Repository
@Transactional
public class VehiculeDaoImpl extends GenericDaoImpl<Vehicule, Integer> implements VehiculeDao {

	public VehiculeDaoImpl(){
		super(null);
	}
	public VehiculeDaoImpl(Class<Vehicule> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	
}

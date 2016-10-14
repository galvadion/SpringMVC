package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.LocationDao;
import com.entities.Location;

@Repository
@Transactional
public class LocationDaoImpl extends GenericDaoImpl<Location, Integer> implements LocationDao {

	public LocationDaoImpl(){
		super(null);
	}
	
	public LocationDaoImpl(Class<Location> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}

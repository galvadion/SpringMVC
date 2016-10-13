package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RentFareDao;
import com.entities.RentFare;

@Repository
@Transactional
public class RentFareDaoImpl extends GenericDaoImpl<RentFare, Integer> implements RentFareDao {

	public RentFareDaoImpl(){
		super(null);
	}
	
	public RentFareDaoImpl(Class<RentFare> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}

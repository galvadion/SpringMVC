package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RentDao;
import com.entities.Rent;

@Repository
@Transactional
public class RentDaoImpl extends GenericDaoImpl<Rent, Integer> implements RentDao {

	public RentDaoImpl(){
		super(null);
	}
	
	public RentDaoImpl(Class<Rent> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}

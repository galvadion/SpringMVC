package com.daoImpl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ExtrasDao;
import com.entities.Extras;

@Repository
@Transactional
public class ExtrasDaoImpl extends GenericDaoImpl<Extras, Integer> implements ExtrasDao {

	public ExtrasDaoImpl(){
		super(null);
	}
	
	public ExtrasDaoImpl(Class<Extras> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}

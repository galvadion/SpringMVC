package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

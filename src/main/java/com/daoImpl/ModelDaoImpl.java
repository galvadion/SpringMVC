package com.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ModelDao;
import com.entities.Model;
import com.entities.Model;

@Repository
@Transactional
public class ModelDaoImpl extends GenericDaoImpl<Model, Integer> implements ModelDao {

	public ModelDaoImpl(){
		super(null);
	}
	
	public ModelDaoImpl(Class<Model> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}


}

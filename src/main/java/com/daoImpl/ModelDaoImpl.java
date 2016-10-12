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
public class ModelDaoImpl implements ModelDao {

	@Autowired
	SessionFactory session;
	
	public void saveOrUpdate(Model Model) {
		session.getCurrentSession().saveOrUpdate(Model);
	}

	public List<Model> list() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Model").list();
	}

	public boolean delete(Model Model) {
		try{
			session.getCurrentSession().delete(Model);
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	

}

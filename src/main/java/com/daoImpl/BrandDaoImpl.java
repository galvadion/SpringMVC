package com.daoImpl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BrandDao;
import com.entities.Brand;

@Repository
@Transactional
public class BrandDaoImpl extends GenericDaoImpl<Brand, Integer> implements BrandDao {

	public BrandDaoImpl(){
		super(null);
	}
	
	public BrandDaoImpl(Class<Brand> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Brand> getAvailable() {
		Query query=currentSession().createQuery("from Brand where unavailable=false");
		List<Brand> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Brand> getNotEmpty() {
		return currentSession().createQuery("from Brand where unavailable=false and models not empty").getResultList();
	}
	
}

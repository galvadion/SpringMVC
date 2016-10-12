package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BrandDao;
import com.entities.Brand;
import com.entities.User;

@Repository
@Transactional
public class BrandDaoImpl implements BrandDao {

	@Autowired
	SessionFactory session;
	
	public void saveOrUpdate(Brand brand) {
		session.getCurrentSession().saveOrUpdate(brand);
	}

	public List<Brand> list() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Brand").list();
	}

	public boolean delete(Brand brand) {
		try{
			session.getCurrentSession().delete(brand);
		}catch(Exception ex){
			return false;
		}
		return true;
	}

	public Brand getBrandById(Integer id) {
		return (Brand) session.getCurrentSession().get(Brand.class,id);
	}
	
}

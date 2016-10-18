package com.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CategoryDao;
import com.entities.Category;

@Repository
@Transactional
public class CategoryDaoImpl extends GenericDaoImpl<Category, Integer> implements CategoryDao {

	public CategoryDaoImpl(){
		super(null);
	}
	
	public CategoryDaoImpl(Class<Category> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}

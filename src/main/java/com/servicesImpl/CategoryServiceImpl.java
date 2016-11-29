package com.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.entities.Category;
import com.services.CategoryService;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Integer> implements CategoryService{

	public CategoryServiceImpl(){
		
	}
	
    @Autowired
    public CategoryServiceImpl(
            @Qualifier("categoryDaoImpl") GenericDao<Category, Integer> genericDao) {
        super(genericDao);
    }

	
}

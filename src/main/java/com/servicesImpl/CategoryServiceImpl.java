package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.CategoryDao;
import com.entities.Category;
import com.services.CategoryService;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Integer> implements CategoryService{

	private CategoryDao CategoryDao;
	public CategoryServiceImpl(){
		
	}
	
    @Autowired
    public CategoryServiceImpl(
            @Qualifier("CategoryDaoImpl") GenericDao<Category, Integer> genericDao) {
        super(genericDao);
        this.CategoryDao = (CategoryDao) genericDao;
    }

	
}

package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.ModelDao;
import com.dao.UserDao;
import com.entities.Model;
import com.entities.User;
import com.services.ModelService;
import com.services.UserServices;

@Service
public class ModelServiceImpl extends GenericServiceImpl<Model, Integer> implements ModelService{

	private ModelDao modelDao;
	public ModelServiceImpl(){
		
	}
	
    @Autowired
    public ModelServiceImpl(
            @Qualifier("modelDaoImpl") GenericDao<Model, Integer> genericDao) {
        super(genericDao);
        this.modelDao = (ModelDao) genericDao;
    }

	
}

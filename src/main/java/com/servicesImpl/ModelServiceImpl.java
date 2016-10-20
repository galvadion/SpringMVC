package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FuelDao;
import com.dao.GenericDao;
import com.dao.ModelDao;
import com.entities.Fuel;
import com.entities.Model;
import com.models.SearchFilter;
import com.services.ModelService;

@Service
public class ModelServiceImpl extends GenericServiceImpl<Model, Integer> implements ModelService{

	private ModelDao modelDao;
	public ModelServiceImpl(){
		
	}
	
    @Autowired
    public ModelServiceImpl(@Qualifier("modelDaoImpl") GenericDao<Model, Integer> genericDao) {
        super(genericDao);
        this.modelDao = (ModelDao) genericDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
	public List<Model> getModelsBetweenFilter(SearchFilter filter) {
		List<Model> models=modelDao.modelInFilter(filter);
		return models;
	}

	
}

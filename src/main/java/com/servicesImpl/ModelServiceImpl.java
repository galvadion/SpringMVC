package com.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jca.endpoint.GenericMessageEndpointManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FuelDao;
import com.dao.GenericDao;
import com.dao.ModelDao;
import com.dao.UserDao;
import com.dao.VehiculeDao;
import com.entities.Fuel;
import com.entities.Model;
import com.entities.User;
import com.entities.Vehicule;
import com.models.SearchFilter;
import com.services.ModelService;
import com.services.UserServices;

@Service
public class ModelServiceImpl extends GenericServiceImpl<Model, Integer> implements ModelService{

	private ModelDao modelDao;
	private VehiculeDao vehiculeDao;
	private FuelDao fuelDao;
	public ModelServiceImpl(){
		
	}
	
    @Autowired
    public ModelServiceImpl(@Qualifier("modelDaoImpl") GenericDao<Model, Integer> genericDao,
    		@Qualifier("fuelDaoImpl") GenericDao<Fuel, Integer> genericDao3,
    		@Qualifier("vehiculeDaoImpl") GenericDao<Vehicule, Integer> genericDao2) {
        super(genericDao);
        this.vehiculeDao = (VehiculeDao) genericDao2;
        this.modelDao = (ModelDao) genericDao;
        this.fuelDao = (FuelDao) genericDao3;
    }

    @Transactional(propagation = Propagation.REQUIRED)
	public List<Model> getModelsBetweenFilter(SearchFilter filter,boolean byFuel,int fuelId) {
    	Fuel fuel=fuelDao.getById(fuelId);
		List<Model> models=modelDao.modelInFilter(filter, fuel, byFuel);
		System.out.println(models);
		return models;
	}

	
}

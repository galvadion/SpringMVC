package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jca.endpoint.GenericMessageEndpointManager;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.ModelDao;
import com.dao.UserDao;
import com.dao.VehiculeDao;
import com.entities.Model;
import com.entities.User;
import com.entities.Vehicule;
import com.models.Filters;
import com.services.ModelService;
import com.services.UserServices;

@Service
public class ModelServiceImpl extends GenericServiceImpl<Model, Integer> implements ModelService{

	private ModelDao modelDao;
	private VehiculeDao vehiculeDao;
	public ModelServiceImpl(){
		
	}
	
    @Autowired
    public ModelServiceImpl(@Qualifier("modelDaoImpl") GenericDao<Model, Integer> genericDao,@Qualifier("vehiculeDaoImpl") GenericDao<Vehicule, Integer> genericDao2) {
        super(genericDao);
        this.vehiculeDao = (VehiculeDao) genericDao2;
        this.modelDao = (ModelDao) genericDao;
    }

	public List<Model> getModelsBetweenFilter(Filters filter) {
		
		return null;
	}

	
}

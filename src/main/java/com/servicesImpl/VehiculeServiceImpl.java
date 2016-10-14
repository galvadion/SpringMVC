package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.VehiculeDao;
import com.entities.Vehicule;
import com.services.VehiculeService;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Integer> implements VehiculeService{

	private VehiculeDao VehiculeDao;
	public VehiculeServiceImpl(){
		
	}
	
    @Autowired
    public VehiculeServiceImpl(
            @Qualifier("vehiculeDaoImpl") GenericDao<Vehicule, Integer> genericDao) {
        super(genericDao);
        this.VehiculeDao = (VehiculeDao) genericDao;
    }

	
}

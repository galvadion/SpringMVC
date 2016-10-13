package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.FuelDao;
import com.entities.Fuel;
import com.services.FuelService;

@Service
public class FuelServiceImpl extends GenericServiceImpl<Fuel, Integer> implements FuelService{

	private FuelDao FuelDao;
	public FuelServiceImpl(){
		
	}
	
    @Autowired
    public FuelServiceImpl(
            @Qualifier("FuelDaoImpl") GenericDao<Fuel, Integer> genericDao) {
        super(genericDao);
        this.FuelDao = (FuelDao) genericDao;
    }

	
}

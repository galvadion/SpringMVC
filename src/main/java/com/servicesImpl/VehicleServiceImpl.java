package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.VehicleDao;
import com.entities.Vehicle;
import com.services.VehicleService;

@Service
public class VehicleServiceImpl extends GenericServiceImpl<Vehicle, Integer> implements VehicleService{

	private VehicleDao VehicleDao;
	public VehicleServiceImpl(){
		
	}
	
    @Autowired
    public VehicleServiceImpl(
            @Qualifier("vehicleDaoImpl") GenericDao<Vehicle, Integer> genericDao) {
        super(genericDao);
        this.VehicleDao = (VehicleDao) genericDao;
    }

	public List<Vehicle> getAvailable() {
		return VehicleDao.getAvailable();
	}

	
}

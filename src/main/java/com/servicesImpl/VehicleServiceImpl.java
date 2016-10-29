package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.VehicleDao;
import com.entities.BranchOffice;
import com.entities.Vehicle;
import com.services.VehicleService;

@Service
public class VehicleServiceImpl extends GenericServiceImpl<Vehicle, Integer> implements VehicleService{

	private VehicleDao vehicleDao;
	public VehicleServiceImpl(){
		
	}
	
    @Autowired
    public VehicleServiceImpl(
            @Qualifier("vehicleDaoImpl") GenericDao<Vehicle, Integer> genericDao) {
        super(genericDao);
        this.vehicleDao = (VehicleDao) genericDao;
    }

	public List<Vehicle> getAvailable() {
		return vehicleDao.getAvailable();
	}

	public List<Vehicle> getPickedUpToday(BranchOffice bo) {
		// TODO Auto-generated method stub
		return vehicleDao.getPickedUpToday(bo);
	}
	public List<Vehicle> getReturnedToday(BranchOffice bo) {
		// TODO Auto-generated method stub
		return vehicleDao.getReturnedToday(bo);
	}
	
}

package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.ModelDao;
import com.dao.VehicleDao;
import com.dao.BrandDao;
import com.entities.Brand;
import com.entities.Model;
import com.entities.Vehicle;
import com.services.BrandService;

@Service
public class BrandServiceImpl extends GenericServiceImpl<Brand, Integer> implements BrandService{

	private BrandDao brandDao;
	private ModelDao modelDao;
	private VehicleDao vehicleDao;
	public BrandServiceImpl(){
		
	}
	
    @Autowired
    public BrandServiceImpl(
            @Qualifier("brandDaoImpl") GenericDao<Brand, Integer> genericDao,
            @Qualifier("modelDaoImpl") GenericDao<Model, Integer> genericDao2,
            @Qualifier("vehicleDaoImpl") GenericDao<Vehicle, Integer> genericDao3) {
        super(genericDao);
        this.brandDao = (BrandDao) genericDao;
        this.modelDao = (ModelDao) genericDao2;
        this.vehicleDao = (VehicleDao) genericDao3;
    }

	public void removeCascade(Brand entity) {
		for(Model model:entity.getModels()){
			model.setUnavailable(true);
			modelDao.saveOrUpdate(model);
			for(Vehicle vehicle:model.getVehicles()){
				vehicle.setUnavailable(true);
				vehicleDao.saveOrUpdate(vehicle);
			}
		}
		entity.setUnavailable(true);
		brandDao.saveOrUpdate(entity);
	}

	
}

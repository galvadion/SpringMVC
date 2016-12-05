package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BrandDao;
import com.dao.GenericDao;
import com.dao.ModelDao;
import com.dao.VehicleDao;
import com.entities.Brand;
import com.entities.Model;
import com.entities.Vehicle;
import com.models.SearchFilter;
import com.services.ModelService;

@Service
public class ModelServiceImpl extends GenericServiceImpl<Model, Integer> implements ModelService{

	private ModelDao modelDao;
	private VehicleDao vehicleDao;
	private BrandDao brandDao;
	public ModelServiceImpl(){
		
	}
	
    @Autowired
    public ModelServiceImpl(@Qualifier("modelDaoImpl") GenericDao<Model, Integer> genericDao,
    		@Qualifier("vehicleDaoImpl") GenericDao<Vehicle, Integer> genericDao2,
    		@Qualifier("brandDaoImpl") GenericDao<Brand,Integer> brandDao) {
        super(genericDao);
        this.modelDao = (ModelDao) genericDao;
        this.vehicleDao = (VehicleDao) genericDao2;
        this.brandDao =(BrandDao) brandDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
	public List<Model> getModelsBetweenFilter(SearchFilter filter) {
		List<Model> models=modelDao.modelInFilter(filter);
		return models;
	}

	public void removeCascade(Model model) {
		for(Vehicle vehicle:model.getVehicles()){
			vehicle.setUnavailable(true);
			vehicleDao.saveOrUpdate(vehicle);
		}
		model.setUnavailable(true);
		modelDao.saveOrUpdate(model);
		
	}

	public List<Model> getAvailable() {
		// TODO Auto-generated method stub
		return modelDao.getAvailable();
	}

	public List<Model> getAvailableByBrand(int parseInt) {
		
		return modelDao.getAvailableByBrand(brandDao.getById(parseInt));
	}

	
}

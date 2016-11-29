package com.servicesImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.LocationDao;
import com.entities.Location;
import com.services.LocationService;

@Service
public class LocationServiceImpl extends GenericServiceImpl<Location, Integer> implements LocationService{

	@SuppressWarnings("unused")
	private LocationDao LocationDao;
	public LocationServiceImpl(){
		
	}
	
    @Autowired
    public LocationServiceImpl(
            @Qualifier("locationDaoImpl") GenericDao<Location, Integer> genericDao) {
        super(genericDao);
        this.LocationDao = (LocationDao) genericDao;
    }

	
}

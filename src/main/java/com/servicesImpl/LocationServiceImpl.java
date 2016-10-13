package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.LocationDao;
import com.entities.Location;
import com.services.LocationService;

@Service
public class LocationServiceImpl extends GenericServiceImpl<Location, Integer> implements LocationService{

	private LocationDao LocationDao;
	public LocationServiceImpl(){
		
	}
	
    @Autowired
    public LocationServiceImpl(
            @Qualifier("LocationDaoImpl") GenericDao<Location, Integer> genericDao) {
        super(genericDao);
        this.LocationDao = (LocationDao) genericDao;
    }

	
}

package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.RentFareDao;
import com.entities.RentFare;
import com.services.RentFareService;

@Service
public class RentFareServiceImpl extends GenericServiceImpl<RentFare, Integer> implements RentFareService{

	private RentFareDao RentFareDao;
	public RentFareServiceImpl(){
		
	}
	
    @Autowired
    public RentFareServiceImpl(
            @Qualifier("rentFareDaoImpl") GenericDao<RentFare, Integer> genericDao) {
        super(genericDao);
        this.RentFareDao = (RentFareDao) genericDao;
    }

	
}

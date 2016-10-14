package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.RentDao;
import com.entities.Rent;
import com.services.RentService;

@Service
public class RentServiceImpl extends GenericServiceImpl<Rent, Integer> implements RentService{

	private RentDao RentDao;
	public RentServiceImpl(){
		
	}
	
    @Autowired
    public RentServiceImpl(
            @Qualifier("rentDaoImpl") GenericDao<Rent, Integer> genericDao) {
        super(genericDao);
        this.RentDao = (RentDao) genericDao;
    }

	
}

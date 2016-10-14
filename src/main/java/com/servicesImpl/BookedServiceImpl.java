package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.BookedDao;
import com.entities.Booked;
import com.services.BookedService;

@Service
public class BookedServiceImpl extends GenericServiceImpl<Booked, Integer> implements BookedService{

	private BookedDao BookedDao;
	public BookedServiceImpl(){
		
	}
	
    @Autowired
    public BookedServiceImpl(
            @Qualifier("BookedDaoImpl") GenericDao<Booked, Integer> genericDao) {
        super(genericDao);
        this.BookedDao = (BookedDao) genericDao;
    }

	
}

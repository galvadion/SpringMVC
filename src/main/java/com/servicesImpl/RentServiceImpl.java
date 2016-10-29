package com.servicesImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.RentDao;
import com.dao.UserDao;
import com.entities.Booked;
import com.entities.Client;
import com.entities.Rent;
import com.entities.User;
import com.services.RentService;

@Service
public class RentServiceImpl extends GenericServiceImpl<Rent, Integer> implements RentService{

	private RentDao rentDao;
	
	public RentServiceImpl(){
		
	}
	
    @Autowired
    public RentServiceImpl(
            @Qualifier("rentDaoImpl") GenericDao<Rent, Integer> genericDao) {
        super(genericDao);
        this.rentDao = (RentDao) genericDao;
    }

	public void confirmRent(Booked book) {
		Rent rent=new Rent();
		rent.setDeliveryDate(LocalDate.now());
		rent.setBooked(book);
		rent.setClient(book.getClient());
		rentDao.saveOrUpdate(rent);
		// TODO Auto-generated method stub
		
	}

	public List<Rent> getBetweenDates(LocalDate initialDate, LocalDate finalDate) {
		// TODO Auto-generated method stub
		return rentDao.getBetweenDates(initialDate,finalDate);
	}

	
}

package com.servicesImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GenericDao;
import com.dao.RentDao;
import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Promotion;
import com.entities.Rent;
import com.repository.RentRepository;
import com.services.RentService;

@Service
@Transactional
public class RentServiceImpl {

	
	@Resource(name="RentRepository")
	RentRepository rentDao;
/*	private RentDao rentDao;
	
	public RentServiceImpl(){
		
	}
	
    @Autowired
    public RentServiceImpl(
            @Qualifier("rentDaoImpl") GenericDao<Rent, Integer> genericDao) {
        super(genericDao);
        this.rentDao = (RentDao) genericDao;
    }*/
	
	
	public Rent create(Rent promo){
		promo.setId(UUID.randomUUID().toString());
		rentDao.save(promo);
		return promo;
		
	}

	public void confirmRent(Booked book) {
		Rent rent=new Rent();
		rent.setDeliveryDate(LocalDate.now());
		rent.setBooked_id(book.getId());
		rentDao.save(rent);
		// TODO Auto-generated method stub
		
	}

	public List<Rent> getBetweenDates(LocalDate initialDate, LocalDate finalDate) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

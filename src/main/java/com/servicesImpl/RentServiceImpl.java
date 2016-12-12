package com.servicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.entities.Booked;

import com.entities.Rent;
import com.repository.RentRepository;

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
		rentDao.save(promo);
		return promo;
		
	}
	
	public Rent get(String id){
		return rentDao.findById(id);
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
		return rentDao.findAll(Query.query(Criteria.where("deliveryDate").gte(initialDate).and("returnDate").lte(finalDate)));
	}

	
}

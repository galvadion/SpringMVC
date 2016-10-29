package com.daoImpl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RentDao;
import com.entities.Rent;

@Repository
@Transactional
public class RentDaoImpl extends GenericDaoImpl<Rent, Integer> implements RentDao {

	public RentDaoImpl(){
		super(null);
	}
	
	public RentDaoImpl(Class<Rent> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	public List<Rent> getBetweenDates(LocalDate initialDate, LocalDate finalDate) {
		Query query=currentSession().createQuery("from Rent where deliveryDate>:initialDate and returnDate<:finalDate");
		query.setParameter("initialDate", initialDate);
		query.setParameter("finalDate", finalDate);
		return query.getResultList();
	}
	
}

package com.daoImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BookedDao;
import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Client;
import com.entities.Model;
import com.entities.Vehicule;
import com.models.BookingModel;

@Repository
@Transactional

public class BookedDaoImpl extends GenericDaoImpl<Booked, Integer> implements BookedDao {

	public BookedDaoImpl(){
		super(null);
	}
	
	public BookedDaoImpl(Class<Booked> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	public Booked getBookedByClient(Client user) {
		Query query=currentSession().createQuery("from Booked where client =:client and :today between beginbookedDate and lastbookedDate");
		query.setParameter("client", user);
		query.setParameter("today",LocalDate.now());
		return (Booked) query.getSingleResult();
	}
	
	
}

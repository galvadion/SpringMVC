package com.daoImpl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BookedDao;
import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Client;
import com.entities.Vehicle;

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
		Query query=currentSession().createQuery("from Booked where client =:client and :today between beginbookedDate and lastbookedDate and canceled=false");
		query.setParameter("client", user);
		query.setParameter("today",LocalDate.now());
		return (Booked) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Booked> getNextBooked(Vehicle vehicle,LocalDate firstDate) {
		Query query=currentSession().createQuery("from Booked where vehicle =:vehicle and beginbookedDate>:Date and canceled=false");
		query.setParameter("vehicle", vehicle);
		query.setParameter("Date", firstDate);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booked> getDelivered(BranchOffice branch, LocalDate date) {
		Query query=currentSession().createQuery("from Booked where beginbookedDate=:date and canceled=false and originOffice=:office");
		query.setParameter("office", branch);
		query.setParameter("date",date);
		return query.getResultList();
	}

	@Override
	public List<Booked> getReturned(BranchOffice branch, LocalDate date) {
		Query query=currentSession().createQuery("from Booked where lastbookedDate=:date and canceled=false and endOffice=:office");
		query.setParameter("office", branch);
		query.setParameter("date",date);
		return query.getResultList();
	}

	@Override
	public List<Booked> getReturned(LocalDate date) {
		Query query=currentSession().createQuery("from Booked where lastbookedDate=:date and canceled=false");
		query.setParameter("date",date);
		return query.getResultList();
	}

	@Override
	public List<Booked> getDelivered(LocalDate date) {
		Query query=currentSession().createQuery("from Booked where beginbookedDate=:date and canceled=false");
		query.setParameter("date",date);
		return query.getResultList();
	}
	
	public List<Booked> getBookedByClientList(Client user) {
		Query query=currentSession().createQuery("from Booked where client =:client ");
		query.setParameter("client", user);
		return  query.getResultList();
	}
	
}

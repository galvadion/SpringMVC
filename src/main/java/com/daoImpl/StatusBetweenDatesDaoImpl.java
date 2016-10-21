package com.daoImpl;

import java.time.LocalDate;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.StatusBetweenDatesDao;
import com.entities.BranchOffice;
import com.entities.StatusBetweenDates;
import com.entities.Vehicle;
import com.models.Vehicle_Status;

/**
 * @author root
 *
 */
@Repository
@Transactional
public class StatusBetweenDatesDaoImpl extends GenericDaoImpl<StatusBetweenDates, Integer> implements StatusBetweenDatesDao {

	public StatusBetweenDatesDaoImpl(){
		super(null);
	}
	
	public StatusBetweenDatesDaoImpl(Class<StatusBetweenDates> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	
	public void editStatusAtBooking(Vehicle vehicle, LocalDate initialDate, LocalDate endDate,BranchOffice finalBranchOffice) {
		Query query=currentSession().createQuery("Select s from StatusBetweenDates s where s.vehicle =:vehicule and :beginDate >= s.beginDate and :endDate <= s.endDate");
		query.setParameter("beginDate", initialDate);
		query.setParameter("endDate", endDate);
		query.setParameter("vehicule", vehicle);
		StatusBetweenDates status=(StatusBetweenDates) query.getSingleResult();
		status.setEndDate(initialDate);
		System.out.println(status);
		this.saveOrUpdate(status);
		StatusBetweenDates booked=new StatusBetweenDates();
		booked.setBeginDate(initialDate);
		LocalDate finalDate=endDate.plusDays(5);
		booked.setEndDate(finalDate);
		booked.setStatus(Vehicle_Status.Unavailable);
		booked.setVehicle(vehicle);
		booked.setBranchOffice(finalBranchOffice);
		System.out.println(booked);
		this.saveOrUpdate(booked);
		StatusBetweenDates newDisponibility=new StatusBetweenDates();
		newDisponibility.setBeginDate(finalDate);
		newDisponibility.setEndDate(finalDate.plusYears(10));
		newDisponibility.setBranchOffice(finalBranchOffice);
		newDisponibility.setStatus(Vehicle_Status.Available);
		newDisponibility.setVehicle(vehicle);
		System.out.println(newDisponibility);
		this.saveOrUpdate(newDisponibility);
	}

	


}

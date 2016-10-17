package com.daoImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FuelDao;
import com.dao.ModelDao;
import com.dao.StatusBetweenDatesDao;
import com.entities.BranchOffice;
import com.entities.Fuel;
import com.entities.Model;
import com.entities.StatusBetweenDates;
import com.entities.Vehicule;
import com.models.SearchFilter;
import com.models.Vehicule_Status;
import com.entities.Model;

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

	
	public void editStatusAtBooking(Vehicule vehicule, LocalDate initialDate, LocalDate endDate,BranchOffice finalBranchOffice) {
		Query query=currentSession().createQuery("Select s from StatusBetweenDates s where s.vehicule =:vehicule and :beginDate >= s.beginDate and :endDate <= s.endDate");
		query.setParameter("beginDate", initialDate);
		query.setParameter("endDate", endDate);
		query.setParameter("vehicule", vehicule);
		StatusBetweenDates status=(StatusBetweenDates) query.getSingleResult();
		status.setEndDate(initialDate);
		System.out.println(status);
		this.saveOrUpdate(status);
		StatusBetweenDates booked=new StatusBetweenDates();
		booked.setBeginDate(initialDate);
		LocalDate finalDate=endDate.plusDays(5);
		booked.setEndDate(finalDate);
		booked.setStatus(Vehicule_Status.Unavailable);
		booked.setVehicule(vehicule);
		booked.setBranchOffice(finalBranchOffice);
		System.out.println(booked);
		this.saveOrUpdate(booked);
		StatusBetweenDates newDisponibility=new StatusBetweenDates();
		newDisponibility.setBeginDate(finalDate);
		newDisponibility.setEndDate(finalDate.plusYears(10));
		newDisponibility.setBranchOffice(finalBranchOffice);
		newDisponibility.setStatus(Vehicule_Status.Available);
		newDisponibility.setVehicule(vehicule);
		System.out.println(newDisponibility);
		this.saveOrUpdate(newDisponibility);
	}

	


}

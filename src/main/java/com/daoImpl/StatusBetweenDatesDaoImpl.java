package com.daoImpl;

import java.time.LocalDate;
import java.util.List;

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

	
	public void editStatusAtBooking(Vehicle vehicle, LocalDate initialDate, LocalDate endDate,BranchOffice finalBranchOffice,BranchOffice originBO) {
		Query query=currentSession().createQuery("Select s from StatusBetweenDates s where s.vehicle =:vehicule and :beginDate >= s.beginDate and :endDate <= s.endDate");
		query.setParameter("beginDate", initialDate);
		query.setParameter("endDate", endDate);
		query.setParameter("vehicule", vehicle);
		StatusBetweenDates status=(StatusBetweenDates) query.getSingleResult();
		status.setEndDate(initialDate);
		this.saveOrUpdate(status);
		StatusBetweenDates booked=new StatusBetweenDates();
		booked.setBeginDate(initialDate);
		LocalDate finalDate=endDate.plusDays(5);
		booked.setEndDate(finalDate);
		booked.setStatus(Vehicle_Status.Booked);
		booked.setVehicle(vehicle);
		booked.setBranchOfficeEnd(finalBranchOffice);
		booked.setBranchOffice(originBO);
		this.saveOrUpdate(booked);
		StatusBetweenDates newDisponibility=new StatusBetweenDates();
		newDisponibility.setBeginDate(finalDate);
		newDisponibility.setEndDate(finalDate.plusYears(10));
		newDisponibility.setBranchOffice(finalBranchOffice);
		newDisponibility.setStatus(Vehicle_Status.Available);
		newDisponibility.setVehicle(vehicle);
		this.saveOrUpdate(newDisponibility);
	}

	
	public StatusBetweenDates getActualStatus(Vehicle vehicle,LocalDate date){
		Query query=currentSession().createQuery("Select s from StatusBetweenDates s where s.vehicle =:vehicle and :date between s.beginDate and s.endDate");
		query.setParameter("date", date);
		query.setParameter("vehicle", vehicle);
		return (StatusBetweenDates) query.getSingleResult();
		
	}

	@SuppressWarnings("unchecked")
	public List<StatusBetweenDates> getNextStatus(Vehicle vehicle,LocalDate date) {
		Query query=currentSession().createQuery("Select s from StatusBetweenDates s where s.vehicle =:vehicle and s.beginDate > :date");
		query.setParameter("vehicle", vehicle);
		query.setParameter("date",date);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<StatusBetweenDates> getStatusList(Vehicle byId) {
		Query query=currentSession().createQuery("Select s from StatusBetweenDates s where s.vehicle =:vehicle and s.status != :status");
		query.setParameter("vehicle", byId);
		query.setParameter("status", Vehicle_Status.Available);
		return query.getResultList();
	}

}

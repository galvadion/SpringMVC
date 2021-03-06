package com.daoImpl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.VehicleDao;
import com.entities.BranchOffice;
import com.entities.Vehicle;
import com.models.BookingModel;
import com.models.Vehicle_Status;

@Repository
@Transactional
public class VehicleDaoImpl extends GenericDaoImpl<Vehicle, Integer> implements VehicleDao {

	public VehicleDaoImpl(){
		super(null);
	}
	public VehicleDaoImpl(Class<Vehicle> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	public Vehicle getVehiculeAvailable(BookingModel model) {
		
		Query query=currentSession().createQuery("Select v from Vehicle v join v.model m join v.status s where m.id=:modelId and v.unavailable=false and s.id in (Select s.id from StatusBetweenDates s join s.branchOffice b where :beginDate >= s.beginDate and :endDate <= s.endDate and s.status =:status and b.id =:branchId)");
		query.setParameter("modelId", model.getIdModel());
		query.setParameter("beginDate", model.getStartDate());
		query.setParameter("endDate", model.getEndDate());
		query.setParameter("status", Vehicle_Status.Available);
		query.setParameter("branchId", model.getOriginBranchOfficeId());
		return (Vehicle) query.getResultList().get(0);
	}
	@SuppressWarnings("unchecked")
	public List<Vehicle> getAvailable() {
		// TODO Auto-generated method stub
		return currentSession().createQuery("from Vehicle where unavailable=false").getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Vehicle> getPickedUpToday(BranchOffice bo) {
		// TODO Auto-generated method stub
		Query query=currentSession().createQuery("Select v from Vehicle v join v.booked s where v.unavailable=false and s.id in (Select s.id from Booked s where s.endOffice =:branchOffice  and s.beginbookedDate=CURRENT_DATE)");
		query.setParameter("branchOffice", bo);
		return query.getResultList();	
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> getReturnedToday(BranchOffice bo) {
		// TODO Auto-generated method stub
		Query query=currentSession().createQuery("Select v from Vehicle v join v.booked s where v.unavailable=false and s.id in (Select s.id from Booked s where s.originOffice =:branchOffice  and s.lastbookedDate=CURRENT_DATE)");
		query.setParameter("branchOffice", bo);
		return query.getResultList();
	}
	
}

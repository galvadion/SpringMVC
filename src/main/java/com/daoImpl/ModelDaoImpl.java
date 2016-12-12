package com.daoImpl;

import java.util.List;

import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ModelDao;
import com.entities.Brand;
import com.entities.Model;
import com.models.SearchFilter;
import com.models.Vehicle_Status;

@Repository
@Transactional
public class ModelDaoImpl extends GenericDaoImpl<Model, Integer> implements ModelDao {

	public ModelDaoImpl(){
		super(null);
	}
	
	public ModelDaoImpl(Class<Model> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	public List<Model> modelInFilter(SearchFilter filter) {
		Query query=currentSession().createQuery("Select distinct m from Model m join m.vehicles v join v.status s where m.unavailable=false and v.unavailable=false and m.passangers>= :passangers and m.luggage >= :luggage and m.airConditioner = :air and v.licensePlateExpirationDate > :endDate and s.id in (Select s.id from StatusBetweenDates s join s.branchOffice b where :beginDate >= s.beginDate and :endDate <= s.endDate and s.status =:status and b.id =:branchId)");
		query.setParameter("passangers",filter.getPassangers());
		query.setParameter("luggage", filter.getLuggage());
		query.setParameter("air",filter.isAirConditioner());
		query.setParameter("beginDate", filter.getBeginDate());
		query.setParameter("endDate", filter.getEndDate());
		query.setParameter("status", Vehicle_Status.Available);
		query.setParameter("branchId", filter.getOfficeOriginId());
		//System.out.println(filter.getEndDate());
		@SuppressWarnings("unchecked")
		List<Model> resultList = (List<Model>) query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Model> getAvailable() {
		// TODO Auto-generated method stub
		return currentSession().createQuery("from Model where unavailable=false").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Model> getAvailableByBrand(Brand byId) {
		Query query=currentSession().createQuery("from Model where unavailable=false and brand=:brand");
		query.setParameter("brand", byId);
		return query.getResultList();
	}

	


}

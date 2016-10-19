package com.daoImpl;

import java.util.List;

import javax.persistence.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FuelDao;
import com.dao.ModelDao;
import com.entities.Fuel;
import com.entities.Model;
import com.models.SearchFilter;
import com.models.Vehicle_Status;
import com.entities.Model;

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
		Query query=currentSession().createQuery("Select distinct m from Model m join m.vehicules v join v.status s where passangers>= :passangers and luggage >= :luggage and airConditioner = :air and s.id in (Select s.id from StatusBetweenDates s join s.branchOffice b where :beginDate >= s.beginDate and :endDate <= s.endDate and s.status =:status and b.id =:branchId)");
		query.setParameter("passangers",filter.getPassangers());
		query.setParameter("luggage", filter.getLuggage());
		query.setParameter("air",filter.isAirConditioner());
		query.setParameter("beginDate", filter.getBeginDate());
		query.setParameter("endDate", filter.getEndDate());
		query.setParameter("status", Vehicle_Status.Available);
		query.setParameter("branchId", filter.getBranchId());
		//System.out.println(filter.getEndDate());
		List<Model> resultList = (List<Model>) query.getResultList();
		return resultList;
	}
	


}

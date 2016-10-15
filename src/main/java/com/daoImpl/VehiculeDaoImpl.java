package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.VehiculeDao;
import com.entities.Vehicule;
import com.models.Filters;
import com.models.Vehicule_Status;

@Repository
@Transactional
public class VehiculeDaoImpl extends GenericDaoImpl<Vehicule, Integer> implements VehiculeDao {

	public VehiculeDaoImpl(){
		super(null);
	}
	public VehiculeDaoImpl(Class<Vehicule> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	public List<Vehicule> vehiculesAvailablesInDates(Filters filter) {
		Query query=currentSession().createQuery("from Vehicule v where v.StatusBetweenDates.id not in (Select s.id from StatusBetweenDates s where :beginDate between s.beginDate and s.endDate =:endDate and :endDate between s.beginDate and s.endDate =:endDate)");
		query.setParameter("beginDate", filter.getBeginDate());
		query.setParameter("endDate", filter.getEndDate());
		//query.setParameter("status", Vehicule_Status.Available);
		return query.list();
	}

	
}

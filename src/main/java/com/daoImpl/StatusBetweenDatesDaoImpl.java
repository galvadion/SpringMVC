package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FuelDao;
import com.dao.ModelDao;
import com.dao.StatusBetweenDatesDao;
import com.entities.Fuel;
import com.entities.Model;
import com.entities.StatusBetweenDates;
import com.models.Filters;
import com.entities.Model;

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

	public List<Model> modelsInDates(Filters filter, Fuel fuel, boolean byFuel) {
		String transqry= "",fuelqry="";
		if(!filter.getTransmission().equals("-")){
			transqry=" And transmission = :transmission ";
		}if(byFuel){
			fuelqry=" And fuel =: fuel";
		}
		Query query=currentSession().createQuery("from Model where passangers>= :passangers and luggage >= :luggage and airConditioner = :air "+transqry+fuelqry);
		query.setParameter("passangers",filter.getPassangers());
		query.setParameter("luggage", filter.getLuggage());
		query.setParameter("air",filter.isAirConditioner());
		if(!filter.getTransmission().equals("-")){
			query.setParameter("transmission", filter.getTransmission());
		}if(byFuel){
			query.setParameter("fuel", fuel);
		}
		return query.list();
	}
	


}

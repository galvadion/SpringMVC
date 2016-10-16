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
import com.models.SearchFilter;
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

	


}

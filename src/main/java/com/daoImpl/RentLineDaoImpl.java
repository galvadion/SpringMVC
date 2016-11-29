package com.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RentLineDao;
import com.entities.RentLine;

@Repository
@Transactional
public class RentLineDaoImpl extends GenericDaoImpl<RentLine, Integer> implements RentLineDao {

	public RentLineDaoImpl(){
		super(null);
	}
	
	public RentLineDaoImpl(Class<RentLine> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}

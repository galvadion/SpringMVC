package com.daoImpl;

import java.time.LocalDate;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BranchOfficeDao;
import com.entities.BranchOffice;

@Repository
@Transactional
public class BranchOfficeDaoImpl extends GenericDaoImpl<BranchOffice, Integer> implements BranchOfficeDao {

	public BranchOfficeDaoImpl(){
		super(null);
	}
	
	public BranchOfficeDaoImpl(Class<BranchOffice> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	public boolean expectingArrivals(BranchOffice branch) {
		Query query=currentSession().createQuery("select v from B where client =:client and :today between beginbookedDate and lastbookedDate and canceled=false");
		query.setParameter("branch", branch);
		query.setParameter("today",LocalDate.now());
		return false;
	}
	
}

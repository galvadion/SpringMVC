package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
}

package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.BranchOfficeDao;
import com.entities.BranchOffice;
import com.services.BranchOfficeService;

@Service
public class BranchOfficeServiceImpl extends GenericServiceImpl<BranchOffice, Integer> implements BranchOfficeService{

	private BranchOfficeDao branchOfficeDao;
	public BranchOfficeServiceImpl(){
		
	}
	
    @Autowired
    public BranchOfficeServiceImpl(
            @Qualifier("branchOfficeDaoImpl") GenericDao<BranchOffice, Integer> genericDao) {
        super(genericDao);
        this.branchOfficeDao = (BranchOfficeDao) genericDao;
    }

	public void closeBranchOffice(BranchOffice entity) throws Exception {
		if(!entity.getVehicles().isEmpty())	throw new Exception("You cant close this branch if you have vehicles registered in it");
		if(branchOfficeDao.expectingArrivals(entity)) throw new Exception("You are expecting vehicles");
		entity.setClosed(true);
		branchOfficeDao.saveOrUpdate(entity);
	}

	public List<BranchOffice> getAvailable() {
		// TODO Auto-generated method stub
		return branchOfficeDao.getAvailable();
	}

	
}

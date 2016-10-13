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

	private BranchOfficeDao BranchOfficeDao;
	public BranchOfficeServiceImpl(){
		
	}
	
    @Autowired
    public BranchOfficeServiceImpl(
            @Qualifier("BranchOfficeDaoImpl") GenericDao<BranchOffice, Integer> genericDao) {
        super(genericDao);
        this.BranchOfficeDao = (BranchOfficeDao) genericDao;
    }

	
}

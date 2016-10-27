package com.services;

import java.util.List;

import com.entities.BranchOffice;

public interface BranchOfficeService extends GenericService<BranchOffice,Integer> {

	public void closeBranchOffice(BranchOffice entity)throws Exception ;

}

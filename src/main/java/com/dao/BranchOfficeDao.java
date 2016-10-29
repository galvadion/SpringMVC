package com.dao;

import java.util.List;

import com.entities.BranchOffice;

public interface BranchOfficeDao extends GenericDao<BranchOffice, Integer> {

	public boolean expectingArrivals(BranchOffice branch);

	public List<BranchOffice> getAvailable();

}

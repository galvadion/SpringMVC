package com.dao;

import java.time.LocalDate;
import java.util.List;

import com.entities.BranchOffice;
import com.entities.Rent;

public interface RentDao extends GenericDao<Rent, Integer> {

	List<Rent> getBetweenDates(LocalDate initialDate, LocalDate finalDate);

	List<Rent> getReturnedToday(BranchOffice branch, LocalDate day);
}

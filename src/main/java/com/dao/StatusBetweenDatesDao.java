package com.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.entities.BranchOffice;
import com.entities.Fuel;
import com.entities.Model;
import com.entities.StatusBetweenDates;
import com.entities.Vehicule;
import com.models.SearchFilter;

public interface StatusBetweenDatesDao extends GenericDao<StatusBetweenDates, Integer> {
	public void editStatusAtBooking(Vehicule vehicule,LocalDate initialDate,LocalDate endDate,BranchOffice finalBranchOffice);
}

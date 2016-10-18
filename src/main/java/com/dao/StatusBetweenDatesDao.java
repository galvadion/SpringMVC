package com.dao;

import java.time.LocalDate;


import com.entities.BranchOffice;
import com.entities.StatusBetweenDates;
import com.entities.Vehicle;

/**
 * @author root
 *
 */
public interface StatusBetweenDatesDao extends GenericDao<StatusBetweenDates, Integer> {
	
	/**
	 * @Vehicule the vehicules that is being booked
	 * @initialDate the date of the booking
	 * @endDate the last Date of the booking
	 *
	 */
	public void editStatusAtBooking(Vehicle vehicle,LocalDate initialDate,LocalDate endDate,BranchOffice finalBranchOffice);
}

package com.dao;

import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Model;
import com.entities.Vehicule;
import com.models.BookingModel;

public interface BookedDao extends GenericDao<Booked, Integer> {
	public void generateBooking(BookingModel model,Vehicule vehicule,BranchOffice originOffice,BranchOffice finalOffice);
}

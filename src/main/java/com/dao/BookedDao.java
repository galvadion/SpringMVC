package com.dao;

import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Client;
import com.entities.Model;
import com.entities.Vehicule;
import com.models.BookingModel;

public interface BookedDao extends GenericDao<Booked, Integer> {

	public Booked getBookedByClient(Client user);
}

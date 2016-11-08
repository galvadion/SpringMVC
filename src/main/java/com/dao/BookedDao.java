package com.dao;

import java.util.List;

import com.entities.Booked;
import com.entities.Client;
import com.entities.Vehicle;

public interface BookedDao extends GenericDao<Booked, Integer> {

	public Booked getBookedByClient(Client user);

	public List<Booked> getNextBooked(Vehicle vehicle);



}

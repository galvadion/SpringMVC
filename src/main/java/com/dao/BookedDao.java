package com.dao;

import java.util.List;

import com.entities.Booked;
import com.entities.Client;

public interface BookedDao extends GenericDao<Booked, Integer> {

	public Booked getBookedByClient(Client user);



}

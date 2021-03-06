package com.dao;

import java.time.LocalDate;
import java.util.List;

import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Client;
import com.entities.Vehicle;

public interface BookedDao extends GenericDao<Booked, Integer> {

	public Booked getBookedByClient(Client user);

	public List<Booked> getNextBooked(Vehicle vehicle,LocalDate date);

	public List<Booked> getDelivered(BranchOffice branch, LocalDate date);

	public List<Booked> getReturned(BranchOffice branch, LocalDate date);

	public List<Booked> getReturned(LocalDate date);

	public List<Booked> getDelivered(LocalDate date);

	public List<Booked> getBookedByClientList(Client client);



}

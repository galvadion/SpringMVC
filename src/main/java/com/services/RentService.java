package com.services;

import java.time.LocalDate;
import java.util.List;

import com.entities.Booked;
import com.entities.Client;
import com.entities.Rent;

public interface RentService extends GenericService<Rent,Integer> {
	public void confirmRent(Booked book);

	public List<Rent> getBetweenDates(LocalDate initialDate, LocalDate finalDate);
}

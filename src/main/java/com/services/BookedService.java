package com.services;


import com.entities.Booked;
import com.entities.Client;
import com.models.BookingModel;

public interface BookedService extends GenericService<Booked,Integer> {
	public void registerBoot(BookingModel model,Client client);

	public Booked getBookedByClient(Client user);
}

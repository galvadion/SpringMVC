package com.services;

import java.util.List;

import com.entities.Booked;
import com.entities.Client;
import com.entities.User;
import com.models.BookingModel;

public interface BookedService extends GenericService<Booked,Integer> {
	public void registerBook(BookingModel model,Client client, String transactionId, String payerId);

	public Booked getBookedByClient(Client user);
}

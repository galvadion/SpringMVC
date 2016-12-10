package com.services;


import java.time.LocalDate;
import java.util.List;

import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Client;
import com.entities.Extras;
import com.models.BookingModel;

public interface BookedService extends GenericService<Booked,Integer> {
	public void registerBook(BookingModel model,Client client, String transactionId, String payerId, List<Extras> extras);

	public Booked getBookedByClient(Client user);
	
	public List<Booked> getBookedByDayAndOffice(BranchOffice branch,LocalDate date);

	public List<Booked> getReturnedToday(BranchOffice branch, LocalDate date);
}

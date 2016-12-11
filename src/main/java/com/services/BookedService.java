package com.services;


import java.time.LocalDate;
import java.util.List;

import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Client;
import com.models.BookingModel;

public interface BookedService extends GenericService<Booked,Integer> {
	public void registerBoot(BookingModel model,Client client);

	public Booked getBookedByClient(Client user);
	
	public List<Booked> getBookedByDayAndOffice(BranchOffice branch,LocalDate date);

	public List<Booked> getReturnedToday(BranchOffice branch, LocalDate date);

	public List<Booked> getReturnedToday(LocalDate date);
	
	public List<Booked> getDeliveredByDay(LocalDate date);

}

package com.services;

import java.util.List;

import com.entities.Booked;
import com.entities.StatusBetweenDates;
import com.models.BookingModel;

public interface StatusBetweenDatesService extends GenericService<StatusBetweenDates,Integer> {
	public void editStatusAtBooked(BookingModel model);
}

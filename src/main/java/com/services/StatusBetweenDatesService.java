package com.services;

import java.time.LocalDate;
import java.util.List;

import com.entities.StatusBetweenDates;
import com.entities.Vehicle;
import com.models.BookingModel;

public interface StatusBetweenDatesService extends GenericService<StatusBetweenDates,Integer> {
	public void editStatusAtBooked(BookingModel model);

	public StatusBetweenDates getCurrentStatus(Vehicle vehicle, LocalDate firstDate);

	public List<StatusBetweenDates> getStatusOfVehicle(Integer id);
}

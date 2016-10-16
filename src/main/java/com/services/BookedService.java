package com.services;

import java.util.List;

import com.entities.Booked;
import com.models.BookingModel;

public interface BookedService extends GenericService<Booked,Integer> {
	public void registerBoot(BookingModel model);
}

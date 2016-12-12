package com.models;

import com.entities.Booked;
import com.entities.Rent;

public class PickedModel {
	private Rent rent;
	private Booked booked;
	public Rent getRent() {
		return rent;
	}
	public void setRent(Rent rent) {
		this.rent = rent;
	}
	public Booked getBooked() {
		return booked;
	}
	public void setBooked(Booked booked) {
		this.booked = booked;
	}
	
	
	
}

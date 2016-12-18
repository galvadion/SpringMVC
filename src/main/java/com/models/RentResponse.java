package com.models;

import com.entities.Booked;
import com.entities.Rent;

public class RentResponse {
	private Rent rent;
	private Booked book;
	public Rent getRent() {
		return rent;
	}
	public void setRent(Rent rent) {
		this.rent = rent;
	}
	public Booked getBook() {
		return book;
	}
	public void setBook(Booked book) {
		this.book = book;
	}
	
	
}

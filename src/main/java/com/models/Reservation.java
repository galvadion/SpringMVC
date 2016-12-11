package com.models;

import java.io.Serializable;
import java.util.List;

import com.entities.Extras;

public class Reservation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private BookingModel bookingModel;
	
	private String token;
	
	private String payerId;
	
	private String itemTotal;
	
	private String orderTotal;
	
	private int clientId;
	
	private List<Extras> extras;

	public BookingModel getBookingModel() {
		return bookingModel;
	}

	public void setBookingModel(BookingModel bookingModel) {
		this.bookingModel = bookingModel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(String itemTotal) {
		this.itemTotal = itemTotal;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public List<Extras> getExtras() {
		return extras;
	}
	public void setExtas(List<Extras> extas) {
		this.extras = extas;
	}
	
}

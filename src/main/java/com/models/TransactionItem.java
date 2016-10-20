package com.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class TransactionItem {

	private String descripcion;
	private Double amount;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}

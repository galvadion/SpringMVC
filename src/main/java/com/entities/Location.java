package com.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
	private String coordinateX;
	private String coordinateY;
	public String getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}
	public String getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}
	
	
}

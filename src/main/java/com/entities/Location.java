package com.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
	private String longitude;
	private String latitude;
	public String getlongitude() {
		return longitude;
	}
	public void setlongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getlatitude() {
		return latitude;
	}
	public void setlatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
}

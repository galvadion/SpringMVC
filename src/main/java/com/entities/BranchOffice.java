package com.entities;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "branch_offices")
public class BranchOffice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	private String name;
	
	@Embedded
	private Location location;
	
	private String city;
	
	private String address;
	
	@Column(name= "aperture_hour")
	private LocalTime apertureHour;
	
	@Column(name= "closing_hour")
	private LocalTime closingHour;
	
	@OneToMany(mappedBy="originOffice")
	private List<Booked> BookedEndsLists;
	
	@OneToMany(mappedBy="endOffice")
	private List<Booked> BookedOriginsLists;
	
	@OneToMany(mappedBy="branchOffice")
	private List<Vehicle> vehicles;

	@OneToMany(mappedBy="branchOffice")
	private List<StatusBetweenDates> statusDates;

	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalTime getApertureHour() {
		return apertureHour;
	}

	public void setApertureHour(LocalTime apertureHour) {
		this.apertureHour = apertureHour;
	}

	public LocalTime getClosingHour() {
		return closingHour;
	}

	public void setClosingHour(LocalTime closingHour) {
		this.closingHour = closingHour;
	}

	

	public List<Booked> getBookedEndsLists() {
		return BookedEndsLists;
	}

	public void setBookedEndsLists(List<Booked> bookedEndsLists) {
		BookedEndsLists = bookedEndsLists;
	}

	public List<Booked> getBookedOriginsLists() {
		return BookedOriginsLists;
	}

	public void setBookedOriginsLists(List<Booked> bookedOriginsLists) {
		BookedOriginsLists = bookedOriginsLists;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<StatusBetweenDates> getStatusDates() {
		return statusDates;
	}

	public void setStatusDates(List<StatusBetweenDates> originStatus) {
		this.statusDates = originStatus;
	}

	
	
}

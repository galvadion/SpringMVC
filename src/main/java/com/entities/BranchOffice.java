package com.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BranchOffice {

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
	@Temporal(TemporalType.TIME)
	private Date apertureHour;
	
	@Column(name= "closing_hour")
	@Temporal(TemporalType.TIME)
	private Date closingHour;
	
	@OneToMany(mappedBy="originBranchOffice")
	private List<Rent> rentEndsLists;
	
	@OneToMany(mappedBy="endBranchOffice")
	private List<Rent> rentOriginsLists;
	
	@OneToMany(mappedBy="branchOffice")
	private List<Vehicule> vehicules;

	
	
	public List<Vehicule> getVehicules() {
		return vehicules;
	}

	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
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

	public Date getApertureHour() {
		return apertureHour;
	}

	public void setApertureHour(Date apertureHour) {
		this.apertureHour = apertureHour;
	}

	public Date getClosingHour() {
		return closingHour;
	}

	public void setClosingHour(Date closingHour) {
		this.closingHour = closingHour;
	}

	public List<Rent> getRentEndsLists() {
		return rentEndsLists;
	}

	public void setRentEndsLists(List<Rent> rentEndsLists) {
		this.rentEndsLists = rentEndsLists;
	}

	public List<Rent> getRentOriginsLists() {
		return rentOriginsLists;
	}

	public void setRentOriginsLists(List<Rent> rentOriginsLists) {
		this.rentOriginsLists = rentOriginsLists;
	}
	
	
}

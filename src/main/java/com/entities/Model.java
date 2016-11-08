package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@NotNull(message = "The name can't be null")
	@NotEmpty(message = "The name can't be empty")
	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "fuel_id")
	private Fuel fuel;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private int year;

	@Min(value = 2, message = "A vehicle has to have room for at least two passangers")
	private int passangers;

	@Min(value = 1, message = "A vehicle has to have room for at least one luggage piece")
	private int luggage;

	private int cylinders;

	private boolean airConditioner;
	
	private boolean unavailable=false;
	
	@Transient
	private int vehicleCount;

	public int getVehicleCount() {
		return vehicles.size();
	}

	@OneToMany(mappedBy = "model")
	private List<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "model")
	private List<Image> image;

	private String transmission;

	@Min(value = 0, message = "The inssurance can't be less than zero")
	private Float insurance;

	@Min(value = 0, message = "The cost of full tank can't be less than zero")
	@Column(name = "full_tank")
	private Float fullTank;

	public Brand getBrand() {
		return brand;
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

	public int getYear() {
		return year;
	}

	public void setYear(int ano) {
		this.year = ano;
	}

	public int getPassangers() {
		return passangers;
	}

	public void setPassangers(int passangers) {
		this.passangers = passangers;
	}

	public int getLuggage() {
		return luggage;
	}

	public void setLuggage(int luggage) {
		this.luggage = luggage;
	}

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}

	public boolean isAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(boolean airConditioner) {
		this.airConditioner = airConditioner;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Float getInsurance() {
		return insurance;
	}

	public void setInsurance(Float insurance) {
		this.insurance = insurance;
	}

	public Float getFullTank() {
		return fullTank;
	}

	public void setFullTank(Float fullTank) {
		this.fullTank = fullTank;
	}

	@AssertTrue(message="The transmission must be M or A")
	private boolean isValid() {
		return transmission.equals("M") || transmission.equals("A");
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", name=" + name + ", brand=" + brand + ", fuel=" + fuel + ", category=" + category
				+ ", year=" + year + ", passangers=" + passangers + ", luggage=" + luggage + ", cylinders=" + cylinders
				+ ", airConditioner=" + airConditioner + ", vehicles=" + vehicles + ", transmission=" + transmission
				+ ", insurance=" + insurance + ", fullTank=" + fullTank + "]";
	}

	public boolean isUnavailable() {
		return unavailable;
	}

	public void setUnavailable(boolean unavailable) {
		this.unavailable = unavailable;
	}

	public List<Image> getImages() {
		return image;
	}

	public void setImages(List<Image> image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setVehicleCount(int vehicleCount) {
		this.vehicleCount = vehicleCount;
	}
	
	

}

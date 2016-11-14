package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@NotNull(message="The name can't be null")
	@NotEmpty(message="The name can't be empty")
	@Column(name = "name",unique =true)
	private String name;
	
	@Min(value=0, message="The value can't be negative")
	@Column(name="base_price")
	private float basePrice;
	
	private boolean unavailable=false;

	@OneToMany(mappedBy="category")
	public List<Model> Models;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getBasePrice() {
		return basePrice;
	}

	
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public List<Model> getModels() {
		return Models;
	}

	public void setModels(List<Model> Models) {
		this.Models = Models;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", basePrice=" + basePrice + ", Models=" + Models + "]";
	}

	public boolean isUnavailable() {
		return unavailable;
	}

	public void setUnavailable(boolean unavailable) {
		this.unavailable = unavailable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

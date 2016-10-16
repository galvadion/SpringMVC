package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@Column(name="base_price")
	private float basePrice;

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
	
	
}

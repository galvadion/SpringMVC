package com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Employee extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}

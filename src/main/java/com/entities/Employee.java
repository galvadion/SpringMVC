package com.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee extends User {

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}

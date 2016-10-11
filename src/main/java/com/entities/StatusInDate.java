package com.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class StatusInDate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name= "begin_status_date")
	@Temporal(TemporalType.DATE)
	private Date beginStatusDate;
	
	@Column(name= "last_status_date")
	@Temporal(TemporalType.DATE)
	private Date lastStatusDate;
	
	@ManyToOne
	@JoinColumn(name="vehicule_id", nullable=false)
	private Vehicule vehicule;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBeginStatusDate() {
		return beginStatusDate;
	}

	public void setBeginStatusDate(Date beginStatusDate) {
		this.beginStatusDate = beginStatusDate;
	}

	public Date getLastStatusDate() {
		return lastStatusDate;
	}

	public void setLastStatusDate(Date lastStatusDate) {
		this.lastStatusDate = lastStatusDate;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	
	
}

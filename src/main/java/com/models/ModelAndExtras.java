package com.models;

import java.util.List;

import com.entities.Extras;
import com.entities.Model;

public class ModelAndExtras {
	
	private static final long serialVersionUID = 1L;

	private Model model;
	
	private List<Extras> extras;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public List<Extras> getExtras() {
		return extras;
	}

	public void setExtras(List<Extras> extras) {
		this.extras = extras;
	}
	
}

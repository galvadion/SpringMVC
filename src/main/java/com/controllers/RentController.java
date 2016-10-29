package com.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Booked;
import com.entities.Client;
import com.entities.Rent;
import com.services.BookedService;
import com.services.RentService;
import com.services.UserServices;


@Controller
@RequestMapping(value = "rent")
public class RentController {
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	RentService rentServices;
	
	@Autowired
	BookedService bookedServices;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("rent/list");
		return view;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView getViewPage() {
		ModelAndView view = new ModelAndView("rent/form");
		return view;
	}
	
	@RequestMapping(value = "/getBooked", method = RequestMethod.GET)
	private ResponseEntity<List<Booked>> getRentByName(@RequestParam("id") String id){
		Client client=(Client) userServices.get(Integer.parseInt(id));
		return ResponseEntity.ok(client.getBooked());
		
	}
	
	@RequestMapping(value = "/confirmRent", method = RequestMethod.GET)
	private ResponseEntity<Object> confirmation(@RequestParam("id") String id){
		Booked booked = bookedServices.get(Integer.parseInt(id));
		Rent rent=new Rent();
		rent.setBooked(booked);
		rent.setClient(booked.getClient());
		rent.setDeliveryDate(LocalDate.now());
		rentServices.saveOrUpdate(rent);
		return ResponseEntity.ok((Object)"It has been confirmed");
		
	}

}

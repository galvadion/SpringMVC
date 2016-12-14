package com.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Booked;
import com.entities.Client;
import com.entities.Rent;
import com.services.BookedService;
import com.services.UserServices;
import com.servicesImpl.RentServiceImpl;


@Controller
@RequestMapping(value = "rent")
public class RentController {

	@Autowired
	UserServices userServices;

	@Autowired
	RentServiceImpl rentServices;

	@Autowired
	BookedService bookedServices;


	@Autowired
	private HttpSession httpSession;

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

	@RequestMapping(value="/confirm",method =RequestMethod.GET)
	public ModelAndView getConfirmationPage(){
		ModelAndView view=new ModelAndView("rent/confirm");
		return view;
	}

	@RequestMapping(value="/return",method =RequestMethod.GET)
	public ModelAndView getReturnPage(){
		ModelAndView view=new ModelAndView("rent/return");
		return view;
	}

	@RequestMapping(value = "/getBooked", method = RequestMethod.GET)
	private ResponseEntity<List<Booked>> getRentByName(@RequestParam("id") String id){
		Client client=(Client) userServices.get(Integer.parseInt(id));
		return ResponseEntity.ok(client.getBooked());

	}

	@RequestMapping(value = "/confirmRent", method = RequestMethod.POST)
	private ResponseEntity<Object> confirmation(@RequestBody Rent rent){
		Booked booked = bookedServices.get(rent.getBooked_id());
		System.out.println(rent);
		rent.setDeliveryDate(LocalDate.now());
		rent.setId(UUID.randomUUID().toString());
		rent = rentServices.create(rent);
		booked.setRent(rent.getId());
		bookedServices.saveOrUpdate(booked);
		return ResponseEntity.ok((Object)"It has been confirmed");
	}

	@RequestMapping(value = "/confirmReturn", method = RequestMethod.POST)
	private ResponseEntity<Object> confirmationReturn(@RequestBody Rent rent){
		rent.setReturnDate(LocalDate.now());
		rent = rentServices.create(rent);
		return ResponseEntity.ok((Object)"It has been confirmed");
	}

	@RequestMapping(value = "/getbyid", method = RequestMethod.GET)
	public ResponseEntity<Rent> getbyid(@RequestParam("id") String id) { 
		if (rentServices.get(id) != null) {
			return ResponseEntity.ok(rentServices.get(id));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Rent>> getall() { 
		return ResponseEntity.ok(rentServices.getAll());
	}
	@RequestMapping(value = "/getbyclient", method = RequestMethod.GET)
	public ResponseEntity<List<Rent>> getByClient() { 
		Client client=(Client) userServices.get(Integer.parseInt(httpSession.getAttribute("user").toString()));
		List<Booked> booked=bookedServices.getByClient(client);
		List<Rent> result=new ArrayList<>();
		for(Booked book:booked){
			try{
				Rent rent=rentServices.get(book.getRent());
				result.add(rent);
			}catch(Exception e){

			}
		}
		return ResponseEntity.ok(result);
	}

}

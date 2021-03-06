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
import com.models.RentResponse;
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
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView getDetailsPage() {
		ModelAndView view = new ModelAndView("rent/details");
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
		try {
			rent.getTransactionNr();
			rent.setTransactionDate(LocalDate.now());
		} catch (Exception e) {
			// TODO: handle exception
		}
		rent.setClientId(booked.getClient().getId());
		rent = rentServices.create(rent);
		booked.setRent(rent.getId());
		bookedServices.saveOrUpdate(booked);
		return ResponseEntity.ok(rent);
	}

	@RequestMapping(value = "/confirmReturn", method = RequestMethod.POST)
	private ResponseEntity<Object> confirmationReturn(@RequestBody Rent rent){
		Booked book=bookedServices.get(rent.getBooked_id());
		book.setReturned(true);
		bookedServices.saveOrUpdate(book);
		rent.setReturnDate(LocalDate.now());
		rent = rentServices.create(rent);
		return ResponseEntity.ok((Object)"It has been confirmed");
	}

	@RequestMapping(value = "/getbyid", method = RequestMethod.GET)
	public ResponseEntity<RentResponse> getbyid(@RequestParam("id") String id) { 
		if (rentServices.get(id) != null) {
			Rent rent=rentServices.get(id);
			RentResponse response=new RentResponse();
			response.setRent(rent);
			response.setBook(bookedServices.get(rent.getBooked_id()));
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<RentResponse>> getall() { 
		List<RentResponse> result=new ArrayList<>();
		List<Rent> rentList=rentServices.getAll();
		for(Rent rent:rentList){
			RentResponse respuesta=new RentResponse();
			respuesta.setRent(rent);
			respuesta.setBook(bookedServices.get(rent.getBooked_id()));
			result.add(respuesta);
		}
		
		return ResponseEntity.ok(result);
	}
	@RequestMapping(value = "/getbyclient", method = RequestMethod.GET)
	public ResponseEntity<List<RentResponse>> getByClient() { 
		List<RentResponse> result=new ArrayList<>();
		List<Rent> rentList=rentServices.getRentsByClient(Integer.parseInt(httpSession.getAttribute("user").toString()));
		for(Rent rent:rentList){
			RentResponse respuesta=new RentResponse();
			respuesta.setRent(rent);
			respuesta.setBook(bookedServices.get(rent.getBooked_id()));
			result.add(respuesta);
		}
		return ResponseEntity.ok(result);
	}

}

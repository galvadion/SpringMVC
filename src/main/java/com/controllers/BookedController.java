package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Client;
import com.entities.Employee;
import com.models.BookingModel;
import com.entities.Booked;
import com.entities.Vehicle;
import com.services.BookedService;
import com.services.UserServices;

@Controller
@RequestMapping(value = "booked")
public class BookedController {

	@Autowired
	BookedService bookedService;
	
	@Autowired
	UserServices userService;
	
	@Autowired
	private HttpSession httpSession;
	
	static Logger log = Logger.getLogger(PaymentTransactionController.class.getName());

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("booked/list");
		return view;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("booked/form");
		return view;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getEditPage() {
		ModelAndView view = new ModelAndView("booked/form");
		return view;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView getSearchPage() {
		ModelAndView view = new ModelAndView("booked/search");
		return view;
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView getDetailsPage() {
		ModelAndView view = new ModelAndView("booked/details");
		return view;
	}
	
	@RequestMapping(value = "/getbyid", method = RequestMethod.GET)
	public ResponseEntity<Booked> getbyid(@RequestParam("id") Integer id) { 
		if (bookedService.get(id) != null) {
			return ResponseEntity.ok(bookedService.get(id));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Booked>> getall() { 	
			return ResponseEntity.ok(bookedService.getAll());
	}
	
	@RequestMapping(value = "/getallbyuser", method = RequestMethod.GET)
	public ResponseEntity<List<Booked>> getallbyYser() {
		Client client=(Client) userService.get(Integer.parseInt(httpSession.getAttribute("user").toString()));
			return ResponseEntity.ok(bookedService.getByClient(client));
	}

}

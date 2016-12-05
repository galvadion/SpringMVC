package com.controllers;

import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Client;
import com.models.BookingModel;
import com.services.BookedService;

@Controller
@RequestMapping(value = "booked")
public class BookedController {

	@Autowired
	BookedService bookedService;
	
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
	
	public String registerBooking(BookingModel model,Client client, String transactionId, String payerId){
		try{
			bookedService.registerBook(model, client, transactionId, payerId);
			return "ok";
		}
		catch(Exception ex){
			log.error(ex);
			return "error";
		}
	}

}

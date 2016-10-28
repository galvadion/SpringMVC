package com.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Brand;
import com.services.BrandService;

@Controller
@RequestMapping(value = "booked")
public class BookedController {

	@Autowired
	BrandService BookedService;

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
		ModelAndView view = new ModelAndView("user/form");
		return view;
	}
	
	/*
	 * return the searchView
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView view = new ModelAndView("booking/search");
		return view;
	}

}

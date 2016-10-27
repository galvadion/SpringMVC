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
@RequestMapping(value = "booking")
public class BookinController {

	@Autowired
	BrandService BookedService;
	
	/*
	 * return the searchView
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView view = new ModelAndView("booking/search");
		return view;
	}



}

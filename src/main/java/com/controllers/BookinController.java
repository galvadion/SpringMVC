package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Brand;
import com.entities.User;
import com.services.BrandService;

@Controller
@RequestMapping(value = "booking")
public class BookinController {

	@Autowired
	BrandService BookedService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView view = new ModelAndView("booking/search");
		return view;
	}



	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSaved(Brand brand) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("status", "200");
		map.put("message", "Your record have been saved successfully");

		return map;
	}
}

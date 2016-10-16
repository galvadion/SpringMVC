package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="vehicle")
public class VehicleController {
	
	@RequestMapping(value="/list",method =RequestMethod.GET)
	public ModelAndView getPage(){
		ModelAndView view=new ModelAndView("vehicle/list");
		return view;
	}
}
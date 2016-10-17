package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="model")
public class ModelController {
	
	@RequestMapping(value="/list",method =RequestMethod.GET)
	public ModelAndView getListPage(){
		ModelAndView view=new ModelAndView("model/list");
		return view;
	}
	
	@RequestMapping(value="/create",method =RequestMethod.GET)
	public ModelAndView getCreatePage(){
		ModelAndView view=new ModelAndView("model/form");
		return view;
	}
}
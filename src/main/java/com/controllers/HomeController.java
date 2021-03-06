package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="home")
public class HomeController {
	
	@RequestMapping(value="/home",method =RequestMethod.GET)
	public ModelAndView getPage(){
		ModelAndView view=new ModelAndView("home/home");
		return view;
	}
	
	@RequestMapping(value="/about",method =RequestMethod.GET)
	public ModelAndView getAboutPage(){
		ModelAndView view=new ModelAndView("home/about");
		return view;
	}
}
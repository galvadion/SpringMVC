package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="blocks")
public class BlocksController {
	
	/*
	 * This method brings the header of the page
	 */
	@RequestMapping(value="/header",method =RequestMethod.GET)
	public ModelAndView getHeader(){
		ModelAndView view=new ModelAndView("blocks/header");
		return view;
	}
}
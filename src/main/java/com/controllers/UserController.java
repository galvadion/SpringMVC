package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="user")
public class UserController {
	
	@RequestMapping(value="/user",method =RequestMethod.GET)
	public ModelAndView getPage(){
		ModelAndView view=new ModelAndView("user/user");
		return view;
	}
}
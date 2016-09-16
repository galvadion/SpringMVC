package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.services.UserServices;

@RestController
public class LoginController {

	@Autowired
	UserServices userService;
	
	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public boolean logInUser(String user,String password){
		
		return userService.validateLogin(user, password);
		
	}
}

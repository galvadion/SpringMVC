package com.restController;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.entities.User;
import com.services.UserServices;


@RestController
public class ApiRestController {

	@Autowired
	UserServices userService;
	
	@RequestMapping(value="/api/allUsers",method =RequestMethod.GET)
	public List<User> getPage(){
		
		return userService.getAll();
	}
	

}

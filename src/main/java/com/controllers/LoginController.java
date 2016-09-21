package com.controllers;

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

import com.services.UserServices;

@Controller
@RequestMapping(value="login")
public class LoginController {

	@Autowired
	UserServices userService;
	
	@RequestMapping(value="/login",method =RequestMethod.GET)
	public ModelAndView getPage(){
		ModelAndView view=new ModelAndView("login/login");
		return view;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> logInUser(){
		String username= Request.getParameter("username");
		boolean log= userService.validateLogin(username(), password());
		if(log){
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}
	
/*	public class LogInResponseBody{
		private String username;
		private String password;
		
		public LogInResponseBody(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		public LogInResponseBody(){};
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		
	}*/
}

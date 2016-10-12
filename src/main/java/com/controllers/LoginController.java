package com.controllers;

import java.io.Serializable;

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
	
	@RequestMapping(value="/forgot",method =RequestMethod.GET)
	public ModelAndView getForgotPage(){
		ModelAndView view=new ModelAndView("forgot/forgot");
		return view;
	}
	
	@RequestMapping(value="/register",method =RequestMethod.GET)
	public ModelAndView getRegisterPage(){
		ModelAndView view=new ModelAndView("register/register");
		return view;
	}
	
	@RequestMapping(value="/terms",method =RequestMethod.GET)
	public ModelAndView getTermsPage(){
		ModelAndView view=new ModelAndView("register/terms");
		return view;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LogInResonseBody> logInUser(@RequestBody LogInRequestBody user,    UriComponentsBuilder ucBuilder){
		boolean log= userService.validateLogin(user.getUsername(), user.getPassword());
		LogInResonseBody response = new LogInResonseBody();
		if(log){
			
			response.setIs_logged_in(true);
			return new ResponseEntity<LogInResonseBody>(response,HttpStatus.OK);
		}else{
			response.setIs_logged_in(false);
			return new ResponseEntity<LogInResonseBody>(response,HttpStatus.CONFLICT);
		}
	}
	
	public static class LogInRequestBody implements Serializable{
		private String username;
		private String password;
		
		public LogInRequestBody(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		public LogInRequestBody(){
			
		};
		
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
	}
	
	public static class LogInResonseBody{
		Boolean is_logged_in;

		public Boolean getIs_logged_in() {
			return is_logged_in;
		}

		public void setIs_logged_in(Boolean is_logged_in) {
			this.is_logged_in = is_logged_in;
		}
		
		public LogInResonseBody(){};
	}
}

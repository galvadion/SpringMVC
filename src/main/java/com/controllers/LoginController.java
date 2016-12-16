package com.controllers;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.entities.Admin;
import com.entities.Client;
import com.entities.User;
import com.services.UserServices;
import com.servicesImpl.MailAuxiliarService;


@Controller
@RequestMapping(value="login")
public class LoginController {

	@Autowired
	UserServices userService;
	
	@Resource(name="MailAuxiliar")
	MailAuxiliarService mailAuxiliar;
	
	@Autowired
	 private HttpSession httpSession;
	
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
	public ResponseEntity<LogInResonseBody> logInUser(@RequestBody LogInRequestBody users,    UriComponentsBuilder ucBuilder){
		User user= userService.validateLogin(users.getEmail(), users.getPassword());
		LogInResonseBody response = new LogInResonseBody();
		if(user != null){
			if (!user.isActive()){
				response.setName("Esta cuenta no se encuentra validada. Si aun no ha recibido el correo, puede elegir para enviarlo nuevamente");
				return new ResponseEntity<LogInResonseBody>(response,HttpStatus.CONFLICT);
			}else{
				response.setEmail(user.getEmail());
				response.setId(user.getId());
				response.setName(user.getName());
				response.setPasssword(user.getPassword());
				if(user.getClass() == Client.class){
					response.setRol("Client");
				}else if ( user.getClass() == Admin.class) {
					response.setRol("Admin");
				}else{
					response.setRol("Employee");
				}
				httpSession.setAttribute("user", user.getId());
				
				return new ResponseEntity<LogInResonseBody>(response,HttpStatus.OK);
			}
			
		}else{
			response.setName("No existe el usuario o a introducido una contraseña incorrecta, intente nuevamente por favor");
			return new ResponseEntity<LogInResonseBody>(response,HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value= "/recoverpassword",method =RequestMethod.GET)
	public ModelAndView recoverPassword(@RequestParam("email") String email){
		
		String password=userService.recoverPassword(email);
		mailAuxiliar.sendMailWithHtmlText("<p>Buenas!</p><br><p>Se le ha generado una nueva contraseña para que pueda ingresar a nuestro sistema.</p><br><p>Ingrese con su correo habitual y la contraseña "+password+", luego cambiela para asegurar su cuenta.</p><br><p>Saludos del personal de Rent-UY</p>", email, "Restablecer contraseña");
		ModelAndView view=new ModelAndView("login/login");
		return view;
	}
	
	@RequestMapping(value="/validate",method =RequestMethod.GET)
	public  boolean validateSession(){
		try{
			httpSession.getAttribute("user");
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	public static class LogInRequestBody implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String email;
		private String password;
		
		public LogInRequestBody(String email, String password) {
			super();
			this.email = email;
			this.password = password;
		}

		public LogInRequestBody(){
			
		};
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
	
	public static class LogInResonseBody{
		private Integer id;
		private String name;
		private String rol;
		private String passsword;
		private String email;
		private String message;
		
		private String getMessage(){
			return message;
		}
		
		private void setMessage(String message){
			this.message=message;
		}
		
		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getRol() {
			return rol;
		}


		public void setRol(String rol) {
			this.rol = rol;
		}


		public String getPasssword() {
			return passsword;
		}


		public void setPasssword(String passsword) {
			this.passsword = passsword;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public LogInResonseBody(){};
	}
}

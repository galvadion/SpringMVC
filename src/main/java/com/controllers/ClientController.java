package com.controllers;

import org.hibernate.internal.ExceptionConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Client;
import com.entities.User;
import com.models.ErrorResponse;
import com.services.UserServices;
import com.servicesImpl.MailAuxiliarService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "client")
public class ClientController {

	@Autowired(required = true)
	private UserServices userServices;
	
	@Resource(name="MailAuxiliar")
	MailAuxiliarService mailSender;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("user/list");
		return view;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("user/form");
		return view;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getEditPage() {
		ModelAndView view = new ModelAndView("user/form");
		return view;
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView getConfirmPage() {
		ModelAndView view = new ModelAndView("user/confirm");
		return view;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> getSaved(@RequestBody Client users,HttpServletRequest request) {
		System.out.println(users);
		try {
			if(users.getId() == null){
				users.setActive(false);
				userServices.saveOrUpdate(users);
				mailSender.sendMailWithHtmlText("<p>Bienvenido "+users.getName() + " "+users.getLastName() +"!</p><br>"
		        		+ "<p>Nos complace mucho que haya escodigo nuestra plataforma para alquilar vehiculos. Por favor para continuar haga click <a href='https://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() +"/#/client/confirm/"+users.getDocument()+"' >aqui</a> y asi activar su cuenta </p>", users.getEmail(), "Bienvenido a Rent-UY");
			}else{
				userServices.saveOrUpdate(users);
			}
			
	        // sends the e-mail
			
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			try{
				userServices.getByDocument(users.getDocument());
				
			}catch(Exception ex){
				ErrorResponse response=new ErrorResponse();
				response.setMessage("Ya existe un usuario registrado con ese correo");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			ErrorResponse response=new ErrorResponse();
			response.setMessage("Ya existe un usuario registrado con ese numero de documento");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAll(User users) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<User> list = userServices.getAll();

		if (list != null) {
			map.put("status", "200");
			map.put("message", "Data found");
			map.put("data", list);
		} else {
			map.put("status", "404");
			map.put("message", "Data not found");

		}

		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> delete(User users) {
		Map<String, Object> map = new HashMap<String, Object>();

		userServices.remove(users);
		map.put("status", "200");
		map.put("message", "Your record have been deleted successfully");

		return map;
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> getAll() {
		List<Client> list = userServices.getAllClients();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/getbydocument", method=RequestMethod.GET)
	public ResponseEntity<Object> getByDocument(@RequestParam("id") String document){
		return ResponseEntity.ok((Object) userServices.getByDocument(document));
	}
}

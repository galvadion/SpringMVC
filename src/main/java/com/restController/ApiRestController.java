package com.restController;

import java.io.Serializable;
import java.util.Date;
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

import com.controllers.LoginController.LogInResonseBody;
import com.entities.Model;
import com.entities.User;
import com.models.SearchFilter;
import com.services.ModelService;
import com.services.UserServices;


@RestController
public class ApiRestController {

	@Autowired
	UserServices userService;
	
	@Autowired
	ModelService modelService;
	
	@RequestMapping(value="/api/allUsers",method =RequestMethod.GET)
	public List<User> getPage(){
		
		return userService.getAll();
	}
	
	@RequestMapping(value="/api/allModels",method =RequestMethod.GET)
	public ResponseEntity<List<Model>> getModelos(){
		SearchFilter filter=new SearchFilter();
		filter.setBeginDate(new Date());
		Date finalDate=new Date();
		finalDate.setDate(17);
		filter.setEndDate(finalDate);
		filter.setAirConditioner(true);
		filter.setLuggage(1);
		filter.setPassangers(0);
		filter.setTransmission("-");
		filter.setBranchId(1);
		return new ResponseEntity<List<Model>>(modelService.getModelsBetweenFilter(filter,false,filter.getFuelTypeId()),HttpStatus.OK);
	}
	

}

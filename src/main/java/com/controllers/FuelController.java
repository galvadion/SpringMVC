package com.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Fuel;
import com.services.FuelService;

@Controller
@RequestMapping(value = "booking")
public class FuelController {
	
	@Autowired
	FuelService fuelService;
	
	private static Validator validator;

	
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("fuel/list");
		return view;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("fuel/form");
		return view;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> getSaved(@RequestBody Fuel fuel) {
		
		System.out.println(fuel);
		setUpValidator();
		Set<ConstraintViolation<Fuel>> constraintViolations = validator.validate(fuel);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body((Object) constraintViolations.iterator().next().getMessage());
		} else {
			try {
				fuelService.saveOrUpdate(fuel);
				return ResponseEntity.ok((Object) fuel);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Object) "There has been an error");
			}
		}
	}

	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Fuel>> getAll() {
		List<Fuel> list = fuelService.getAll();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
}

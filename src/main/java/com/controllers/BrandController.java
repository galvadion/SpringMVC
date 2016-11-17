package com.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Brand;
import com.services.BrandService;

@Controller
@RequestMapping(value = "brand")
public class BrandController {

	@Autowired
	BrandService brandService;

	private static Validator validator;

	@RequestMapping(value="",method =RequestMethod.GET)
	public ModelAndView getListPage(){
		ModelAndView view=new ModelAndView("brand/list");
		return view;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("brand/form");
		return view;
	}
	
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> getAll() {
		List<Brand> list = brandService.getAvailable();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value = "/getnotempty", method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> getnotempty() {
		List<Brand> list = brandService.getNotEmpty();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> getSaved(@RequestBody Brand brand) {
		setUpValidator();
		Set<ConstraintViolation<Brand>> constraintViolations = validator.validate(brand);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body((Object)constraintViolations.iterator().next().getMessage());
		} else {
			try {
				brandService.saveOrUpdate(brand);
				return ResponseEntity.ok((Object)brand);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Object)"The name of the brand is duplicated");
			}
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestParam("id") String id){
		Brand entity=brandService.get(Integer.valueOf(id));
		brandService.removeCascade(entity);
		return ResponseEntity.ok((Object)"It has been removed");
		
	}
	
}

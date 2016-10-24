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

import com.entities.BranchOffice;
import com.entities.Brand;
import com.entities.Extras;
import com.services.BranchOfficeService;

@Controller
@RequestMapping(value = "branch_offices")
public class BranchOfficeController {
	
	@Autowired
	BranchOfficeService branchOfficeService;
	
	private static Validator validator;
	
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
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
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> createOffice(@RequestBody BranchOffice office){
		setUpValidator();
		Set<ConstraintViolation<BranchOffice>> constraintViolations = validator.validate(office);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body((Object)constraintViolations.iterator().next().getMessage());
		} else {
			try {
				branchOfficeService.saveOrUpdate(office);
				return ResponseEntity.ok((Object)office);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Object)"The name of the branch is duplicated");
			}
		}
	}
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<BranchOffice>> getAll() {
		List<BranchOffice> list = branchOfficeService.getAll();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
}

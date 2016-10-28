package com.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.BranchOffice;
import com.entities.Brand;
import com.entities.Extras;
import com.services.BranchOfficeService;

@Controller
@RequestMapping(value = "branchoffice")
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
		ModelAndView view=new ModelAndView("branchoffice/list");
		return view;
	}
	
	@RequestMapping(value="/create",method =RequestMethod.GET)
	public ModelAndView getCreatePage(){
		ModelAndView view=new ModelAndView("branchoffice/form");
		return view;
	}
	
	@RequestMapping(value="/edit",method =RequestMethod.GET)
	public ModelAndView getEditPage(){
		ModelAndView view=new ModelAndView("branchoffice/form");
		return view;
	}
	
	/*
	 * BranchOffice is the model to persist or update
	 * return an String if there is an error or a BranchOffice in case it is successful
	 */
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
	
	/*
	 * returns a list of all the brands that are not closed
	 */
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<BranchOffice>> getAll() {
		List<BranchOffice> list = branchOfficeService.getAll();
		List<BranchOffice> result=new ArrayList<BranchOffice>();
		if (list != null) {
			for(BranchOffice branch:list){
				if(!branch.isClosed()) result.add(branch);
			}
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	/*
	 * 
	 * Throws an error if the branch has vehicles in it or if it is going to recibe a vehicle
	 * returns ok if the branch is empty
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestParam("id") String id){
		BranchOffice entity=branchOfficeService.get(Integer.valueOf(id));
		try {
			branchOfficeService.closeBranchOffice(entity);
		} catch (Exception e) {
			return ResponseEntity.ok((Object)e.getMessage());
		}
		return ResponseEntity.ok((Object)"It has been removed");
		
	}
	
}

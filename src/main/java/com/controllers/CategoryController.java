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

import com.entities.Category;
import com.services.CategoryService;

@Controller
@RequestMapping(value = "category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	private static Validator validator;

	
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("category/list");
		return view;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("category/form");
		return view;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> getSaved(@RequestBody Category category) {
		
		System.out.println(category);
		setUpValidator();
		Set<ConstraintViolation<Category>> constraintViolations = validator.validate(category);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body((Object) constraintViolations.iterator().next().getMessage());
		} else {
			try {
				categoryService.saveOrUpdate(category);
				return ResponseEntity.ok((Object) category);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Object) "There has been an error");
			}
		}
	}

	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAll() {
		List<Category> list = categoryService.getAll();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
}

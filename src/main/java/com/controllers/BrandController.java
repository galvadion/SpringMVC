package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Brand;
import com.entities.User;
import com.services.BrandService;

@Controller
@RequestMapping(value = "brand")
public class BrandController {

	@Autowired
	BrandService BrandService;

	private static Validator validator;

	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("brand/list");
		return view;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("brand/form");
		return view;
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Brand> list = BrandService.getAll();
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

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSaved(Brand brand) {
		Map<String, Object> map = new HashMap<String, Object>();
		setUpValidator();
		Set<ConstraintViolation<Brand>> constraintViolations = validator.validate(brand);
		if (constraintViolations.size() > 0) {
			map.put("status", "400");
			map.put("message", constraintViolations.iterator().next().getMessage());
		} else {
			try {
				BrandService.saveOrUpdate(brand);
				map.put("status", "200");
				map.put("message", "Your record have been saved successfully");
			} catch (Exception e) {
				map.put("status", "400");
				map.put("message", "The name of the brand is duplicated");
			}
		}
		return map;
	}
}

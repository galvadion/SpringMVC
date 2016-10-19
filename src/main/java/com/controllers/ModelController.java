package com.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Model;
import com.models.SearchFilter;
import com.services.BrandService;
import com.services.CategoryService;
import com.services.ModelService;

@Controller
@RequestMapping(value = "model")
public class ModelController {

	@Autowired
	ModelService modelService;

	@Autowired
	BrandService brandService;

	@Autowired
	CategoryService categoryService;

	private static Validator validator;

	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("model/list");
		return view;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("model/form");
		return view;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> getSaved(@RequestBody RequestModel requestModel) {
		Model model = requestModel.model;
		model.setBrand(brandService.get(requestModel.brand_id));
		model.setCategory(categoryService.get(requestModel.category_id));
		setUpValidator();
		Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body((Object) constraintViolations.iterator().next().getMessage());
		} else {
			try {
				modelService.saveOrUpdate(model);
				return ResponseEntity.ok((Object) model);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Object) "There has been an error");
			}
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<Model>> searchModels(@RequestBody SearchFilter search) {
		return ResponseEntity.ok(modelService.getModelsBetweenFilter(search));
	}
	
	private static class RequestModel implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Model model;
		private Integer category_id;
		private Integer brand_id;

	}
}
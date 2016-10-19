package com.controllers;

import java.io.Serializable;
import java.time.LocalDate;
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

import com.entities.Model;
import com.entities.StatusBetweenDates;
import com.entities.Vehicle;
import com.models.Vehicle_Status;
import com.services.BranchOfficeService;
import com.services.ModelService;
import com.services.StatusBetweenDatesService;
import com.services.VehicleService;

@Controller
@RequestMapping(value="vehicle")
public class VehicleController {
	
	@Autowired
	ModelService modelService;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	BranchOfficeService branchService;
	
	@Autowired
	StatusBetweenDatesService statusService;
	
	private static Validator validator;

	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	
	@RequestMapping(value="",method =RequestMethod.GET)
	public ModelAndView getListPage(){
		ModelAndView view=new ModelAndView("vehicle/list");
		return view;
	}
	
	@RequestMapping(value="/create",method =RequestMethod.GET)
	public ModelAndView getCreatePage(){
		ModelAndView view=new ModelAndView("vehicle/form");
		return view;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> getSaved(@RequestBody RequestModel requestModel) {
		Vehicle vehicle = requestModel.vehicle;
		vehicle.setState(Vehicle_Status.Available);
		vehicle.setModel(modelService.get(requestModel.model_id));
		vehicle.setBranchOffice(branchService.get(requestModel.branchOffice_id));
		setUpValidator();
		Set<ConstraintViolation<Vehicle>> constraintViolations = validator.validate(vehicle);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body((Object) constraintViolations.iterator().next().getMessage());
		} else {
			try {
				vehicleService.saveOrUpdate(vehicle);
				StatusBetweenDates availability=new StatusBetweenDates();
				availability.setBeginDate(LocalDate.now());
				availability.setEndDate(LocalDate.now().plusYears(5));
				availability.setVehicle(vehicle);
				availability.setBranchOffice(branchService.get(requestModel.branchOffice_id));
				statusService.saveOrUpdate(availability);
				return ResponseEntity.ok((Object) vehicle);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Object) "There has been an error");
			}
		}
	}
	
	private static class RequestModel implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Vehicle vehicle;
		private Integer model_id;
		private Integer branchOffice_id;
	}
}
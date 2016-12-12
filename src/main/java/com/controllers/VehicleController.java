package com.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Image;
import com.entities.StatusBetweenDates;
import com.entities.Vehicle;
import com.models.MaintenanceModel;
import com.models.Vehicle_Status;
import com.services.BranchOfficeService;
import com.services.ImageService;
import com.services.ModelService;
import com.services.StatusBetweenDatesService;
import com.services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

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
		
		@Autowired
		ImageService imageService;
		
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
	
	@RequestMapping(value="/edit",method =RequestMethod.GET)
	public ModelAndView getEditPage(){
		ModelAndView view=new ModelAndView("vehicle/form");
		return view;
	}
	
	@RequestMapping(value="/details",method =RequestMethod.GET)
	public ModelAndView getDetailsPage(){
		ModelAndView view=new ModelAndView("vehicle/details");
		return view;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> getSaved(@RequestBody Vehicle requestModel) {
		requestModel.setState(Vehicle_Status.Available);
		setUpValidator();
		Set<ConstraintViolation<Vehicle>> constraintViolations = validator.validate(requestModel);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body((Object) constraintViolations.iterator().next().getMessage());
		} else {
			try {
				vehicleService.saveOrUpdate(requestModel);
				StatusBetweenDates availability=new StatusBetweenDates();
				availability.setBeginDate(LocalDate.now());
				availability.setEndDate(LocalDate.now().plusYears(5));
				availability.setVehicle(requestModel);
				availability.setStatus(Vehicle_Status.Available);
				availability.setBranchOffice(branchService.get(requestModel.getBranchOffice().getId()));
				statusService.saveOrUpdate(availability);
				return ResponseEntity.ok((Object) requestModel);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Object) "There has been an error");
			}
		}
	}
	
	@RequestMapping(value = "/maintenance", method = RequestMethod.POST)
	public ResponseEntity<Object> sendToMaintenance(@RequestBody MaintenanceModel requestModel){
		Vehicle vehicle=vehicleService.get(requestModel.getVehicleId());
		vehicleService.abortNewEvents(vehicle);
		StatusBetweenDates current=statusService.getCurrentStatus(vehicle,requestModel.getFirstDate());
		current.setEndDate(requestModel.getFirstDate());
		statusService.saveOrUpdate(current);
		//Creates the status in the unavailable date
		StatusBetweenDates unavailability=new StatusBetweenDates();
		unavailability.setBeginDate(requestModel.getFirstDate());
		unavailability.setStatus(Vehicle_Status.Maintenance);
		unavailability.setEndDate(requestModel.getEndDate());
		unavailability.setVehicle(vehicle);
		unavailability.setBranchOffice(vehicle.getBranchOffice());
		statusService.saveOrUpdate(unavailability);
		//Creates the status in the available date
		StatusBetweenDates availability=new StatusBetweenDates();
		availability.setBeginDate(requestModel.getEndDate());
		availability.setStatus(Vehicle_Status.Available);
		availability.setEndDate(requestModel.getEndDate().plusYears(5));
		availability.setVehicle(vehicle);
		availability.setBranchOffice(vehicle.getBranchOffice());
		statusService.saveOrUpdate(availability);
		return ResponseEntity.ok((Object) "Its been registered");
		
	}
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Vehicle>> getVehicles(){
		List<Vehicle> list=vehicleService.getAvailable();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(String id) {
		Vehicle vehicle=vehicleService.get(Integer.parseInt(id));
		vehicle.setUnavailable(true);
		vehicleService.abortNewEvents(vehicle);
		
		return ResponseEntity.ok((Object)"It has been removed");
	}
	
	@RequestMapping(value = "/deleteimage", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteImage(String id) {
		Image vehicle=imageService.get(Integer.parseInt(id));
		imageService.remove(vehicle);
		return ResponseEntity.ok((Object)"It has been removed");
	}
	
	@RequestMapping(value = "/getbyid", method = RequestMethod.GET)
	public ResponseEntity<Vehicle> getbyid(@RequestParam("id") Integer id) { 
		if (vehicleService.get(id) != null) {
			return ResponseEntity.ok(vehicleService.get(id));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
}
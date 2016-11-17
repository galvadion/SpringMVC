package com.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entities.BranchOffice;
import com.entities.Rent;
import com.entities.StatusBetweenDates;
import com.entities.Vehicle;
import com.models.MaintenanceModel;
import com.models.PickedModel;
import com.models.ReportSearch;
import com.models.Vehicle_Status;
import com.services.BranchOfficeService;
import com.services.ModelService;
import com.services.RentService;
import com.services.StatusBetweenDatesService;
import com.services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(value = "report")
public class ReportController {

	@Autowired
	ModelService modelService;

	@Autowired
	VehicleService vehicleService;

	@Autowired
	BranchOfficeService branchService;

	@Autowired
	StatusBetweenDatesService statusService;

	@Autowired
	RentService rentService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("vehicle/list");
		return view;
	}

	@RequestMapping(value = "/getpickuptoday", method = RequestMethod.GET)
	public ResponseEntity<List<PickedModel>> getpickedupToday() {
		List<PickedModel> result = new ArrayList();
		for (BranchOffice bo : branchService.getAvailable()) {
			for (Vehicle vehicle : vehicleService.getPickedUpToday(bo)) {
				PickedModel pm = new PickedModel();
				pm.setVehicle(vehicle);
				pm.setBoo(bo);
				result.add(pm);
			}
		}
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/getreturnedtoday", method = RequestMethod.GET)
	public ResponseEntity<List<PickedModel>> getreturnedToday() {
		List<PickedModel> result = new ArrayList();
		for (BranchOffice bo : branchService.getAvailable()) {
			for (Vehicle vehicle : vehicleService.getReturnedToday(bo)) {
				PickedModel pm = new PickedModel();
				pm.setVehicle(vehicle);
				pm.setBoo(bo);
				result.add(pm);
			}
		}
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/getBookedBetweenDates", method = RequestMethod.POST)
	public ResponseEntity<List<PickedModel>> bookedInDates(ReportSearch rs) {
		List<PickedModel> result = new ArrayList();
		for (Rent rent : rentService.getBetweenDates(rs.getInitialDate(), rs.getFinalDate())) {
			PickedModel pm = new PickedModel();
			pm.setBoe(rent.getBooked().getEndOffice());
			pm.setBoo(rent.getBooked().getOriginOffice());
			pm.setVehicle(rent.getBooked().getVehicle());
		}
		return ResponseEntity.ok(result);
	}
}
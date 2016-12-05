package com.controllers;


import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entities.BranchOffice;
import com.entities.Rent;
import com.entities.Vehicle;
import com.models.PickedModel;
import com.models.ReportSearch;
import com.services.BranchOfficeService;
import com.services.ModelService;
import com.services.RentService;
import com.services.StatusBetweenDatesService;
import com.services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

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
		ModelAndView view = new ModelAndView("report/list");
		return view;
	}

	@RequestMapping(value = "/getpickuptoday", method = RequestMethod.GET)
	public ResponseEntity<List<PickedModel>> getpickedupToday() {
		List<PickedModel> result = new ArrayList<PickedModel>();
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
		List<PickedModel> result = new ArrayList<PickedModel>();
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
		List<PickedModel> result = new ArrayList<PickedModel>();
		for (Rent rent : rentService.getBetweenDates(rs.getInitialDate(), rs.getFinalDate())) {
			PickedModel pm = new PickedModel();
			pm.setBoe(rent.getBooked().getEndOffice());
			pm.setBoo(rent.getBooked().getOriginOffice());
			pm.setVehicle(rent.getBooked().getVehicle());
		}
		return ResponseEntity.ok(result);
	}
}
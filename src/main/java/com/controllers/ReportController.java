package com.controllers;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Employee;
import com.entities.Rent;
import com.models.PickedModel;
import com.models.ReportSearch;
import com.services.BookedService;
import com.services.BranchOfficeService;
import com.services.ModelService;
import com.services.StatusBetweenDatesService;
import com.services.UserServices;
import com.services.VehicleService;
import com.servicesImpl.RentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	UserServices userService;

	@Autowired
	RentServiceImpl rentServices;

	@Autowired
	BookedService bookedService;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("report/list");
		return view;
	}


	@RequestMapping(value = "/getpickup", method = RequestMethod.GET)
	public ResponseEntity<List<Booked>> getpickedupToday(@RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		try{
			System.out.println(date);
			Employee employee=(Employee) userService.get(Integer.parseInt(httpSession.getAttribute("user").toString()));
			BranchOffice branch=(employee.getBranchOffice());
			return ResponseEntity.ok(bookedService.getBookedByDayAndOffice(branch, date));
		}catch(Exception e){
			return ResponseEntity.ok(bookedService.getDeliveredByDay(date));	
		}
	}

	@RequestMapping(value = "/getreturned", method = RequestMethod.GET)
	public ResponseEntity<List<Booked>> getreturnedToday(@RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		/*	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy");
		LocalDate day = LocalDate.parse(date, dtf);*/
		try{
			Employee employee=(Employee) userService.get(Integer.parseInt(httpSession.getAttribute("user").toString()));
			BranchOffice branch=(employee.getBranchOffice());
			System.out.println(branch);
			return ResponseEntity.ok(bookedService.getReturnedToday(branch, date));
		}catch(Exception e){
			return ResponseEntity.ok(bookedService.getReturnedToday(date));	
		}
	}

	@RequestMapping(value = "/getreturnedadmin", method = RequestMethod.GET)
	public ResponseEntity<List<Booked>> getreturnedAdminToday(@RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		/*	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy");
		LocalDate day = LocalDate.parse(date, dtf);*/
		return ResponseEntity.ok(bookedService.getReturnedToday(date));
	}

	@RequestMapping(value = "/getdelivered", method = RequestMethod.GET)
	public ResponseEntity<List<Booked>> getdeliverToday(@RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy");
		LocalDate day = LocalDate.parse(date, dtf);*/

		return ResponseEntity.ok(bookedService.getDeliveredByDay(date));
	}

	@RequestMapping(value = "/getBookedBetweenDates", method = RequestMethod.POST)
	public ResponseEntity<List<PickedModel>> bookedInDates(@RequestBody  ReportSearch rs) {
		List<Rent> rents=rentServices.getBetweenDates(rs.getBeginDate(), rs.getEndDate());
		List<PickedModel> result=new ArrayList<>();
		for(Rent rent:rents){
			Booked book=bookedService.get(rent.getBooked_id());
			PickedModel pm=new PickedModel();
			pm.setBooked(book);
			pm.setRent(rent);
			result.add(pm);
		}
		
		return  ResponseEntity.ok(result);
	}
}
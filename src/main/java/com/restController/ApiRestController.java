package com.restController;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.entities.Booked;
import com.entities.Brand;
import com.entities.Client;
import com.entities.User;
import com.models.BookingModel;
import com.models.SearchFilter;
import com.services.BookedService;
import com.services.BrandService;
import com.services.ModelService;
import com.services.RentService;
import com.services.UserServices;

@RestController
public class ApiRestController {

	@Autowired
	UserServices userService;

	@Autowired
	ModelService modelService;

	@Autowired
	BookedService bookedService;

	@Autowired
	RentService rentService;

	@Autowired
	BrandService brandService;

	private static Validator validator;

	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@RequestMapping(value = "/api/allUsers", method = RequestMethod.GET)
	public List<User> getPage() {

		return userService.getAll();
	}

	@RequestMapping(value = "/api/allModels", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getModelos() {
		SearchFilter filter = new SearchFilter();
		filter.setBeginDate(LocalDate.now());
		LocalDate finalDate = LocalDate.now().plusDays(2);
		filter.setEndDate(finalDate);
		filter.setAirConditioner(true);
		filter.setLuggage(1);
		filter.setPassangers(0);
		filter.setTransmission("-");
		filter.setBranchId(1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "200");
		map.put("message", "Your record have been saved successfully");
		map.put("models", modelService.getModelsBetweenFilter(filter, false, filter.getFuelTypeId()));
		map.put("gps", "200");
		return map;
	}

	@RequestMapping(value = "/api/book", method = RequestMethod.POST)
	public void bookVehicule() {
		BookingModel booking = new BookingModel();
		booking.setEndBranchOfficeId(2);
		LocalDate finalDate = LocalDate.now().plusDays(2);
		booking.setEndDate(finalDate);
		booking.setOriginBranchOfficeId(1);
		booking.setStartDate(LocalDate.now());
		booking.setWithFullTank(false);
		booking.setWithGps(true);
		booking.setWithInsurance(false);
		booking.setIdModel(1);
		bookedService.registerBoot(booking, (Client) userService.get(1));
	}

	@RequestMapping(value = "/api/confirm", method = RequestMethod.GET)
	public void confirmRent() {
		Booked booking = bookedService.getBookedByClient((Client) userService.get(1));
		rentService.confirmRent(booking);
	}

	@RequestMapping(value = "/api/insertBrand", method = RequestMethod.GET)
	public ResponseEntity<String> insertBrand() {

		Brand brand = new Brand();
		brand.setName("Peugeot");
		setUpValidator();
		Set<ConstraintViolation<Brand>> constraintViolations = validator.validate(brand);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(constraintViolations.iterator().next().getMessage());
		} else {
			try {
				brandService.saveOrUpdate(brand);
				return ResponseEntity.ok("The brand has been saved");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The name of the brand is duplicated");
			}
		}
		
	}
}

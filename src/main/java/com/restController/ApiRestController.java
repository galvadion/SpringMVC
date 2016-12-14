package com.restController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.controllers.LoginController.LogInRequestBody;
import com.controllers.LoginController.LogInResonseBody;
import com.entities.Admin;
import com.entities.BranchOffice;
import com.entities.Brand;
import com.entities.Client;
import com.entities.Extras;
import com.entities.Model;
import com.entities.Promotion;
import com.entities.User;
import com.models.BookingModel;
import com.models.SearchFilter;
import com.services.BookedService;
import com.services.BranchOfficeService;
import com.services.BrandService;
import com.services.ExtrasService;
import com.services.ModelService;
import com.services.UserServices;
import com.servicesImpl.PromotionService;

@RestController
public class ApiRestController {

	@Autowired
	UserServices userService;

	@Autowired
	ModelService modelService;

	@Autowired
	BookedService bookedService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	ExtrasService extrasService;
	
	@Autowired
	PromotionService promotionService;
	
	@Autowired
	BranchOfficeService branchOfficeService;

	private static Validator validator;

	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Secured("ROLE_ADMIN")
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
		filter.setOfficeOriginId(1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "200");
		map.put("message", "Your record have been saved successfully");
		map.put("models", modelService.getModelsBetweenFilter(filter));
		return map;
	}
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<LogInResonseBody> logInUser(@RequestBody LogInRequestBody users,    UriComponentsBuilder ucBuilder){
		User user= userService.validateLogin(users.getEmail(), users.getPassword());
		LogInResonseBody response = new LogInResonseBody();
		if(user != null){
			response.setEmail(user.getEmail());
			response.setId(user.getId());
			response.setName(user.getName());
			response.setPasssword(user.getPassword());
			if(user.getClass() == Client.class){
				response.setRol("Client");
			}else if ( user.getClass() == Admin.class) {
				response.setRol("Admin");
			}else{
				response.setRol("Employee");
			}			
			return new ResponseEntity<LogInResonseBody>(response,HttpStatus.OK);
		}else{
			return new ResponseEntity<LogInResonseBody>(response,HttpStatus.CONFLICT);
		}
	} 
	
	@RequestMapping(value = "/api/allExtras", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getExtras(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("extras", extrasService.getAll());
		map.put("status", "200");
		return map;
	}
	
	@RequestMapping(value = "/api/promotion",method =RequestMethod.GET)
	public void createPromo(){
		Promotion promo=new Promotion();
		promo.setBeginPromotionDate(LocalDate.now());
		promo.setDescriptionText("Esta promo es valida de hoy a ma");promo.setLastPromotionDate(LocalDate.now().plusDays(10));
		promo.setPercentage(40);
		promo.setPromotionCode("ASD4521");
		promo.setModels(modelService.getAll());
		promotionService.create(promo);
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
		//booking.setWithGps(true);
		booking.setWithInsurance(false);
		booking.setIdModel(1);
		List<Extras> extras = new ArrayList<Extras>();
 		bookedService.registerBook(booking, (Client) userService.get(1), "Transaction_ID_test", "PayPal_Payer_ID", extras);
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
	
	@RequestMapping(value = "/api/insertModel", method = RequestMethod.GET)
	public ResponseEntity<String> insertModel() {
		Model model=new Model();
		model.setName("Nombre 1");
		model.setTransmission("M");
		model.setPassangers(2);
		model.setLuggage(1);
		setUpValidator();
		Set<ConstraintViolation<Model>> constraintViolations = validator.validate(model);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(constraintViolations.iterator().next().getMessage());
		} else {
			try {
				modelService.saveOrUpdate(model);
				return ResponseEntity.ok("The model has been saved");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There has been an error");
			}
		}
	}
	
    @Autowired
    private JavaMailSender mailSender;
     
    @RequestMapping(value = "/api/sendMail", method = RequestMethod.GET)
    public String doSendEmail() {
        // takes input from e-mail form
        String recipientAddress = "urashimasan@gmail.com";
        String subject = "POrueba mail";
        String message = "Este es un amil de prueba";
         
        // prints debug info
        System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
         
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
         
        // sends the e-mail
        mailSender.send(new MimeMessagePreparator() {
        	  public void prepare(MimeMessage mimeMessage) throws MessagingException {
        	    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        	    message.setFrom("info.rentuy@gmail.com");
        	    message.setTo("urashimasan@gmail.com");
        	    message.setSubject("A file for you");
        	    message.setText("<b>See the attached</b>", true);
        	  }
        	});
         
        // forwards to the view named "Result"
        return "Result";
    }
    
	@RequestMapping(value = "/api/search", method = RequestMethod.POST)
	public ResponseEntity<List<Model>> searchModels(@RequestBody SearchFilter search) {
		System.out.println(search);
		return ResponseEntity.ok(modelService.getModelsBetweenFilter(search));
	}
	@RequestMapping(value = "/api/allOffices", method = RequestMethod.GET)
	public ResponseEntity<List<BranchOffice>> getAllOffices() {
		return ResponseEntity.ok(branchOfficeService.getAll());
	}
}

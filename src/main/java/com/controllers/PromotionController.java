package com.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;
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

import com.entities.BranchOffice;
import com.entities.Client;
import com.entities.Model;
import com.entities.Promotion;
import com.models.PromoValidation;
import com.services.BranchOfficeService;
import com.services.UserServices;
import com.servicesImpl.PromotionService;

@Controller
@RequestMapping(value = "promotion")
public class PromotionController {

	@Autowired
	PromotionService promotionService;
	
	@Autowired
	BranchOfficeService branchService;
	
	@Autowired
	UserServices userService;
	
	@Autowired
	HttpSession httpSession;
	
	private static Validator validator;

	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView view = new ModelAndView("promotion/list");
		return view;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage() {
		ModelAndView view = new ModelAndView("promotion/form");
		return view;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView getEditPage() {
		ModelAndView view = new ModelAndView("promotion/form");
		return view;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Object> getSaved(@RequestBody Promotion model) {
		
		System.out.println(model);
		setUpValidator();
		Set<ConstraintViolation<Promotion>> constraintViolations = validator.validate(model);
		if (constraintViolations.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body((Object) constraintViolations.iterator().next().getMessage());
		} else {
			try {
				List<Integer> clients_id=new ArrayList<>();
				model.setClients_id(clients_id);
				model.setId(UUID.randomUUID().toString());
				model.setCreationDate(LocalDate.now());
				promotionService.create(model);
				return ResponseEntity.ok((Object) model);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Object) "There has been an error");
			}
		}
	}
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Promotion>> getAll() {
		List<Promotion> list = promotionService.getAll();
		if (list != null) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	
	@RequestMapping(value = "/validatecode", method = RequestMethod.POST)
	public ResponseEntity<PromoResponse> validatePromo(@RequestBody PromoValidation model) {
		Promotion promo=promotionService.getPromotionByCode(model.getPromotionCode());
		Client client=(Client)userService.get(Integer.parseInt(httpSession.getAttribute("user").toString()));
		boolean modelValid=false,officeValid=false,dateValid=false,userValid=true;
		String messageError="";
		try{
			for(Integer cli:promo.getClients_id()){
				if(cli.equals(client.getId())){ userValid=false;messageError="Usted ya ha utilizado este codigo";}
			}
		}catch(NullPointerException e){
			userValid=true;
		}try{
			for(Model mod:promo.getModels()){
				System.out.println(mod);
				if(mod.getId().equals(model.getModel().getId())){
					modelValid=true;
				}
			}if(promo.getModels().size()==0) modelValid=true;
		}catch(NullPointerException e){
			 modelValid=true;
		}try{
			for(BranchOffice office:promo.getOffices()){
				if(office.getId().equals(model.getOrigin())) officeValid=true;
			}
			if(promo.getOffices().size()==0) officeValid=true;
		}catch(NullPointerException e){
			officeValid=true;
		}
		if(model.getOriginDate().isAfter(promo.getBeginPromotionDate()) || model.getOriginDate().isEqual(promo.getBeginPromotionDate())){
			dateValid=true;
		}else{
			messageError="Su reserva arranca fuera de las fechas de su reserva";
		}
		PromoResponse response=new PromoResponse();
		response.setValid(modelValid && officeValid && dateValid && userValid);
		response.setPercentage(promo.getPercentage());
		response.setPromotionId(promo.getId());
		response.setPromotionCode(promo.getPromotionCode());
		response.setValidationMessage(messageError);
		return ResponseEntity.ok(response);
	}
	
	public static class PromoResponse implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6061590258295939206L;
		private boolean valid;
		private String promotionId;
		private Float percentage;
		private String validationMessage;
		private String promotionCode;
		public boolean isValid() {
			return valid;
		}
		public void setValid(boolean valid) {
			this.valid = valid;
		}
		public String getPromotionId() {
			return promotionId;
		}
		public void setPromotionId(String promotionId) {
			this.promotionId = promotionId;
		}
		public Float getPercentage() {
			return percentage;
		}
		public void setPercentage(Float percentage) {
			this.percentage = percentage;
		}
		public String getValidationMessage() {
			return validationMessage;
		}
		public void setValidationMessage(String validationMessage) {
			this.validationMessage = validationMessage;
		}
		public String getPromotionCode() {
			return promotionCode;
		}
		public void setPromotionCode(String promotionCode) {
			this.promotionCode = promotionCode;
		}
		
		
	}
}


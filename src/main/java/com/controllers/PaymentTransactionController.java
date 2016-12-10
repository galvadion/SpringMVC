package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Client;
import com.entities.Extras;
import com.entities.Model;
import com.models.BookingModel;
import com.models.ModelAndExtras;
import com.models.PayPalTransaction;
import com.models.Reservation;
import com.models.TransactionItem;
import com.services.BookedService;
import com.services.ExtrasService;
import com.services.ModelService;
import com.services.PayPalService;
import com.services.UserServices;

@Controller
@RequestMapping(value="payment")
public class PaymentTransactionController {

	static Logger log = Logger.getLogger(PaymentTransactionController.class.getName());

	@Resource(name="PayPalServiceImpl")
	PayPalService paypalService;
	
	@Autowired
	ModelService modelService;
	
	@Autowired
	UserServices userService;
	
	@Autowired
	BookedService bookedService;
	
	@Autowired
	ExtrasService extrasService;

	@RequestMapping(value="/start-paypal",method =RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> paypalPaymentBegin(@RequestBody List<TransactionItem> itemsList){
		
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("status", "500");
//		map.put("message", "An error has ocurred");
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);

		try{
			
			if(itemsList == null){
				throw new Exception("Items list from client is empty.");
			}
			validateItemList(itemsList);

			Map<String, String> response = paypalService.beginTransaction(itemsList);
			
			switch (response.get("ack")) {
			case "success" :
				map.put("status", "200");
				map.put("token", response.get("token"));
				return ResponseEntity.ok(map);
			
			case "failure" :
				map.put("status", "500");
				map.put("message", "An error has ocurred");
				log.error(response.get("message"));
				break;
			case "none" : 
				map.put("status", "500");
				map.put("message","An error has ocurred");
				log.error(response.get("message"));
				break;
			default:
				map.put("status", "500");
				map.put("message", "An error has ocurred");
				break;
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
		catch(Exception ex){
			log.error(ex.getMessage());
			map.put("message", "An error has ocurred");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	@RequestMapping(value="/booking", method=RequestMethod.GET)
	public ModelAndView bookingVehicle(){
		
//		
		ModelAndView view = new ModelAndView("payment/booking");
		return view;
	}
	
	@RequestMapping(value="/model", method=RequestMethod.GET)
	public ResponseEntity<ModelAndExtras> getModelDetails(@RequestParam("modelId") int modelId){
		if (modelService.get(modelId) != null) {
			Model model = modelService.get(modelId);
			List<Extras> extras = extrasService.getAll();
			ModelAndExtras response = new ModelAndExtras();
			response.setModel(model);
			response.setExtras(extras);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value="/example", method=RequestMethod.GET)
	public ModelAndView getExamplePage(){
		ModelAndView view = new ModelAndView("payment/example");
		return view;
	}
	
	@RequestMapping(value="/details-payment", method=RequestMethod.GET)
	public ResponseEntity<PayPalTransaction> detailsPayment(@RequestParam("token") String token, @RequestParam("PayerID") String PayerID) throws Exception{
		
		try{
			PayPalTransaction transaction = paypalService.getTransactionDetails(token);
			return ResponseEntity.ok(transaction);
		}
		catch(Exception ex){
			log.error(ex.getMessage());
			throw ex;
		}
	}
	
	@RequestMapping(value="/confirm-transaction", method=RequestMethod.POST)
	public ResponseEntity<String> confirmTransaction(@RequestBody Reservation reservation){
		try{
			
			String token = reservation.getToken();
			String PayerID = reservation.getPayerId();
			String itemTotal = reservation.getItemTotal();
			String orderTotal = reservation.getOrderTotal();
			BookingModel bookingCar = reservation.getBookingModel();
			int clientId = reservation.getClientId();
			List<Extras> extras = reservation.getExtras();
			PayPalTransaction transaction = paypalService.confirmTransaction(token, PayerID, itemTotal, orderTotal);
			switch(transaction.getAck()){
			case "SUCCESS":
				String transactionId = transaction.getTransactionID();
				Client client = (Client)userService.get(clientId);
				bookedService.registerBook(bookingCar, client, transactionId, transaction.getPayerPayPalID(), extras);
				
				return ResponseEntity.ok("Transaction complete!");
				
			case "FAILURE":
				String error = transaction.getErrorMessage();
				log.error(error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
				
			default:
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
			}
			
			
		}
		catch(Exception ex){
			log.error(ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
		}
	}
	
	@RequestMapping(value="/paypal-transaction-flow", method=RequestMethod.GET)
	public String paypalTransactionFlow(){
		return "payment/paypal_iframe_transaction";
	}
	
	@RequestMapping(value="/cancel-payment", method=RequestMethod.GET)
	public String paypalCancelPayment(String token){
		return "payment/paypal_iframe_transaction";
	}
	
	@RequestMapping(value="/accept-payment", method=RequestMethod.GET)
	public String paypalAceptPayment(String token){
		return "payment/paypal_iframe_transaction";
	}
	
	private void validateItemList(List<TransactionItem> listItem) throws Exception{
		for(TransactionItem item : listItem){
			if(item.getName() == null || item.getName().equals("")){
				throw new Exception("An item name is empty");
			}
			String amount = item.getAmount();
			if(amount == null || amount.equals("")){
				throw new Exception("An item amount is empty");
			}
			Double.parseDouble(amount);
			int quantity = item.getQuantity();
			if(quantity < 0){
				throw new Exception("The quantity value is lower than 0");
			}
		}
	}
}

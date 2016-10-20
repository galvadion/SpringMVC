package com.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.models.TransactionItem;
import com.services.PayPalService;

@Controller
@RequestMapping(value="payment")
public class PaymentTransactionController {

	static Logger log = Logger.getLogger(PaymentTransactionController.class.getName());

	@Autowired
	PayPalService paypalService;

	@RequestMapping(value="/start-paypal",method =RequestMethod.GET)
	public Map<String, Object> paypalPaymentBegin(){
		
		Map<String, Object> map = new HashMap<String, Object>();

		// El metodo debe recibir una lista TransactionItems, validar su contenido y luego enviarlo al paypalService
		try{
			// HARDCODED *************************

			TransactionItem item = new TransactionItem();
			item.setAmount(12450.5);
			item.setDescripcion("Alquiler BMW Z4 x 1 semana");

			List<TransactionItem> itemsList = new ArrayList<TransactionItem>();
			itemsList.add(item);

			// *************************************

			String token = paypalService.beginTransaction(itemsList);
			
			map.put("status", "200");
			map.put("token", token);

			return map;
		}
		catch(Exception ex){
			log.error(ex.getMessage());
			map.put("status", "500");
			map.put("message", "An error has ocurred");
			return map;
		}
	}

}

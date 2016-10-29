package com.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jboss.logging.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.models.TransactionItem;
import com.services.PayPalService;

@Controller
@RequestMapping(value="payment")
public class PaymentTransactionController {

	static Logger log = Logger.getLogger(PaymentTransactionController.class.getName());

	@Resource(name="PayPalServiceImpl")
	PayPalService paypalService;

	@RequestMapping(value="/start-paypal",method =RequestMethod.POST)
	public Map<String, Object> paypalPaymentBegin(){
		
		Map<String, Object> map = new HashMap<String, Object>();

		// El metodo debe recibir una lista TransactionItems, validar su contenido y luego enviarlo al paypalService
		try{
			// HARDCODED *************************

			TransactionItem item = new TransactionItem();
			item.setAmount("12450.50");
			item.setName("Alquiler BMW Z4 x 1 semana");
			item.setQuantity(2);
			
			List<TransactionItem> itemsList = new ArrayList<TransactionItem>();
			itemsList.add(item);

			// *************************************

			Map<String, String> response = paypalService.beginTransaction(itemsList);
			
			switch (response.get("ack")) {
			case "success" :
				map.put("status", "200");
				map.put("token", response.get("token"));
				break;
			
			case "failure" :
				map.put("status", "500");
				map.put("message", "An error has ocurred");
				log.error(response.get("message"));
			case "none" : 
				map.put("status", "500");
				map.put("message","An error has ocurred");
				log.error(response.get("message"));
			default:
				map.put("status", "500");
				map.put("message", "An error has ocurred");
				break;
			}

			return map;
		}
		catch(Exception ex){
			log.error(ex.getMessage());
			map.put("status", "500");
			map.put("message", "An error has ocurred");
			return map;
		}
	}

	@RequestMapping(value="/example", method=RequestMethod.GET)
	public ModelAndView getExamplePage(){
		ModelAndView view = new ModelAndView("payment/example");
		return view;
	}
}

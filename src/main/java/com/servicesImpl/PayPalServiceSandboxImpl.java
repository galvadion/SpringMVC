package com.servicesImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

import com.models.TransactionItem;
import com.paypalnvp.core.PayPal;
import com.paypalnvp.fields.Currency;
import com.paypalnvp.fields.Payment;
import com.paypalnvp.fields.PaymentItem;
import com.paypalnvp.profile.BaseProfile;
import com.paypalnvp.profile.Profile;
import com.paypalnvp.request.SetExpressCheckout;
import com.services.PayPalService;

@Service
@PropertySource("classpath:config.properties")
public class PayPalServiceSandboxImpl implements PayPalService {

	private PayPal payPal;
	
	@Autowired
	Environment env;
	
	public String beginTransaction(List<TransactionItem> items){
		
		Profile user = new BaseProfile.Builder(env.getProperty("PayPalUser"), env.getProperty("PayPalPass"))
				.signature(env.getProperty("PayPalSignature")).build();
		payPal = new PayPal(user,PayPal.Environment.SANDBOX);
		
		Integer size = items.size();
		PaymentItem[] payItems = new PaymentItem[size];
		Integer index = 0;
		for(TransactionItem item : items)
		{
			PaymentItem p = new PaymentItem();
			p.setDescription(item.getDescripcion());
			p.setAmount(item.getAmount().toString());
			payItems[index] = p;
			index++;
		}
		
		Payment payment = new Payment(payItems);
		payment.setCurrency(Currency.UYU); // HARCODED!!! Cambiar por parametro a eleccion de usuario
		
		SetExpressCheckout setEC = new SetExpressCheckout(payment, "https://localhost/SpringMvc/confirm", "https://localhost/SpringMvc/cancel");
		
		payPal.setResponse(setEC);
		
		Map<String, String> response = setEC.getNVPResponse();
		return response.get("token");
	}
}

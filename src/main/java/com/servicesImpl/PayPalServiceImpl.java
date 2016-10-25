package com.servicesImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.models.TransactionItem;
import com.services.PayPalService;

import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AckCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

@Component("PayPalServiceImpl")
@PropertySource("classpath:config.properties")
public class PayPalServiceImpl implements PayPalService {

	@Autowired
	Environment env;
	
	static Logger log = Logger.getLogger(PayPalServiceImpl.class.getName());

	public Map<String, String> beginTransaction(List<TransactionItem> items) throws Exception{
		try{

			PaymentDetailsType paymentDetails = new PaymentDetailsType();
			paymentDetails.setPaymentAction(PaymentActionCodeType.fromValue("Sale"));

			List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();

			double total = 0;

			for(TransactionItem transactionItem : items){
				BasicAmountType amt = new BasicAmountType();
				amt.setCurrencyID(CurrencyCodeType.USD);
				amt.setValue(transactionItem.getAmount());
				PaymentDetailsItemType item = new PaymentDetailsItemType();
				item.setQuantity(transactionItem.getQuantity());
				item.setName(transactionItem.getName());
				item.setAmount(amt);

				lineItems.add(item);

				total = total + (transactionItem.getQuantity() * Double.parseDouble(transactionItem.getAmount()));
			}

			paymentDetails.setPaymentDetailsItem(lineItems);
			BasicAmountType orderTotal = new BasicAmountType();
			orderTotal.setCurrencyID(CurrencyCodeType.fromValue("USD"));
			orderTotal.setValue(String.valueOf(total));
			paymentDetails.setOrderTotal(orderTotal);
			List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();
			paymentDetailsList.add(paymentDetails);

			SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetails  = new SetExpressCheckoutRequestDetailsType();
			setExpressCheckoutRequestDetails .setReturnURL(env.getProperty("PayPalReturnUrl"));
			setExpressCheckoutRequestDetails .setCancelURL(env.getProperty("PayPalCancelUrl"));

			setExpressCheckoutRequestDetails .setPaymentDetails(paymentDetailsList);

			SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(setExpressCheckoutRequestDetails );
			setExpressCheckoutRequest.setVersion("204.0");

			SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
			setExpressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutRequest);

			Map<String, String> sdkConfig = new HashMap<String, String>();
			sdkConfig.put("mode", "sandbox");
			sdkConfig.put("acct1.UserName", env.getProperty("PayPalUser"));
			sdkConfig.put("acct1.Password", env.getProperty("PayPalPass"));
			sdkConfig.put("acct1.Signature", env.getProperty("PayPalSignature"));

			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
			SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(setExpressCheckoutReq);

			Map<String, String> response = new HashMap<String, String>();

			AckCodeType ack = setExpressCheckoutResponse.getAck();
			System.out.println(ack.name());
			switch (ack.name()) {
			case "SUCCESS":
				System.out.println(setExpressCheckoutResponse.getToken());
				response.put("ack", "success");
				response.put("token", setExpressCheckoutResponse.getToken());
				response.put("corrrelationId", setExpressCheckoutResponse.getCorrelationID());
				response.put("version", setExpressCheckoutResponse.getVersion());
				break;
			case "FAILURE" :
				response.put("ack", "failure");
				List<ErrorType> errors = setExpressCheckoutResponse.getErrors();
				String message = "";
				for(ErrorType error : errors){
					message += "Error code: " + error.getErrorCode() + "; Short-Message: " + error.getShortMessage() + "; Long-Message: " + error.getLongMessage() + "| ";
				}
				response.put("message", message);
				response.put("version", setExpressCheckoutResponse.getVersion());
				break;

			default:
				response.put("ack", "none");
				response.put("message", "Implementation error");
				break;
			}
			return response;
		}
		catch(Exception ex){
			log.error(ex);
			throw new Exception(ex.getMessage());
		}
	}
}

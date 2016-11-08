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

import com.models.PayPalTransaction;
import com.models.TransactionItem;
import com.services.PayPalService;

import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AckCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;
import urn.ebay.apis.eBLBaseComponents.GetExpressCheckoutDetailsResponseDetailsType;
import urn.ebay.apis.eBLBaseComponents.PayPalUserStatusCodeType;
import urn.ebay.apis.eBLBaseComponents.PayerInfoType;
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
			switch (ack.name()) {
			case "SUCCESS":
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

	public PayPalTransaction getTransactionDetails(String token) throws Exception{

		try{
			GetExpressCheckoutDetailsRequestType getExpressCheckoutDetailsRequest = new GetExpressCheckoutDetailsRequestType(token);
			getExpressCheckoutDetailsRequest.setVersion("204.0");

			GetExpressCheckoutDetailsReq getExpressCheckoutDetailsReq = new GetExpressCheckoutDetailsReq();
			getExpressCheckoutDetailsReq.setGetExpressCheckoutDetailsRequest(getExpressCheckoutDetailsRequest);

			Map<String, String> sdkConfig = new HashMap<String, String>();
			sdkConfig.put("mode", "sandbox");
			sdkConfig.put("acct1.UserName", env.getProperty("PayPalUser"));
			sdkConfig.put("acct1.Password", env.getProperty("PayPalPass"));
			sdkConfig.put("acct1.Signature", env.getProperty("PayPalSignature"));
			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
			GetExpressCheckoutDetailsResponseType getExpressCheckoutDetailsResponse = service.getExpressCheckoutDetails(getExpressCheckoutDetailsReq);
			/***
			 * For details of GetExpressCheckoutDetails API Operation search in: 
			 * https://developer.paypal.com/docs/classic/api/merchant/GetExpressCheckoutDetails_API_Operation_SOAP
			 */

			AckCodeType ack = getExpressCheckoutDetailsResponse.getAck();
			PayPalTransaction transaction = new PayPalTransaction();
			switch(ack.name()){

			case "SUCCESS": 

				GetExpressCheckoutDetailsResponseDetailsType checkoutDetailsResponse = getExpressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails();
				PayerInfoType payer = checkoutDetailsResponse.getPayerInfo();

				transaction.setAck("success");
				transaction.setPayerMarketingEmail(checkoutDetailsResponse.getBuyerMarketingEmail());
				transaction.setCheckoutStatus(checkoutDetailsResponse.getCheckoutStatus());
				transaction.setInvoiceID(checkoutDetailsResponse.getInvoiceID());
				transaction.setContactPhone(checkoutDetailsResponse.getContactPhone());
				transaction.setPayerFirsName(payer.getPayerName().getFirstName());
				transaction.setPayerLastName(payer.getPayerName().getLastName());
				transaction.setPayerPayPalID(payer.getPayerID());
				transaction.setPayerStatus(payer.getPayerStatus().toString());

				List<PaymentDetailsType> detailsList = checkoutDetailsResponse.getPaymentDetails();

				// For loop only would apply for parallel payments. Change if required
				for(PaymentDetailsType detail : detailsList){

					transaction.setItemTotal(detail.getItemTotal().getValue());;
					transaction.setOrderTotal(detail.getOrderTotal().getValue());

					List<PaymentDetailsItemType> detailsItemList = detail.getPaymentDetailsItem();
					List<TransactionItem> transactionItems = new ArrayList<TransactionItem>();

					for(PaymentDetailsItemType detailsItemType : detailsItemList){
						TransactionItem item = new TransactionItem();
						item.setName(detailsItemType.getName());
						item.setAmount(detailsItemType.getAmount().getValue());
						item.setQuantity(detailsItemType.getQuantity());
						transactionItems.add(item);
					}
					transaction.setItemsList(transactionItems);
				}

				return transaction;
			case "FAILURE":
				transaction.setAck("failure");
				String errors = "";
				for(ErrorType errorList : getExpressCheckoutDetailsResponse.getErrors()){
					if(errors.equals("")){
						errors = errorList.getLongMessage();
					}
					else{
						errors += " - " + errorList.getLongMessage();
					}
				}
				transaction.setErrorMessage(errors);
				return transaction;

			default :
				throw new Exception("An error has occurred");
			}

		}
		catch(Exception ex){
			log.error(ex);
			throw new Exception(ex.getMessage());
		}

	}
}

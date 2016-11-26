package com.models;

import java.util.List;

public class PayPalTransaction {
	
	// Transaction identification number of the transaction that was created.
	// Note: This field is only returned after a successful transaction for DoExpressCheckout has occurred.
	private String transactionID;
	
	private List<TransactionItem> itemsList;
	
	// Your own invoice or tracking number, as set by you in the element of the same name in the SetExpressCheckout request.
	private String invoiceID;
	
	private String orderTotal;
	
	private String currency;
	
	private String itemTotal;
	
	/**
	 * Status of the checkout session. If payment is completed, the transaction identification number
	 * of the resulting transaction is returned. It is one of the following values:
			PaymentActionNotInitiated
			PaymentActionFailed
			PaymentActionInProgress
			PaymentActionCompleted
	 */
	private String checkoutStatus;
	
	// Payer's email address if the buyer provided it on the PayPal pages.
	private String payerMarketingEmail;
	
	// Buyer's contact phone number
	private String contactPhone;
	
	// Status of buyer. It is one of the following values: verified - unverified
	private String payerStatus;
	
	// Unique PayPal Customer Account identification number.
	private String payerPayPalID;
	
	// First name of buyer
	private String payerFirsName;
	
	// Last name of buyer
	private String payerLastName;
	
	private String ack;
	
	// In case of failure
	private String errorMessage;
	
	public String getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public List<TransactionItem> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<TransactionItem> itemsList) {
		this.itemsList = itemsList;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(String itemTotal) {
		this.itemTotal = itemTotal;
	}

	public String getCheckoutStatus() {
		return checkoutStatus;
	}

	public void setCheckoutStatus(String checkoutStatus) {
		this.checkoutStatus = checkoutStatus;
	}

	public String getPayerMarketingEmail() {
		return payerMarketingEmail;
	}

	public void setPayerMarketingEmail(String payerMarketingEmail) {
		this.payerMarketingEmail = payerMarketingEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getPayerStatus() {
		return payerStatus;
	}

	public void setPayerStatus(String payerStatus) {
		this.payerStatus = payerStatus;
	}

	public String getPayerPayPalID() {
		return payerPayPalID;
	}

	public void setPayerPayPalID(String payerPayPalID) {
		this.payerPayPalID = payerPayPalID;
	}

	public String getPayerFirsName() {
		return payerFirsName;
	}

	public void setPayerFirsName(String payerFirsName) {
		this.payerFirsName = payerFirsName;
	}

	public String getPayerLastName() {
		return payerLastName;
	}

	public void setPayerLastName(String payerLastName) {
		this.payerLastName = payerLastName;
	}

	public String getAck() {
		return ack;
	}

	public void setAck(String ack) {
		this.ack = ack;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}

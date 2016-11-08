package com.services;

import java.util.List;
import java.util.Map;

import com.models.PayPalTransaction;
import com.models.TransactionItem;

public interface PayPalService  {
	
	/**
	 * Initialize the process of transaction with paypal Server
	 * @return Token from PayPal for application user client use
	 * @throws Exception 
	 */
	Map<String, String> beginTransaction(List<TransactionItem> items) throws Exception;
	
	PayPalTransaction getTransactionDetails(String token) throws Exception;
}

package com.services;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.models.PayPalTransaction;
import com.models.TransactionItem;

public interface PayPalService  {
	
	/**
	 * Initialize the process of transaction with paypal Server
	 * @return Token from PayPal for application user client use
	 * @throws Exception 
	 */
	Map<String, String> beginTransaction(List<TransactionItem> items,HttpServletRequest request) throws Exception;
	
	/**
	 * Get payment details made by application customer
	 * @param token
	 * @return
	 * @throws Exception
	 */
	PayPalTransaction getTransactionDetails(String token) throws Exception;
	
	/**
	 * Confirms transaction done by customer to paypal serer
	 * @param token
	 * @param payerId
	 * @param itemTotal
	 * @param orderTotal
	 * @return
	 * @throws Exception
	 */
	PayPalTransaction confirmTransaction(String token, String payerId, String itemTotal, String orderTotal) throws Exception;
}

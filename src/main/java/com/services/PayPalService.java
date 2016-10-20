package com.services;

import java.util.List;

import com.models.TransactionItem;

public interface PayPalService {
	
	/**
	 * Initialize the process of transaction with paypal Server
	 * @return Token from PayPal for application user client use
	 */
	String beginTransaction(List<TransactionItem> items);
}

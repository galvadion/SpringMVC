(function(){
	'use strict';
	
	angular
		.module('app')
		.factory('PaymentService', PaymentService);
	
	PaymentService.$inject = ['$http'];
	
	function PaymentService($http){
		
		var myCart = new shoppingCart("example");
		
		myCart.addCheckoutParameters("PayPal", "info-rentuy@gmail.com");
		
		//*****************************************
		// Item HARDCODED
		myCart.addItem(1, "BMW z4", 1000, 1);
		myCart.addItem(2, "GPS", 5, 1);
		//*****************************************
		
		return {
			cart: myCart
		};
	}
})();
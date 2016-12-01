(function(){
	'use strict';
	
	angular
		.module('app')
		.factory('PaymentService', PaymentService);
	
	PaymentService.$inject = ['$http'];
	
	function PaymentService($http){
		
		var myCart = new shoppingCart("example");
		
		var GetPaymentDetails = GetPaymentDetails;
		var ConfirmPayment = ConfirmPayment;
		
		myCart.addCheckoutParameters("PayPal", "info-rentuy@gmail.com");
		
		//*****************************************
		// Item HARDCODED
		myCart.addItem(1, "BMW z4", 1000, 1);
		//*****************************************
		
		return {
			cart: myCart,
			GetPaymentDetails,
			ConfirmPayment
		};
		
		function GetPaymentDetails(token, payerId){
			return $http.get('/SpringMVC/payment/details-payment?token=' + token +"&PayerID=" + payerId).then(handleSuccess, handleError);
		}
		
		function ConfirmPayment(token, payerId, itemTotal, orderTotal){
			return $http.get('/SpringMVC/payment/confirm-transaction?token='+token+'&PayerID='+payerId+"&itemTotal="+itemTotal+"&orderTotal="+orderTotal).then(handleSuccess, handleError);
		}
		
		// private functions

        function handleSuccess(data) {
        	var response = {};
        	response.success = true;
    		response.data = data.data;
            return response;
        }
        
        function handleError(data) {
        	var response = {};
    		response.success = false;
    		response.data = data.data;
            return response;
        }
	}
})();
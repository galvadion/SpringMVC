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
		var GetModelDetails = GetModelDetails;
		
		myCart.addCheckoutParameters("PayPal", "info-rentuy@gmail.com");
		
		return {
			cart: myCart,
			GetPaymentDetails,
			ConfirmPayment,
			GetModelDetails
		};
		
		function GetModelDetails(modelId){
			return $http.get('/SpringMVC/payment/model?modelId=' + modelId).then(handleSuccess, handleError);
		}
		
		function GetPaymentDetails(token, payerId){
			return $http.get('/SpringMVC/payment/details-payment?token=' + token +"&PayerID=" + payerId).then(handleSuccess, handleError);
		}
		
		function ConfirmPayment(data){
			return $http.post('/SpringMVC/payment/confirm-transaction', data).then(handleSuccess, handleError);
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
(function(){
	'use strict';
	
	angular
		.module('app')
		.factory('PaymentService', PaymentService);
	
	PaymentService.$inject = ['$http'];
	
	function PaymentService($http){
		var service = {};
		
		
	}
})
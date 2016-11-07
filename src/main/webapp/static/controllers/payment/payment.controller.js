(function(){
	'use strict';
	
	angular
		.module('app')
		.controller('PaymentController', PaymentController);
	
	PaymentController.$inject = ['$location','$rootScope','$scope', 'PaymentService'];
	
	function PaymentController($location, $rootScope, $scope, PaymentService){
		
		var vm = this;
		
		initController();
		
		$scope.cart = PaymentService.cart;
	}
	
	function initController(){
		NProgress.start();
		NProgress.done();
	}
	
})();
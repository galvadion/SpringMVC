(function(){
	'use strict';
	
	angular
		.module('app')
		.controller('PaymentController', PaymentController);
	
	PaymentController.$inject = ['$location','$rootScope','$scope', 'PaymentService'];
	
	function PaymentController($location, $rootScope, $scope, PaymentService){
		
		var vm = this;
		
		var token, payerId;
		
		vm.booleano = true;
		
		vm.items = [];
		
		vm.itemTotal;
		
		vm.orderTotal;
		
		initController();
		
		$scope.cart = PaymentService.cart;
		
		function initController(){
			NProgress.start();
			NProgress.done();
		}
		
		$scope.getDetails = function() {
			token = $("#token").val();
			payerId = $("#payerId").val();
			NProgress.start();
			PaymentService.GetPaymentDetails(token, payerId).then(function(response){
				if(response.success){
					vm.items = response.data.itemsList;
					vm.itemTotal = response.data.itemTotal;
					vm.orderTotal = response.data.orderTotal;
				}
			})
			$("#token").remove();
			$("#payerId").remove();
			vm.booleano = false;
			NProgress.done();
			
		}
		
		$scope.ConfirmBooking = function(){
			NProgress.start();
			PaymentService.ConfirmPayment(token, payerId, vm.itemTotal, vm.orderTotal).then(function(response){
				if(response.success){
					alert(response.data);
				}
				else{
					alert(response.data);
				}
			})
			NProgress.done();
		}
		
		$scope.CancelBooking = function(){
			vm.booleano = true;
		}
	}
	
	
	
})();
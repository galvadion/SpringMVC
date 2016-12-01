(function(){
	'use strict';
	
	angular
		.module('app')
		.controller('PaymentController', PaymentController);
	
	PaymentController.$inject = ['$location','$rootScope','$scope', 'PaymentService', '$routeParams'];
	
	function PaymentController($location, $rootScope, $scope, PaymentService, $routeParams){
		
		var vm = this;
		
		var token, payerId;
		
		vm.estado = "booking";
		
		vm.items = [];
		
		vm.itemTotal;
		
		vm.orderTotal;
		
		vm.initDate;
		vm.endDate;
		vm.gpsCheck = false;
		vm.gpsPrice = "4.00";
		vm.insuranceCheck = false;
		vm.insurancePrice = "20.00";
		vm.fulltankCheck = false;
		vm.fulltankPrice = "6.00";
		vm.babySeat1 = 0;
		vm.babySeat2 = 0;
		vm.babySeat3 = 0;
		
		console.log("Initial Date: " + $rootScope.dateInitial);
		console.log("EndDate: " + $rootScope.dateEnding);
		console.log("InitialOfficeId: " + $rootScope.officeInitial);
		console.log("EndOfficeId: " + $rootScope.officeEnding);
		
		initController();
		
		$scope.cart = PaymentService.cart;
		
		function initController(){
			NProgress.start();	
			NProgress.done();
		}
		
		function getModelCarDetails(){
			
		}
		
		$scope.addItem = function(id, name, price, checked){
			if(checked){
				$scope.cart.addItem(id, name, price, 1);
			}
			else{
				$scope.cart.addItem(id, name, price, -1);
			}
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
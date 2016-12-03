(function(){
	'use strict';
	
	angular
		.module('app')
		.controller('PaymentController', PaymentController);
	
	PaymentController.$inject = ['$location','$rootScope','$scope', 'PaymentService', '$routeParams'];
	
	function PaymentController($location, $rootScope, $scope, PaymentService, $routeParams){
		
		var vm = this;
		
		var token, payerId;
		
		vm.estado = "";
		vm.items = [];
		vm.itemTotal;
		vm.orderTotal;
		vm.initDate;
		vm.endDate;
		vm.gpsCheck = false;
		vm.gpsPrice = "4.00";
		vm.insuranceCheck = false;
		vm.fulltankCheck = false;
		vm.babySeat1 = 0;
		vm.babySeat2 = 0;
		vm.babySeat3 = 0;
		vm.bookingDays = 0;
		
		$scope.model;
		$scope.gpsPrice = vm.gpsPrice;
		$scope.insurancePrice;
		$scope.fulltankPrice;
		
		console.log("Model Id: " + $rootScope.modelId);
		console.log("Initial Date: " + $rootScope.dateInitial);
		console.log("EndDate: " + $rootScope.dateEnding);
		console.log("InitialOfficeId: " + $rootScope.officeInitial);
		console.log("EndOfficeId: " + $rootScope.officeEnding);
		
		initController();
		
		$scope.cart = PaymentService.cart;
		
		function initController(){
			NProgress.start();
			getModelCarDetails($rootScope.modelId);
			NProgress.done();
		}
		
		function getModelCarDetails(id){
			if(id == null){
				$location.path("/");
			}
			else{
				PaymentService.GetModelDetails(id).then(function(response){
					if(response.success){
						$scope.model = response.data;
						console.log($scope.model);
						$scope.insurancePrice = $scope.model.insurance;
						$scope.fulltankPrice = $scope.model.fullTank;
						vm.bookingDays = Math.round(((new Date($rootScope.dateEnding.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$2/$1/$3")).getTime()) - (new Date($rootScope.dateInitial.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$2/$1/$3")).getTime()))/(1000*60*60*24));
						$scope.cart.addItem($scope.model.id, $scope.model.brand.name + " " + $scope.model.name, $scope.model.category.basePrice, vm.bookingDays);
						vm.estado = "booking";
					}
					else{
						alert(response.data);
					}
			
				})
			}
		}
		
		$scope.volver = function(){
			$location.path("/search/origin=" + $rootScope.officeInitial + "&destination=" + $rootScope.officeEnding + "&from=" + $rootScope.dateInitial + "&to=" + $rootScope.dateEnding);
		}
		
		$scope.addItem = function(id, name, price, checked){
			var quantity = 0;
			if(id == -3){
				quantity = 1;
			}
			else{
				quantity = vm.bookingDays;
			}
			if(checked){
				$scope.cart.addItem(id, name, price, quantity);
			}
			else{
				$scope.cart.addItem(id, name, price, -1 * quantity);
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
			vm.estado = "details";
			NProgress.done();
			
		}
		
		$scope.ConfirmBooking = function(){
			NProgress.start();
			var booking = {	'idModel' : $scope.model.id, 
							'startDate' : ($rootScope.dateInitial.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$1/$2/$3")),
							'endDate' : ($rootScope.dateEnding.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$1/$2/$3")),
							'originBranchOfficeId' : $rootScope.officeInitial,
							'endBranchOfficeId' : $rootScope.officeEnding,
							'withGps' : vm.gpsCheck,
							'withInsurance' : vm.insuranceCheck,
							'withFullTank' : $scope.fulltankPrice
						   };
			var data = {
					"bookingModel" : booking,
					"token" : token,
					"payerId" : payerId,
					"itemTotal" : vm.itemTotal,
					"orderTotal" : vm.orderTotal,
					"clientId" : $rootScope.globals.currentUser.id
			}
			console.log($rootScope.globals.currentUser.id);
			PaymentService.ConfirmPayment(data).then(function(response){
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
			vm.estado = "booking";
		}
	}
	
	
	
})();
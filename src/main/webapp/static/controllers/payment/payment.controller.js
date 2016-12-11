(function(){
	'use strict';
	
	angular
		.module('app')
		.controller('PaymentController', PaymentController);
	
	PaymentController.$inject = ['$location','$rootScope','$scope', 'PaymentService', '$routeParams', 'AuthenticationService'];
	
	function PaymentController($location, $rootScope, $scope, PaymentService, $routeParams, AuthenticationService){
		
		var vm = this;
		
		var token, payerId;
		
		vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
		vm.estado = "";
		vm.items = [];
		vm.itemTotal;
		vm.orderTotal;
		vm.initDate = "";
		vm.endDate = "";
		vm.insuranceCheck = false;
		vm.fulltankCheck = false;
		vm.babySeat1 = 0;
		vm.babySeat2 = 0;
		vm.babySeat3 = 0;
		vm.bookingDays = 0;
		
		$scope.model;
		$scope.insurancePrice;
		$scope.fulltankPrice;
		$scope.extras = [];
		
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
				vm.initDate = $rootScope.dateInitial;
				vm.endDate = $rootScope.dateEnding;
				PaymentService.GetModelDetails(id).then(function(response){
					if(response.success){
						$scope.model = response.data.model;
						$scope.insurancePrice = $scope.model.insurance;
						$scope.fulltankPrice = $scope.model.fullTank;
						vm.bookingDays = Math.round(((new Date(vm.endDate.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$2/$1/$3")).getTime()) - (new Date(vm.initDate.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$2/$1/$3")).getTime()))/(1000*60*60*24));
						$scope.cart.addItem($scope.model.id, $scope.model.brand.name + " " + $scope.model.name, $scope.model.category.basePrice, vm.bookingDays);
						var extras = response.data.extras;
						for(var i in extras){
							$scope.extras.push(new ExtraItems(extras[i].id, extras[i].name, extras[i].price, false));
						}
						console.log($scope.model);
						vm.estado = "booking";
						//vm.estado = "confirm";
					}
					else{
						$rootScope.doFlash(response.data, "", "error", 6);
					}
			
				})
			}
		}
		
		$scope.loginModal = function(){
			AuthenticationService.ClearCredentials();
			$("#loginModal").modal();
		}
		
		$scope.volver = function(){
			$location.path("/search/origin=" + $rootScope.officeInitial + "&destination=" + $rootScope.officeEnding + "&from=" + $rootScope.dateInitial.replace(/(\d{2})[/](\d{2})[/](\d{4})/, "$1-$2-$3") + "&to=" + $rootScope.dateEnding.replace(/(\d{2})[/](\d{2})[/](\d{4})/, "$1-$2-$3"));
		}
		
		$scope.login = function(){
			AuthenticationService.Login(vm.email, vm.password).then(function (response) {
				if(response.success) {
					AuthenticationService.SetCredentials(response.data.id, response.data.email, response.data.name, response.data.rol, response.data.password);
					$rootScope.doFlashMessage('Bienvenido!','','success');
					vm.roladmin = $rootScope.roladmin;
			        vm.rolemployee = $rootScope.rolemployee;
			        vm.rolclient = $rootScope.rolclient;
			        $("#loginModal").modal("hide");
				}
				else{
					vm.error = "Usuario o contraseña incorrecta"
				}
			})
		}
		
		$scope.addItem = function(id, name, price, checked){
			var quantity = 0;
			if(id == "FullTank"){
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
					$("#token").remove();
					$("#payerId").remove();
					vm.estado = "details";
					
				}
				else{
					$rootScope.doFlash(response.data, "", "error", 6);
				}
			})
			NProgress.done();
			
		}
		
		$scope.ConfirmBooking = function(){
			NProgress.start();
			var selectedExtras = [];
			for(var i in $scope.extras){
				if($scope.extras[i].checked){
					selectedExtras.push({ id : $scope.extras[i].id, name : $scope.extras[i].name, price : $scope.extras[i].price});
				}
			}
			var booking = {	'idModel' : $scope.model.id, 
							'startDate' : (vm.initDate.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$1/$2/$3")),
							'endDate' : (vm.endDate.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$1/$2/$3")),
							'originBranchOfficeId' : $rootScope.officeInitial,
							'endBranchOfficeId' : $rootScope.officeEnding,
							'withInsurance' : vm.insuranceCheck,
							'withFullTank' : $scope.fulltankPrice
						   };
			var data = {
					"bookingModel" : booking,
					"token" : token,
					"payerId" : payerId,
					"itemTotal" : vm.itemTotal,
					"orderTotal" : vm.orderTotal,
					'extras' : selectedExtras,
					"clientId" : $rootScope.globals.currentUser.id
			}
			PaymentService.ConfirmPayment(data).then(function(response){
				if(response.success){
					vm.estado = "confirm";
				}
				else{
					$rootScope.doFlash(response.data, "", "error", 6);
				}
			})
			NProgress.done();
		}
		
		$scope.CancelBooking = function(){
			vm.estado = "booking";
		}
	}
	
	function ExtraItems(id, name, price, checked){
		this.id = id;
		this.name = name;
		this.price = price;
		this.checked = checked;
	}
	
	
	
})();
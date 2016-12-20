(function(){
	'use strict';
	
	angular
		.module('app')
		.controller('PaymentController', PaymentController);
	
	PaymentController.$inject = ['$location','$rootScope','$scope', 'PaymentService', '$routeParams', 'AuthenticationService','BookedService'];
	
	function PaymentController($location, $rootScope, $scope, PaymentService, $routeParams, AuthenticationService,BookedService){
		
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
		vm.percentage=0;
		vm.promotionCode="";
		vm.bookingDays = 0;
		vm.discount = 0;
		vm.isPromotion=false;
		vm.modelPrice=0;
		
		$scope.model;
		$scope.insurancePrice;
		$scope.fulltankPrice;
		$scope.extras = [];
		
		initController();
		
		$scope.cart = PaymentService.cart;
		$scope.cart.clearItems();
		
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
						vm.items = [];
						vm.itemTotal=0;
						vm.orderTotal=0;
						$scope.model = response.data.model;
						$scope.insurancePrice = $scope.model.insurance;
						vm.bookingDays = Math.round(((new Date(vm.endDate.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$2/$1/$3")).getTime()) - (new Date(vm.initDate.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$2/$1/$3")).getTime()))/(1000*60*60*24));
						$scope.fulltankPrice = -($scope.model.fuel.fuelPrice * vm.bookingDays);
						if(vm.bookingDays>1){
							vm.modelPrice= ($scope.model.category.basePrice+$scope.model.fuel.fuelPrice )*0.80;
						}else{
							vm.modelPrice=$scope.model.category.basePrice+$scope.model.fuel.fuelPrice;
						}
						$scope.cart.addItem($scope.model.id, $scope.model.brand.name + " " + $scope.model.name, vm.modelPrice, vm.bookingDays);
						var extras = response.data.extras;
						for(var i in extras){
							$scope.extras.push(new ExtraItems(extras[i].id, extras[i].name, extras[i].price, false));
						}
						$(".toggleCheck").bootstrapToggle();
						vm.estado = "booking";
//						vm.estado = "details";
					}
					else{
						$rootScope.doFlashMessage(response.data, "", "error", 60000);
					}
			
				})
			}
		}
		
		$scope.validatePromotion = function(){
        	vm.promo.model=$scope.model;
        	vm.promo.origin=$rootScope.officeInitial;
        	vm.promo.destiny=$rootScope.officeEnding;
        	vm.promo.originDate=vm.initDate.replace( /(\d{2})[/](\d{2})[/](\d{4})/, "$1/$2/$3");
        	BookedService.ValidatePromotion(vm.promo).then(function (response) {
        		if(response.success){
	        		vm.isPromotion=response.data.valid;	
	        		if(!vm.isPromotion){
	        			$rootScope.doFlashMessage(response.data.validationMessage, "", "error", 6000);
	        		}else{
	        			vm.percentage=response.data.percentage;
	        			vm.promotionCode=response.data.promotionCode;
	        			vm.discount += $scope.model.category.basePrice*vm.bookingDays*(vm.percentage/100);
	        			for(var i in vm.items){
	        				vm.discount+=i.price*(vm.percentage/100);
	        			}
	        			$scope.cart.addItem("discount", "Descuento del "+vm.percentage + "%", -vm.discount.toFixed(2), 1);
	        		}
        		}
        		else{
        		//	vm.allBookeds = [];
        			console.log("invalido")
        		}
        		NProgress.done();
        	});
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
				if(vm.isPromotion){
					$scope.cart.addItem("discount", "Descuento del "+vm.percentage + "%", vm.discount.toFixed(2), -1);
					vm.discount +=price*quantity*(vm.percentage/100);
					$scope.cart.addItem("discount", "Descuento del "+vm.percentage + "%",-vm.discount.toFixed(2), 1);
				}
			}
			else{
				$scope.cart.addItem(id, name, price, -1 * quantity);
				if(vm.isPromotion){
					$scope.cart.addItem("discount", "Descuento del "+vm.percentage + "%", vm.discount.toFixed(2) , -1);
					vm.discount -=price*quantity*(vm.percentage/100);
					$scope.cart.addItem("discount", "Descuento del "+vm.percentage + "%",-vm.discount.toFixed(2) , 1);
				}
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
					$("#errorModal").modal();
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
					"promotionCode" : vm.promotionCode,
					"clientId" : $rootScope.globals.currentUser.id
			}
			PaymentService.ConfirmPayment(data).then(function(response){
				if(response.success){
					vm.estado = "confirm";
				}
				else{
					$("#errorModal").modal();
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
	
	function ToogleCheck(){
		$(document).ready(function(){
			$(".toggleCheck").bootstrapToggle();
		})
	}
	
})();
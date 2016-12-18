(function () {
	'use strict';

	angular
	.module('app')
	.controller('RentController', RentController);

	RentController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','RentService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','BookedService','$routeParams'];

	function RentController($location, UserService, $rootScope, $scope, $timeout, SessionService, RentService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder,BookedService,$routeParams) {

		var vm = this;
		vm.roladmin = $rootScope.roladmin;
		vm.rolemployee = $rootScope.rolemployee;
		vm.rolclient = $rootScope.rolclient;
		vm.rent = {};
		vm.allRents = [];
		vm.booked={};
		vm.driver= {};
		vm.rent.driversList=[];
		vm.detail= {};
		vm.rent.rentLine=[];

		vm.dtOptions = DTOptionsBuilder.newOptions().withDOM('dfrtip')
		.withPaginationType('simple_numbers')
		.withDisplayLength(10)
		.withLanguage({
			"sEmptyTable":     "No hay entradas disponibles",
			"sInfo":           "Mostrando _START_ - _END_ de _TOTAL_ entradas",
			"sInfoEmpty":      "Mostrando 0 a 0 de 0 entradas",
			"sInfoFiltered":   "(filtrando desde _MAX_ entradas totales)",
			"sInfoPostFix":    "",
			"sInfoThousands":  ",",
			"sLengthMenu":     "_MENU_",
			"sLoadingRecords": "Cargando...",
			"sProcessing":     "Procesando...",
			"sSearch":         "",
			"sZeroRecords":    "No se encontraron resultados",
			'sSearchPlaceholder': "Buscar...",
			"paginate": {
				"first":      "Primera",
				"last":       "Última",
				"next":       "Siguiente",
				"previous":   "Anterior"
			},
		});
		
		var today = new Date();
        today = today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getFullYear()-18;
		
		
        $('#birthDate').datetimepicker({
        	language:  'es',
            weekStart: 1,
        	autoclose: 1,
        	todayHighlight: 1,
        	startView: 2,
        	minView: 2,
        	forceParse: 0,
        	endDate: today
        });
        
        $('#licenseExpirationDate').datetimepicker({
        	language:  'es',
            weekStart: 1,
        	autoclose: 1,
        	todayHighlight: 1,
        	startView: 2,
        	minView: 2,
        	forceParse: 0
        });

		vm.DTColumnDefs = [
		                   DTColumnDefBuilder.newColumnDef(9).notSortable(),
		                   DTColumnDefBuilder.newColumnDef(10).notSortable(),
		                   DTColumnDefBuilder.newColumnDef(11).notSortable(),
		                   ];

		initController();

		function initController() {
			NProgress.start();
			vm.location = $location.path().split('/',3);
			if(vm.location[2] == "confirm" || vm.location[2] == "return" ){
				getBookedById($routeParams.id);
				
			}
			else if(vm.location[2] == "details"){
        		getRentById($routeParams.id);
        	}
			else{
            	if(vm.roladmin || vm.rolemployee){
            		getAllRents();
            	}
            	else if(vm.rolclient){
            		getRentsByClient();
            	}
            	
            }
			NProgress.done();
		}

		function getAllRents(){
			RentService.GetAllRents().then(function (response) {
				if(response.success){
					vm.allRents = response.data;
				}
				else{
					vm.allRents = [];
				}
				NProgress.done();
			});
		}

		function getRentsByClient(){
			RentService.GetRentsByClient($rootScope.globals.currentUser.id).then(function (response) {
        		if(response.success){
        			vm.allRents = response.data;
        		}
        		else{
        			vm.allRents = [];
        		}
        		NProgress.done();
        	});
        }
		
		function getBookedById(id){
			BookedService.GetBookedById(id).then(function (response) {
				if(response.success){  
					vm.booked = response.data;
					vm.rent.booked_id=vm.booked.id;
					if( vm.location[2] == "return"){
						getRentById(vm.booked.rent);
						vm.rent.rentLine=[];
					}
				}
				else{
					vm.booked = [];
				}

				NProgress.done();
			});
		}
		
		function getRentById(id){
			RentService.GetRentById(id).then(function (response) {
				if(response.success){  
					vm.rent = response.data;
				}
				else{
					vm.booked = [];
				}

				NProgress.done();
			});
		}

		$scope.confirmRent = function() {
			NProgress.start();
			RentService.InsertRent(vm.rent).then(function (response) {
				if(response.success){
					getAllRents();
					$rootScope.doFlashMessage("Se ha confirmado correctamente con éxito",'/details/'+vm.rent.id,'success');
					$scope.cleanInput();
				}
				else{
					$rootScope.doFlashMessage("Error al confirmar",'','error');
				}
				NProgress.done();
			});
		};
		
		$scope.confirmReturn = function() {
			NProgress.start();
			RentService.confirmReturn(vm.rent).then(function (response) {
				if(response.success){
					getAllRents();
					$rootScope.doFlashMessage("Se ha confirmado correctamente con éxito",'/details/'+vm.rent.id,'success');
					$scope.cleanInput();
				}
				else{
					$rootScope.doFlashMessage("Error al confirmar",'','error');
				}
				NProgress.done();
			});
		};
		
		$scope.addFine = function(){
			vm.rent.rentLine.push(vm.detail);
			vm.detail={};
			
		}
		$scope.addDriver = function() {
            vm.rent.driversList.push(vm.driver);
            vm.driver={};
        }

		$scope.cleanInput = function() {
			vm.rent = {};
			$scope.form.$setPristine();
		};

		$scope.editRent = function(brand) {
			vm.rent = angular.copy(brand);
			$scope.scrollTo( "#wrap");
		};

		$scope.deleteRent = function(id) {
			NProgress.start();
			RentService.DeleteRent(id).then(function (response) {
				if(response.success){
					getAllRents();
					$rootScope.doFlashMessage('Marca eliminada','','success');
				}
				else{
					$rootScope.doFlashMessage('Error al eliminar','','error');
				}
				NProgress.done();
			});
		};

		$scope.scrollTo = function(element) {
			$( 'html, body').animate({
				scrollTop: $(element).offset().top
			}, 500);
		}
		


	}

})();
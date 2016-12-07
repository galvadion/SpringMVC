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

		vm.DTColumnDefs = [
		                   DTColumnDefBuilder.newColumnDef(1).notSortable(),
		                   DTColumnDefBuilder.newColumnDef(2).notSortable(),
		                   DTColumnDefBuilder.newColumnDef(3).notSortable(),
		                   ];

		initController();

		function initController() {
			NProgress.start();
			vm.location = $location.path().split('/',3);
			if(vm.location[2] == "confirm"){
				getBookedById($routeParams.id);
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

		function getBookedById(id){
			BookedService.GetBookedById(id).then(function (response) {
				if(response.success){  
					vm.booked = response.data;
					console.log(vm.booked)
				}
				else{
					vm.booked = [];
				}

				NProgress.done();
			});
		}

		$scope.saveRent = function() {
			NProgress.start();
			var mgsSuccess = "";
			var mgsError = "";

			if(vm.rent.id){
				mgsSuccess = "Marca editada con éxito";
				mgsError = "Error al editar marca";

			}
			else{
				mgsSuccess = "Marca creada con éxito";
				mgsError = "Marca ya existente";
			}

			RentService.InsertRent(vm.rent).then(function (response) {
				if(response.success){
					getAllRents();
					$rootScope.doFlashMessage(mgsSuccess,'','success');
					$scope.cleanInput();
				}
				else{
					$rootScope.doFlashMessage(mgsError,'','error');
				}
				NProgress.done();
			});
		};

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
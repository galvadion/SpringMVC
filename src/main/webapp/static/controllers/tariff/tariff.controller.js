(function () {
    'use strict';

    angular
        .module('app')
        .controller('TariffController', TariffController);

    TariffController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','TariffService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    
    function TariffController($location, UserService, $rootScope, $scope, $timeout, SessionService, TariffService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
        vm.category = {};
        vm.fuelType ={};
        vm.categoryList = [];
        vm.fuelTypeList =[];
        vm.extrasList=[];
        
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
            getAllCategories();
            getAllFuelTypes();
            getAllExtras();
            $scope.buttonFuelType="Crear";
            $scope.buttonCategory="Crear";
            $scope.buttonExtras="Crear";
        }
        
        function getAllCategories(){
        	TariffService.getAllCategories().then(function (response) {
        		if(response.success){
        			vm.categoryList = response.data;
        		}
        		else{
        			vm.categoryList = [];
        		}
        		NProgress.done();
        	});
        }
        
        function getAllExtras(){
        	        	TariffService.getAllExtras().then(function (response) {
        	        		if(response.success){
        	        			vm.extrasList = response.data;
        	       		}
        	        		else{
        	        			vm.extrasList = [];
        	        		}
        	        		NProgress.done();
        	        	});
        }
        
        function getAllFuelTypes(){
        	TariffService.getAllFuelTypes().then(function (response) {
        		if(response.success){
        			vm.fuelTypeList = response.data;
        		}
        		else{
        			vm.fuelTypeList = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveCategory = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.category.id){
        		mgsSuccess = "Categoria editada con éxito";
        		mgsError = "Categoria al editar marca";
        		 $scope.buttonCategory="Crear";
        	}
        	else{
        		mgsSuccess = "Categoria creada con éxito";
        		mgsError = "Categoria ya existente";
        	}
        	
        	TariffService.InsertCategory(vm.category).then(function (response) {
        		if(response.success){
        			getAllCategories();
        			$rootScope.doFlashMessage(mgsSuccess,'','success');
        			$scope.cleanInputCat();
        		}
        		else{
        			$rootScope.doFlashMessage(mgsError,'','error');
        		}
        		NProgress.done();
        	});
        };
        
        $scope.saveFuelType = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.fuelType.id){
        		mgsSuccess = "Tipo de combustible editada con éxito";
        		mgsError = "Tipo de combustible al editar marca";
        		 $scope.buttonFuelType="Crear";
        	}
        	else{
        		mgsSuccess = "Tipo de combustible creada con éxito";
        		mgsError = "Tipo de combustible ya existente";
        	}
        	
        	TariffService.InsertFuelType(vm.fuelType).then(function (response) {
        		if(response.success){
        			getAllFuelTypes();
        			$rootScope.doFlashMessage(mgsSuccess,'','success');
        			$scope.cleanInputFuel();
        		}
        		else{
        			$rootScope.doFlashMessage(mgsError,'','error');
        		}
        		NProgress.done();
        	});
        };
        
        $scope.saveExtra = function() {
           	NProgress.start();
           	var mgsSuccess = "";
           	var mgsError = "";
           	
           	if(vm.fuelType.id){
           		mgsSuccess = "Extra editada con éxito";
           		mgsError = "Error a editar el extra";
           		$scope.buttonExtras="Crear";
           	}
           	else{
           		mgsSuccess = "Extra creada con éxito";
           		mgsError = "Extra ya existente";
           	}
           	
           	TariffService.InsertExtra(vm.extra).then(function (response) {
           		if(response.success){
           			getAllExtras();
           			$rootScope.doFlashMessage(mgsSuccess,'','success');
           			$scope.cleanInputExtra();
           		}
           		else{
           			$rootScope.doFlashMessage(mgsError,'','error');
           		}
           		NProgress.done();
           	});
           };
        
        $scope.cleanInputExtra = function() {
        	vm.extra = {};
        	$scope.form-extra.$setPristine();
        };
        
        $scope.cleanInputFuel = function() {
        	vm.fuelType = {};
        	$scope.form-fuel.$setPristine();
        };
        
        $scope.cleanInputCat = function() {
        	vm.category = {};
        	$scope.form-cat.$setPristine();
        };
        
        
        $scope.deleteTariff = function(id) {
        	NProgress.start();
        	TariffService.DeleteTariff(id).then(function (response) {
        		if(response.success){
        			getAllTariffs();
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
        
        $scope.editExtra = function(extra) {
        	vm.extra = angular.copy(extra);
        	$scope.buttonExtras="Editar";
        	$scope.scrollTo( "#wrap");
        };
        
        $scope.editFuelType = function(fuel) {
        	vm.fuelType = angular.copy(fuel);
        	$scope.buttonFuelType="Editar";
        	$scope.scrollTo( "#wrap");
        };
        
        $scope.editCategory = function(category) {
        	vm.category = angular.copy(category);
        	$scope.buttonCategory="Editar";
        	$scope.scrollTo( "#wrap");
        };

    }

})();
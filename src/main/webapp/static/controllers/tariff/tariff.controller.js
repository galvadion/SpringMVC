(function () {
    'use strict';

    angular
        .module('app')
        .controller('TariffController', TariffController);

    TariffController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','TariffService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    
    function TariffController($location, UserService, $rootScope, $scope, $timeout, SessionService, TariffService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.category = {};
        vm.fuelType ={};
        vm.categoryList = [];
        vm.fuelTypeList =[];
        
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
        		
        	}
        	else{
        		mgsSuccess = "Categoria creada con éxito";
        		mgsError = "Categoria ya existente";
        	}
        	
        	TariffService.InsertCategory(vm.category).then(function (response) {
        		if(response.success){
        			getAllCategories();
        			$rootScope.doFlashMessage(mgsSuccess,'','success');
        			$scope.cleanInput();
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
        		
        	}
        	else{
        		mgsSuccess = "Tipo de combustible creada con éxito";
        		mgsError = "Tipo de combustible ya existente";
        	}
        	
        	TariffService.InsertFuelType(vm.fuelType).then(function (response) {
        		if(response.success){
        			getAllFuelTypes();
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
        	vm.category = {};
        	vm.fuelType= {};
        	$scope.form-fuel.$setPristine();
        	$scope.form-cat.$setPristine();
        };
        
        $scope.editCategory = function(brand) {
        	vm.category = angular.copy(brand);
        	$scope.scrollTo( "#wrap");
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

    }

})();
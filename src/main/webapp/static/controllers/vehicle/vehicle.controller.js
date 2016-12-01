(function () {
    'use strict';

    angular
        .module('app')
        .controller('VehicleController', VehicleController);

    VehicleController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','VehicleService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','BrandService','ModelService', 'BranchofficeService','$routeParams'];
    
    function VehicleController($location,UserService, $rootScope, $scope,$timeout,SessionService,VehicleService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder,BrandService,ModelService, BranchofficeService,$routeParams) {

        var vm = this;
        
        vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
        vm.allVehicles = [];
        vm.allBrands = [];
        vm.modelsByBrand = [];
        vm.allOffices = [];
        vm.vehicle = {};
        vm.brand = {};
        vm.vehicle.model = {};
        vm.vehicle.model.brand = {};
        
		var localDate = new Date();
		localDate = localDate.getFullYear() + '-' + (localDate.getMonth() + 1)
				+ '-' + localDate.getDate();
		$('.date').datetimepicker({
			language : 'es',
			weekStart : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			startDate : localDate
		});

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
            DTColumnDefBuilder.newColumnDef(4).notSortable(),
            DTColumnDefBuilder.newColumnDef(5).notSortable(),
            DTColumnDefBuilder.newColumnDef(6).notSortable(),
        ];
        
        initController();
        
        function initController() {
            NProgress.start();
            vm.location = $location.path().split('/',3);
            if(vm.location[2] == "edit"){
        		
                getAllBrands();
                getAllOffices();
                getVehicleById($routeParams.id);
        	}
            else if(vm.location[2] == "create"){
            	vm.vehicle.id = null;
            	getAllBrands();
            	getAllOffices();
            }
            else{
            	getAllVehicles();
            }
            NProgress.done();
        }
        
        function getAllOffices() {
			BranchofficeService.GetAllBranchoffices().then(function(response) {
				if (response.success) {
					if (response.data.length > 0) {
						vm.allOffices = response.data;
					}
				} else {
					vm.allOffices = [];
				}
				NProgress.done();
			});
		}
        
        function getAllVehicles() {
			VehicleService.GetAllVehicles().then(function(response) {
				if (response.success) {
					if (response.data.length > 0) {
						vm.allVehicles = response.data;
					}
				} else {
					vm.allVehicles = [];
				}
				NProgress.done();
			});
		}
        
        function getAllBrands(){
        	BrandService.GetAllBrands().then(function (response) {
        		if(response.success){
        			vm.allBrands = response.data;
        		}
        		else{
        			vm.allBrands = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.getModelsByBrand = function(){
        	ModelService.GetModelsByBrand(vm.vehicle.model.brand.id).then(function (response) {
        		if(response.success){
        			vm.modelsByBrand = response.data;
        			vm.vehicle.model.id = "";
        		}
        		else{
        			vm.modelsByBrand = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveVehicle = function() {
        	NProgress.start();
        	VehicleService.InsertVehicle(vm.vehicle).then(function (response) {
	    		if(response.success){
	    			$rootScope.doFlashMessage("Vehículo guardado con éxito",'/vehicle','success');
	    		}
	    		else{
	    			$rootScope.doFlashMessage("Error, intente nuevamente",'','error');
	    		}
	    		NProgress.done();
	    	});
        };
        
        function getVehicleById(id){
        	VehicleService.GetVehicleById(id).then(function (response) {
        		if(response.success){  
        			vm.vehicle.model.brand.id = response.data.model.brand.id;
        			$scope.getModelsByBrand();
        			vm.vehicle = response.data;
        		}
        		else{
        			vm.requestModel = [];
        		}
        		
        		NProgress.done();
        	});
        }

    }

})();
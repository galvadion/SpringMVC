(function () {
    'use strict';

    angular
        .module('app')
        .controller('VehicleController', VehicleController);

    VehicleController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','VehicleService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','BrandService','ModelService', 'BranchofficeService'];
    
    function VehicleController($location,UserService, $rootScope, $scope,$timeout,SessionService,VehicleService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder,BrandService,ModelService, BranchofficeService) {

        var vm = this;
        initController();
        vm.roladmin = $rootScope.roladmin;
        vm.allVehicles = [];
        vm.allBrands = [];
        vm.modelsByBrand = [];
        vm.allOffices = [];
        
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
            DTColumnDefBuilder.newColumnDef(7).notSortable(),
        ];
        
        function initController() {
            NProgress.start();
            getAllBrands();
            getAllOffices();
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
        
        function getAllBrands(){
        	BrandService.GetAllBrands(vm.brand).then(function (response) {
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
        	ModelService.GetModelsByBrand(vm.vehicle.brand.id).then(function (response) {
        		if(response.success){
        			vm.modelsByBrand = response.data;
        		}
        		else{
        			vm.modelsByBrand = [];
        		}
        		vm.vehicle.model.id="";
        		NProgress.done();
        	});
        }

    }

})();
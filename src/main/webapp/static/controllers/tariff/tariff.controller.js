(function () {
    'use strict';

    angular
        .module('app')
        .controller('TariffController', TariffController);

    TariffController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','TariffService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    
    function TariffController($location, UserService, $rootScope, $scope, $timeout, SessionService, TariffService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.tariff = {};
        vm.allTariffs = [];
        
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
            getAllTariffs();
        }
        
        function getAllTariffs(){
        	TariffService.GetAllTariffs().then(function (response) {
        		if(response.success){
        			vm.allTariffs = response.data;
        		}
        		else{
        			vm.allTariffs = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveTariff = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.tariff.id){
        		mgsSuccess = "Marca editada con éxito";
        		mgsError = "Error al editar marca";
        		
        	}
        	else{
        		mgsSuccess = "Marca creada con éxito";
        		mgsError = "Marca ya existente";
        	}
        	
        	TariffService.InsertTariff(vm.tariff).then(function (response) {
        		if(response.success){
        			getAllTariffs();
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
        	vm.tariff = {};
        	$scope.form.$setPristine();
        };
        
        $scope.editTariff = function(brand) {
        	vm.tariff = angular.copy(brand);
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
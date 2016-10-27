(function () {
    'use strict';

    angular
        .module('app')
        .controller('BookedController', BookedController);

    BookedController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','BookedService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    
    function BookedController($location, UserService, $rootScope, $scope, $timeout, SessionService, BookedService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.booked = {};
        vm.allBookeds = [];
        
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
            getAllBookeds();
        }
        
        function getAllBookeds(){
        	BookedService.GetAllBookeds().then(function (response) {
        		if(response.success){
        			vm.allBookeds = response.data;
        		}
        		else{
        			vm.allBookeds = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveBooked = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.booked.id){
        		mgsSuccess = "Marca editada con éxito";
        		mgsError = "Error al editar marca";
        		
        	}
        	else{
        		mgsSuccess = "Marca creada con éxito";
        		mgsError = "Marca ya existente";
        	}
        	
        	BookedService.InsertBooked(vm.booked).then(function (response) {
        		if(response.success){
        			getAllBookeds();
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
        	vm.booked = {};
        	$scope.form.$setPristine();
        };
        
        $scope.editBooked = function(brand) {
        	vm.booked = angular.copy(brand);
        	$scope.scrollTo( "#wrap");
        };
        
        $scope.deleteBooked = function(id) {
        	NProgress.start();
        	BookedService.DeleteBooked(id).then(function (response) {
        		if(response.success){
        			getAllBookeds();
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
(function () {
    'use strict';

    angular
        .module('app')
        .controller('ReportController', ReportController);

    ReportController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','ReportService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    
    function ReportController($location, UserService, $rootScope, $scope, $timeout, SessionService, ReportService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
        vm.pickedToday = [];
        vm.returnedToday =[];
        
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
            getPickedToday();
            getReturnedToday();
        }
        
        function getPickedToday(){
        	ReportService.getPickedToday().then(function (response) {
        		if(response.success){
        			vm.pickedToday = response.data;
        		}
        		else{
        			vm.pickedToday = [];
        		}
        		NProgress.done();
        	});
        }
        
        function getReturnedToday(){
        	ReportService.getReturnedToday().then(function (response) {
        		if(response.success){
        			vm.returnedToday = response.data;
        		}
        		else{
        			vm.returnedToday = [];
        		}
        		NProgress.done();
        	});
        }
        
        
        
        $scope.scrollTo = function(element) {
            $( 'html, body').animate({
                scrollTop: $(element).offset().top
            }, 500);
        }

    }

})();
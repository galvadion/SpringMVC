(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);        

    UserController.$inject = ['$routeParams','$timeout','$rootScope','$scope','$location', 'UserService', 'FlashService','FileUploader','SessionService','ngDialog','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    function UserController($routeParams,$timeout,$rootScope,$scope,$location, UserService, FlashService,FileUploader,SessionService,ngDialog, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {
        
        var vm = this;

        vm.user = {};
        vm.roladmin = $rootScope.roladmin;
        vm.rolclient = false
        vm.newpassword = "";
        vm.location = "";

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
            DTColumnDefBuilder.newColumnDef(3).notSortable(),
            DTColumnDefBuilder.newColumnDef(4).notSortable(),
            DTColumnDefBuilder.newColumnDef(5).notSortable(),
            DTColumnDefBuilder.newColumnDef(6).notSortable(),
            DTColumnDefBuilder.newColumnDef(8).notSortable(),
            DTColumnDefBuilder.newColumnDef(9).notSortable(),
            DTColumnDefBuilder.newColumnDef(10).notSortable(),
        ];

        initController();

        function initController(){
            NProgress.start();
            //SessionService.initService();
            
            vm.location = $location.path().split('/',2);
            vm.location = vm.location[1];
            
            if(vm.location == "profile"){

            }
            else if(vm.location == "employee"){
            	getAllEmployees();
            }
            else if(vm.location == "client"){
            	getAllClients();
            }

            NProgress.done();
        }

        function getAllEmployees(){
        	UserService.GetAllEmployees().then(function (response) {
        		console.log(response);
        	});
        }
        
        function getAllClients(){
        	UserService.GetAllClients().then(function (response) {
        		console.log(response);
        	});
        }


        
    }

})();

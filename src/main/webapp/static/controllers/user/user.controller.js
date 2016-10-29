(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);        

    UserController.$inject = ['$routeParams','$timeout','$rootScope','$scope','$location', 'UserService', 'FlashService','FileUploader','SessionService','ngDialog','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    function UserController($routeParams,$timeout,$rootScope,$scope,$location, UserService, FlashService,FileUploader,SessionService,ngDialog, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {
        
        var vm = this;

        vm.user = {};
        vm.allUsers = [];
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
            	getUserById($scope.globals.currentUser.id);
            }
            else if(vm.location == "employee"){
            	if($scope.globals.editUser != ""){
            		console.log($scope.globals.editUser);
            	}
            	else{
            		getAllEmployees();
            	}
            }
            else if(vm.location == "client"){
            	getAllClients();
            }

        }

        function getUserById(id){
        	UserService.GetUserById(id).then(function (response) {
        		if(response.success){
        			vm.user = response.data;
        		}
        		NProgress.done();
        	});
        }
        
        function getAllEmployees(){
        	UserService.GetAllEmployees().then(function (response) {
        		if(response.success){
        			vm.allUsers = response.data;
        		}
        		NProgress.done();
        	});
        }
        
        function getAllClients(){
        	UserService.GetAllClients().then(function (response) {
        		if(response.success){
        			vm.allUsers = response.data;
        		}
        		NProgress.done();
        	});
        }
        
        $scope.changeStatus = function (object) {
        	alert('a');
        };

        $scope.openDialog = function (object) {
            ngDialog.openConfirm({
                template: 'modalDialog',
                className: 'ngdialog-theme-default',
                scope: $scope,
                object : object
            }).then(function (value) {}, function (reason) {});
        };

        
    }

})();

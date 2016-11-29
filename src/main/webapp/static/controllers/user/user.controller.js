﻿(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);        

    UserController.$inject = ['$routeParams','$rootScope','$scope','$location', 'UserService', 'DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder', 'ngDialog', 'BranchofficeService'];
    function UserController($routeParams, $rootScope, $scope,$location, UserService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder, ngDialog,BranchofficeService) {
        
        var vm = this;

        vm.user = {};
        vm.allUsers = [];
        vm.roladmin = $rootScope.roladmin;
        vm.rolclient = false
        vm.allOffices = [];
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
            DTColumnDefBuilder.newColumnDef(7).notSortable(),
            DTColumnDefBuilder.newColumnDef(8).notSortable(),
            DTColumnDefBuilder.newColumnDef(9).notSortable(),
        ];

        initController();

        function initController(){
            NProgress.start();
            
            vm.location = $location.path().split('/',3);
 
            if(vm.location[1] == "profile"){
            	getUserById($scope.globals.currentUser.id);
            }
            else if(vm.location[2] == "edit"){
        		getUserById($routeParams.id);
        	}
            else if(vm.location[2] == "create"){
            	vm.user.id = null;
                vm.user.active = true;
                getAllOffices();
            }
            else{
            	if(vm.location[1] == "employee"){
	            	getAllEmployees();
	            	getAllOffices();
	            }
	            else if(vm.location[1] == "client"){
	            	getAllClients();
	            }
            }

            NProgress.done();
        }

        function getUserById(id){
        	UserService.GetUserById(id).then(function (response) {
        		if(response.success){
        			vm.user = response.data;
        		}
        		NProgress.done();
        	});
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
        
        $scope.saveUser = function() {
        	NProgress.start();
        	if(vm.location[1] == "client"){
	        	UserService.InsertClient(vm.user).then(function (response) {
	        		if(response.success){
	        			$rootScope.doFlashMessage("Cliente guardado con éxito",'/client','success');
	        		}
	        		else{
	        			$rootScope.doFlashMessage("Error, intente nuevamente",'','error');
	        		}
	        		NProgress.done();
	        	});
        	}
        	else if(vm.location[1] == "employee"){
	        	UserService.InsertEmployee(vm.user).then(function (response) {
	        		if(response.success){
	        			$rootScope.doFlashMessage("Empleado guardado con éxito",'/employee','success');
	        		}
	        		else{
	        			$rootScope.doFlashMessage("Error, intente nuevamente",'','error');
	        		}
	        		NProgress.done();
	        	});
        	}
        };
        
        $scope.deleteUser = function (id) {
        	NProgress.start();
        	if(vm.location[1] == "client"){
        		var msg = "Cliente eliminado con éxito";
        	}
        	else if(vm.location[1] == "employee"){
        		var msg = "Empleado eliminado con éxito";
        	}
        	UserService.DeleteUser(id).then(function (response) {
        		if(response.success){
        			$rootScope.doFlashMessage(msg,'','success');
        			initController();
        		}
        		else{
        			$rootScope.doFlashMessage("Error, intente nuevamente",'','error');
        		}
        		NProgress.done();
        	});
        };
        
        $scope.openDialog = function (user) {
        	vm.auxUser = angular.copy(user);
            ngDialog.openConfirm({
                template: 'modalDialog',
                className: 'ngdialog-theme-default',
                scope: $scope,
            }).then(function (value) {}, function (reason) {});
        };

        
    }

})();

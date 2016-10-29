﻿(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$scope', 'AuthenticationService'];
    function RegisterController(UserService, $location, $rootScope, FlashService, $scope, AuthenticationService) {
        var vm = this;
        vm.register = register;

        NProgress.start();
        (function initController() {
            AuthenticationService.ClearCredentials();
            NProgress.done();
        })();
        
        function register() {
            NProgress.start();
            
            vm.user.id = null;
            vm.user.active = true;
            
           UserService.InsertClient(vm.user)
	            .then(function (response) {
	            	if (response.success) {
	                    $rootScope.doFlashMessage('Cliente creado con éxito','/login','success',5000);
	                } else {
	                    $rootScope.doFlashMessage('Email ya en uso, reingrese e intente nuevamente','','error',1000);
	                }
	            NProgress.done();
	        });
        }
        
        $scope.checkBirthDateErr = function(varDate) {
        	var localDate = new Date();
        	localDate = (localDate.getFullYear() - 18) + '-' + (localDate.getMonth() + 1) + '-' +  localDate.getDate();
        	console.log(localDate);
        	if(Date.parse(localDate) <= Date.parse(varDate)){
        		$scope.birthDateError = true;
        	}
        	else{
        		$scope.birthDateError = false;
        	}
        	
        };
        
        $scope.checkLicenseExpirationDateErr = function(varDate) {
        	var localDate = new Date();
        	localDate = localDate.getFullYear() + '-' + (localDate.getMonth() + 1) + '-' +  localDate.getDate();
        	if(Date.parse(localDate) > Date.parse(varDate)){
        		$scope.licenseExpirationDateError = true;
        	}
        	else{
        		$scope.licenseExpirationDateError = false;
        	}
        	
        };
        
    }

})();

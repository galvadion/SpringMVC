(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$scope', 'AuthenticationService'];
    function RegisterController(UserService, $location, $rootScope, FlashService, $scope, AuthenticationService) {
        var vm = this;
        vm.register = register;

        var localDate = new Date();
        localDate = (localDate.getFullYear()-18) + '-' + (localDate.getMonth() + 1) + '-' +  localDate.getDate();
        
        $('#birthDate').datetimepicker({
        	language:  'es',
            weekStart: 1,
        	autoclose: 1,
        	todayHighlight: 1,
        	startView: 2,
        	minView: 2,
        	forceParse: 0,
        	endDate: localDate
        });
        
        $('#licenseExpirationDate').datetimepicker({
        	language:  'es',
            weekStart: 1,
        	autoclose: 1,
        	todayHighlight: 1,
        	startView: 2,
        	minView: 2,
        	forceParse: 0
        });

        
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
	                    $rootScope.doFlashMessage('Cliente creado con éxito, se le ha enviado un correo para validar su cuenta. Siga los pasos para poder ingresar al sistema','/login','success',5000);
	                } else {
	                    $rootScope.doFlashMessage('Email ya en uso, reingrese e intente nuevamente','','error',1000);
	                }
	            NProgress.done();
	        });
        }
        
    }

})();

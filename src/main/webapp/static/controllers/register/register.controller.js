(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$scope', 'AuthenticationService'];
    function RegisterController(UserService, $location, $rootScope, FlashService, $scope, AuthenticationService) {
        var vm = this;
        vm.register = register;
		vm.registered=false;


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
	            		vm.registered=true;
	                } else {
	                    $rootScope.doFlashMessage(response.data.message,'','error',6000);
	                }
	            NProgress.done();
	        });
        }
        
    }

})();

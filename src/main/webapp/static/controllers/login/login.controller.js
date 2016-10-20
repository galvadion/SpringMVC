(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', '$rootScope'];
    function LoginController($location, AuthenticationService, $rootScope) {

        var vm = this;
        NProgress.start();
        vm.login = login;
        
        (function initController() {
            AuthenticationService.ClearCredentials();
            NProgress.done();
        })();

        
        function login() {
            NProgress.start();
            
            AuthenticationService.Login(vm.email, vm.password, function(response) {
            	console.log(response);
                if(response.success) {
                    AuthenticationService.SetCredentials(response.data.id, response.data.email, response.data.name, response.data.dtype, response.data.password);
                	$location.path('/home');
                } else {
                    //$rootScope.doFlashMessage('Error en login','','error');
                }
                NProgress.done();
            });
        };

    }

})();

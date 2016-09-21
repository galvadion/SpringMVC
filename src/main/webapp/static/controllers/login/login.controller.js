(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', '$rootScope'];
    function LoginController($location, AuthenticationService, $rootScope) {

        var vm = this;
        NProgress.start();

        vm.menu = false;
        vm.login = login;
        
        
        (function initController() {
            AuthenticationService.ClearCredentials();
            NProgress.done();
        })();

        function login() {
            vm.dataLoading = true;
            NProgress.start();
            AuthenticationService.Login(vm.emailAddress, vm.password, function (response) {
            	console.log(response +"aca tmb llego");
                if (response.is_logged_in) {
                    AuthenticationService.SetCredentials(vm.emailAddress, vm.password ,response.id,response.rol[0].role_id,response.avatarURL);
                    NProgress.done();
                    $location.path('/home');
                    vm.menu = true;
                } else {
                    $rootScope.doFlashMessage(response.error,'','error');
                    NProgress.done();
                    vm.dataLoading = false;
                }
            });
        };

    }

})();

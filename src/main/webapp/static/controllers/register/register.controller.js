(function () {
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
            
            /*UserService.CheckEmail(vm.user.email).then(function (response) {
                if(response.data.exist != true){*/
            
                    UserService.CreateClient(vm.user)
                        .then(function (response) {
                             /*if (response.data.id > 0) {
                                $rootScope.doFlashMessage('Client created successfully','/login','success',10000);
                            } else {
                                $rootScope.doFlashMessage(response.error,'/login','error');
                            }*/console.log(response);
                        NProgress.done();
                    });
                /*}
                else{
                    $rootScope.doFlashMessage('Email already exist.','','error', 5000);
                    NProgress.done();
                }
            });*/
        }
    }

})();

(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$scope'];
    function RegisterController(UserService, $location, $rootScope, FlashService, $scope) {
        var vm = this;

        vm.register = register;

        function register() {
            NProgress.start();
            vm.user.avatarURL = window.location.protocol + "//" + window.location.host + "/assets/images/avatar.png";
            
            UserService.CheckEmail(vm.user.email).then(function (response) {
                if(response.data.exist != true){
                    UserService.Create(vm.user.name,vm.user.lastName,vm.user.email,vm.user.password,null,vm.user.avatarURL,vm.user.rol,'register')
                        .then(function (response) {
                             if (response.data.id > 0) {
                                $rootScope.doFlashMessage('You user is created successfully-Contact with the Site Admin to active','/login','success',10000);
                            } else {
                                $rootScope.doFlashMessage(response.error,'/login','error');
                            }
                        NProgress.done();
                    });
                }
                else{
                    $rootScope.doFlashMessage('Email already exist.','','error', 5000);
                    NProgress.done();
                }
            });
        }
    }

})();

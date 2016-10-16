(function () {
    'use strict';

    angular
        .module('app')
        .controller('ForgotController', ForgotController);

    ForgotController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', 'AuthenticationService'];
    function ForgotController(UserService, $location, $rootScope, FlashService, AuthenticationService) {
        var vm = this;
        vm.forgot = forgot;
        
        NProgress.start();
        (function initController() {
            AuthenticationService.ClearCredentials();
            NProgress.done();
        })();
        
        function forgot() {
            vm.dataLoading = true;
             UserService.ValidateUser(vm.username)
                .then(function (response) {
                    if (response.data.status == 'error') {
                        $rootScope.doFlashMessage(response.data.msg,'/login','error');
                        vm.dataLoading = false;
                    } else {
                        $rootScope.doFlashMessage(response.data.msg,'/login','success');
                    }
            });
        }
    }

})();

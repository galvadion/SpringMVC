(function () {
    'use strict';

    angular
        .module('app')
        .controller('ForgotController', ForgotController);

    ForgotController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function ForgotController(UserService, $location, $rootScope, FlashService) {
        var vm = this;
        vm.forgot = forgot;
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

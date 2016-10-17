(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModelController', ModelController);

    ModelController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService'];
    
    function ModelController($location,UserService, $rootScope, $scope,$timeout,SessionService) {

        var vm = this;
        initController();
        vm.roladmin = $rootScope.roladmin;

        function initController() {
            NProgress.start();

            NProgress.done();
        }

    }

})();
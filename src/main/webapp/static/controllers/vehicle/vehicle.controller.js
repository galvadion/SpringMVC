﻿(function () {
    'use strict';

    angular
        .module('app')
        .controller('VehicleController', VehicleController);

    VehicleController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService'];
    
    function VehicleController($location,UserService, $rootScope, $scope,$timeout,SessionService) {

        var vm = this;
        initController();
        vm.roladmin = $rootScope.roladmin;

        function initController() {
            NProgress.start();

            NProgress.done();
        }

    }

})();
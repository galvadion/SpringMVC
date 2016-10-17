(function () {
    'use strict';

    angular
        .module('app')
        .controller('BrandController', BrandController);

    BrandController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService'];
    
    function BrandController($location,UserService, $rootScope, $scope,$timeout,SessionService) {

        var vm = this;
        initController();
        vm.roladmin = $rootScope.roladmin;

        function initController() {
            NProgress.start();

            NProgress.done();
        }

    }

})();
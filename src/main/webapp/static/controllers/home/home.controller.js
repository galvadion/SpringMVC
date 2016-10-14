(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService'];
    
    function HomeController($location,UserService, $rootScope, $scope,$timeout,SessionService) {

        var vm = this;
        initController();
        

        function initController() {
            NProgress.start();
            //SessionService.initService();
            //$rootScope.checkCurrentRol();    
            NProgress.done();
        }

    }

})();
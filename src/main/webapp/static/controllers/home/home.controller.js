(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','ModelService'];
    
    function HomeController($location,UserService, $rootScope, $scope,$timeout,SessionService,ModelService) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.search = {};
        
        initController();

        function initController() {
            NProgress.start();
            getAllOffers();
            
            vm.search.beginDate = "10-11-2016";
            vm.search.beginDate = "10-12-2016";
            vm.search.branchId = 1;
            vm.search.airConditioner = true;
            vm.search.passanger = 0;
            vm.search.luggage = 0;
            console.log(vm.search);
            searchModels();
            
            NProgress.done();
        }
        
        function getAllOffers(){
        	return true;
        }
        
        function searchModels() {
        	ModelService.SearchModels(vm.search).then(function (response) {
        		console.log(response);
        	});
        }

    }

})();
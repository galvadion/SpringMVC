(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','ModelService'];
    
    function HomeController($location, UserService, $rootScope, $scope, $timeout, SessionService, ModelService) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.search = {};


        $scope.searchModels = function() {
        	
        	//Pruebas
        	vm.search.airConditioner = true;
            vm.search.passanger = 0;
            vm.search.luggage = 0;
        	console.log(vm.search);
        	//
        	
        	ModelService.SearchModels(vm.search).then(function (response) {
        		console.log(response);
        	});
        };
        
        initController();

        function initController() {
            NProgress.start();
            getAllOffers();
            NProgress.done();
        }
        
        function getAllOffers(){
        	
        }

    }

})();
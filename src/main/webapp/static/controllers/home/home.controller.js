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
        vm.searchResult = [];
        
        var localDate = new Date();
    	localDate = localDate.getFullYear() + '-' + (localDate.getMonth() + 1) + '-' +  localDate.getDate();
        $('.date').datetimepicker({
        	language:  'es',
            weekStart: 1,
        	autoclose: 1,
        	todayHighlight: 1,
        	startView: 2,
        	minView: 2,
        	forceParse: 0,
        	startDate: localDate
        });
        
        $scope.checkEndDate = function(varDate) {
        	if(Date.parse(vm.search.beginDate) > Date.parse(vm.search.endDate)){
        		$scope.endDateError = true;
        	}
        	else{
        		$scope.endDateError = false;
        	}
        };
        
        $scope.map = {
        		center: { 
        			latitude: -34.901113,
        			longitude: -56.164531
        		},
        		zoom: 14,
        };
        
        $scope.options = {
        		scrollwheel: false
	    };

        $scope.markers = [];
        $scope.markers.push({
        	id: 1,
    		latitude: -34.901113,
            longitude: -56.164531,
            title: 'Sucursal 1'
        });
        $scope.markers.push({
        	id: 2,
    		latitude: -34.904113,
            longitude: -56.160531,
            title: 'Sucursal 2'
        });


        $scope.searchModels = function() {        	
        	//Pruebas
        	vm.search.airConditioner = true;
            vm.search.passangers = 0;
            vm.search.luggage = 0;
        	console.log(vm.search);
        	//
        	
        	ModelService.SearchModels(vm.search).then(function (response) {
        		if(response){
        			vm.searchResult = response.data;
        			console.log(vm.searchResult);
        		}
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
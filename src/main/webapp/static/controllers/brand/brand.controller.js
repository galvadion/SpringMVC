(function () {
    'use strict';

    angular
        .module('app')
        .controller('BrandController', BrandController);

    BrandController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','BrandService'];
    
    function BrandController($location, UserService, $rootScope, $scope, $timeout, SessionService, BrandService) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.error = false;
        vm.success = false;
        vm.brand = {};
        vm.allBrands = [];
        
        
        initController();
        
        function initController() {
            NProgress.start();
            getAllBrands();
            NProgress.done();
        }
        
        function getAllBrands(){
        	BrandService.GetAllBrands(vm.brand).then(function (response) {
        		if(response.success){
        			vm.allBrands = response.data;
        			console.log(response);
        			console.log(vm.allBrands);
        		}
        		else{
        			vm.allBrands = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveBrand = function() {
        	NProgress.start();
        	$scope.cleanMessagges();
        	BrandService.CreateBrand(vm.brand).then(function (response) {
        		if(response.success){
        			vm.success = true;
        			$scope.cleanInput();
        		}
        		else{
        			vm.error = true;
        		}
        		NProgress.done();
        	});
        };
        
        $scope.cleanInput = function() {
        	vm.brand.name = "";
        	$scope.form.$setPristine();
        };
        
        $scope.cleanMessagges = function() {
        	$scope.form.$setPristine();
        	vm.error = false;
            vm.success = false;
        };

    }

})();
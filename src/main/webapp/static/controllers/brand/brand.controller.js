(function () {
    'use strict';

    angular
        .module('app')
        .controller('BrandController', BrandController);

    BrandController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','BrandService'];
    
    function BrandController($location,UserService, $rootScope, $scope,$timeout,SessionService,BrandService) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.brand = {};
        
        initController();
        
        function initController() {
            NProgress.start();

            NProgress.done();
        }
        
        $scope.saveBrand = function() {
        	BrandService.CreateBrand(vm.brand).then(function (response) {
        		console.log(response);
        		$scope.cleanInput();
        	});
        };
        
        $scope.cleanInput = function() {
        	vm.brand.name = "";
        	$scope.form.$setPristine();
        };

    }

})();
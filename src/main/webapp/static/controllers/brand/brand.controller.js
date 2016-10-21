(function () {
    'use strict';

    angular
        .module('app')
        .controller('BrandController', BrandController);

    BrandController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','BrandService'];
    
    function BrandController($location, UserService, $rootScope, $scope, $timeout, SessionService, BrandService) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.brand = {};
        vm.allBrands = [];
        
        
        initController();
        
        function initController() {
            NProgress.start();
            getAllBrands();
        }
        
        function getAllBrands(){
        	BrandService.GetAllBrands().then(function (response) {
        		if(response.success){
        			vm.allBrands = response.data;
        		}
        		else{
        			vm.allBrands = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveBrand = function() {
        	NProgress.start();
        	BrandService.CreateBrand(vm.brand).then(function (response) {
        		if(response.success){
        			getAllBrands();
        			$rootScope.doFlashMessage('Marca creada','','success');
        			$scope.cleanInput();
        		}
        		else{
        			$rootScope.doFlashMessage('Marca ya existente','','error');
        		}
        		NProgress.done();
        	});
        };
        
        $scope.cleanInput = function() {
        	vm.brand.name = "";
        	$scope.form.$setPristine();
        };
        
        $scope.editBrand = function(brand) {
        	vm.brand = angular.copy(brand);
        	$scope.scrollTo( "#wrap");
        };
        
        $scope.deleteBrand = function(id) {
        	alert('eliminar brand id: ' + id);
        };
        
        $scope.scrollTo = function(element) {
            $( 'html, body').animate({
                scrollTop: $(element).offset().top
            }, 500);
        }

    }

})();
(function () {
    'use strict';

    angular
        .module('app')
        .factory('BrandService', BrandService);

    BrandService.$inject = ['$http'];

    function BrandService($http) {
        var service = {};

        service.GetAllBrands = GetAllBrands;
        service.InsertBrand = InsertBrand;
        service.DeleteBrand = DeleteBrand;
        
        
        return service;

        
        // Brand functions
        
        function GetAllBrands() {
            return $http.get('/SpringMVC/brand/getall').then(handleSuccess, handleError);
        }
        
        function InsertBrand(brand) {
            return $http.post('/SpringMVC/brand/insert', brand).then(handleSuccess, handleError);
        }
        
        function DeleteBrand(id) {
            return $http.delete('/SpringMVC/brand/delete?id='+id).then(handleSuccess, handleError);
        }

        
        // private functions

        function handleSuccess(data) {
        	var response = {};
        	response.success = true;
    		response.data = data.data;
            return response;
        }
        
        function handleError(data) {
        	var response = {};
    		response.success = false;
    		response.data = data.data;
            return response;
        }
    }

})();

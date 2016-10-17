(function () {
    'use strict';

    angular
        .module('app')
        .factory('VehicleService', VehicleService);

    VehicleService.$inject = ['$http'];

    function VehicleService($http) {
        var service = {};

        service.GetAllBrands = GetAllBrands;
        service.CreateBrand = CreateBrand;
        service.UpdateBrand = UpdateBrand;
        service.DeleteBrand = DeleteBrand;
        
        
        return service;

        
        //Brand functions
        
        function GetAllBrands() {
            return $http.get('/SpringMVC/brand/getall').then(handleSuccess, handleError('Error getting all brands'));
        }
        
        function CreateBrand(brand) {
            return $http.post('/SpringMVC/brand/insert', brand).then(handleSuccess, handleError('Error creating brand'));
        }
        
        function UpdateBrand(brand) {
            return $http.put('/SpringMVC/brand/update', brand).then(handleSuccess, handleError('Error updating brand'));
        }
        
        function DeleteBrand(id) {
            return $http.delete('/SpringMVC/brand/delete', id).then(handleSuccess, handleError('Error deleting brand'));
        }

        
        // private functions

        function handleSuccess(data) {
            return data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();

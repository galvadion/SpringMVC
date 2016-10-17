(function () {
    'use strict';

    angular
        .module('app')
        .factory('VehicleService', VehicleService);

    VehicleService.$inject = ['$http'];

    function VehicleService($http) {
        var service = {};

        service.CheckBrand = CheckBrand;
        service.GetAllBrands = GetAllBrands;
        service.GetBrandById = GetBrandById;
        service.CreateBrand = CreateBrand;
        service.UpdateBrand = UpdateBrand;
        service.DeleteBrand = DeleteBrand;
        
        
        return service;

        
        //Brand functions
        
        function CheckBrand(name) {
            return $http.post('/SpringMVC/brand/checkname', name).then(handleSuccess, handleError('Error checking brand'));
        }
        
        function GetAllBrands() {
            return $http.get('/SpringMVC/brand/getall').then(handleSuccess, handleError('Error getting all brands'));
        }
        
        function GetBrandById(id) {
            return $http.get('/SpringMVC/brand/getbyid' + id).then(handleSuccess, handleError('Error getting brand by id'));
        }
        
        function CreateBrand(brand) {
            return $http.post('/SpringMVC/brand/register', brand).then(handleSuccess, handleError('Error creating brand'));
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

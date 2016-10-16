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
        
        
        service.CheckModel = CheckModel;
        service.GetAllModels = GetAllModels;
        service.GetModelById = GetModelById;
        service.CreateModel = CreateModel;
        service.UpdateModel = UpdateModel;
        service.DeleteModel = DeleteModel;
        
        service.CheckVehicle = CheckVehicle;
        service.GetAllVehicles = GetAllVehicles;
        service.GetVehicleById = GetVehicleById;
        service.CreateVehicle = CreateVehicle;
        service.UpdateVehicle = UpdateVehicle;
        service.DeleteVehicle = DeleteVehicle;
        
        
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
        
        
        //Model functions
        
        function CheckModel(name) {
            return $http.post('/SpringMVC/model/checkname', name).then(handleSuccess, handleError('Error checking model'));
        }
        
        function GetAllModels() {
            return $http.get('/SpringMVC/model/getall').then(handleSuccess, handleError('Error getting all models'));
        }
        
        function GetModelById(id) {
            return $http.get('/SpringMVC/model/getbyid' + id).then(handleSuccess, handleError('Error getting model by id'));
        }
        
        function CreateModel(model) {
            return $http.post('/SpringMVC/model/register', model).then(handleSuccess, handleError('Error creating model'));
        }
        
        function UpdateModel(model) {
            return $http.put('/SpringMVC/model/update', model).then(handleSuccess, handleError('Error updating model'));
        }
        
        function DeleteModel(id) {
            return $http.delete('/SpringMVC/model/delete', id).then(handleSuccess, handleError('Error deleting model'));
        }
        
        
        //Vehicle functions
        
        function CheckVehicle(vehicle) {
            return $http.post('/SpringMVC/vehicle/checkvehicle', vehicle).then(handleSuccess, handleError('Error checking vehicle'));
        }
        
        function GetAllVehicles() {
            return $http.get('/SpringMVC/vehicle/getall').then(handleSuccess, handleError('Error getting all vehicles'));
        }
        
        function GetVehicleById(id) {
            return $http.get('/SpringMVC/vehicle/getbyid' + id).then(handleSuccess, handleError('Error getting vehicle by id'));
        }
        
        function CreateVehicle(vehicle) {
            return $http.post('/SpringMVC/vehicle/register', vehicle).then(handleSuccess, handleError('Error creating vehicle'));
        }
        
        function UpdateVehicle(vehicle) {
            return $http.put('/SpringMVC/vehicle/update', vehicle).then(handleSuccess, handleError('Error updating vehicle'));
        }
        
        function DeleteVehicle(id) {
            return $http.delete('/SpringMVC/vehicle/delete', id).then(handleSuccess, handleError('Error deleting vehicle'));
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

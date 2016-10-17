(function () {
    'use strict';

    angular
        .module('app')
        .factory('VehicleService', VehicleService);

    VehicleService.$inject = ['$http'];

    function VehicleService($http) {
        var service = {};

        service.CheckVehicle = CheckVehicle;
        service.GetAllVehicles = GetAllVehicles;
        service.GetVehicleById = GetVehicleById;
        service.CreateVehicle = CreateVehicle;
        service.UpdateVehicle = UpdateVehicle;
        service.DeleteVehicle = DeleteVehicle;
        
        
        return service;

        
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

(function () {
    'use strict';

    angular
        .module('app')
        .factory('VehicleService', VehicleService);

    VehicleService.$inject = ['$http'];

    function VehicleService($http) {
        var service = {};

        service.GetAllVehicles = GetAllVehicles;
        service.GetVehicleById = GetVehicleById;
        service.CreateVehicle = CreateVehicle;
        service.UpdateVehicle = UpdateVehicle;
        service.DeleteVehicle = DeleteVehicle;
        
        
        return service;

        
        //Vehicle functions
        
        function GetAllVehicles() {
            return $http.get('/SpringMVC/vehicle/getall').then(handleSuccess, handleError('Error getting all vehicles'));
        }
        
        function GetVehicleById(id) {
            return $http.get('/SpringMVC/vehicle/getbyid' + id).then(handleSuccess, handleError('Error getting vehicle by id'));
        }
        
        function CreateVehicle(vehicle) {
            return $http.post('/SpringMVC/vehicle/insert', vehicle).then(handleSuccess, handleError('Error creating vehicle'));
        }
        
        function UpdateVehicle(vehicle) {
            return $http.put('/SpringMVC/vehicle/update', vehicle).then(handleSuccess, handleError('Error updating vehicle'));
        }
        
        function DeleteVehicle(id) {
            return $http.delete('/SpringMVC/vehicle/delete', id).then(handleSuccess, handleError('Error deleting vehicle'));
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

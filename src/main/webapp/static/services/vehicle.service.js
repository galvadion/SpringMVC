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
        service.InsertVehicle = InsertVehicle;
        service.DeleteVehicle = DeleteVehicle;
        service.DeleteImage = DeleteImage;
        
        
        return service;

        
        //Vehicle functions
        
        function GetAllVehicles() {
            return $http.get('/SpringMVC/vehicle/getall').then(handleSuccess, handleError('Error getting all vehicles'));
        }
        
        function GetVehicleById(id) {
            return $http.get('/SpringMVC/vehicle/getbyid?id=' + id).then(handleSuccess, handleError('Error getting vehicle by id'));
        }
        
        function InsertVehicle(vehicle) {
        	vehicle.licensePlateExpirationDate = vehicle.licensePlateExpirationDate.replace(/-/g , "/");
            return $http.post('/SpringMVC/vehicle/insert', vehicle).then(handleSuccess, handleError('Error creating vehicle'));
        }
        
        function DeleteVehicle(id) {
            return $http.delete('/SpringMVC/vehicle/delete?id=' + id).then(handleSuccess, handleError('Error deleting vehicle'));
        }

        function DeleteImage(id) {
            return $http.delete('/SpringMVC/vehicle/deleteimage?id=' + id).then(handleSuccess, handleError('Error deleting vehicle'));
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

(function () {
    'use strict';

    angular
        .module('app')
        .factory('VehicleService', VehicleService);

    VehicleService.$inject = ['$http'];

    function VehicleService($http) {
        var service = {};

        service.CheckModel = CheckModel;
        service.GetAllModels = GetAllModels;
        service.GetModelById = GetModelById;
        service.CreateModel = CreateModel;
        service.UpdateModel = UpdateModel;
        service.DeleteModel = DeleteModel;
        
        
        return service;

        
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

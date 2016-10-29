(function () {
    'use strict';

    angular
        .module('app')
        .factory('ModelService', ModelService);

    ModelService.$inject = ['$http'];

    function ModelService($http) {
        var service = {};

        service.GetAllModels = GetAllModels;
        service.GetModelById = GetModelById;
        service.CreateModel = CreateModel;
        service.UpdateModel = UpdateModel;
        service.DeleteModel = DeleteModel;
        service.SearchModels = SearchModels;
        
        
        return service;

        
        //Model functions
        
        function GetAllModels() {
            return $http.get('/SpringMVC/model/getall').then(handleSuccess, handleError('Error getting all models'));
        }
        
        function GetModelById(id) {
            return $http.get('/SpringMVC/model/getbyid' + id).then(handleSuccess, handleError('Error getting model by id'));
        }
        
        function CreateModel(model) { console.log(model);
            return $http.post('/SpringMVC/model/insert', model).then(handleSuccess, handleError('Error creating model'));
        }
        
        function UpdateModel(model) {
            return $http.put('/SpringMVC/model/update', model).then(handleSuccess, handleError('Error updating model'));
        }
        
        function DeleteModel(id) {
            return $http.delete('/SpringMVC/model/delete', id).then(handleSuccess, handleError('Error deleting model'));
        }
        
        function SearchModels(searchObject) {
            return $http.post('/SpringMVC/model/search', searchObject).then(handleSuccess, handleError('Error searching models'));
        }

        
        // private functions

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

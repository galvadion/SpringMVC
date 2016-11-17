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
        service.DeleteModel = DeleteModel;
        service.SearchModels = SearchModels;
        service.GetModelsByBrand = GetModelsByBrand;
        
        
        return service;

        
        //Model functions
        
        function GetAllModels() {
            return $http.get('/SpringMVC/model/getall').then(handleSuccess, handleError('Error getting all models'));
        }
        
        function GetModelById(id) {
            return $http.get('/SpringMVC/model/getbyid?id=' + id).then(handleSuccess, handleError('Error getting model by id'));
        }
        
        function GetModelsByBrand(id){
        	return $http.get('/SpringMVC/model/getmodelsbyid?id=' + id).then(handleSuccess, handleError('Error getting model by id'));
        }
        
        function CreateModel(model) {
            return $http.post('/SpringMVC/model/insert', model).then(handleSuccess, handleError('Error creating model'));
        }
        
        function DeleteModel(id) {
            return $http.delete('/SpringMVC/model/delete?id=' + id).then(handleSuccess, handleError('Error deleting model'));
        }
        
        function SearchModels(searchObject) {
        	searchObject.beginDate = searchObject.beginDate.replace(/-/g , "/");
        	searchObject.endDate = searchObject.endDate.replace(/-/g , "/");
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

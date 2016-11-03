(function () {
    'use strict';

    angular
        .module('app')
        .factory('BranchofficeService', BranchofficeService);

    BranchofficeService.$inject = ['$http'];

    function BranchofficeService($http) {
        var service = {};

        service.GetAllBranchoffices = GetAllBranchoffices;
        service.GetBranchofficeById = GetBranchofficeById;
        service.InsertBranchoffice = InsertBranchoffice;
        service.DeleteBranchoffice = DeleteBranchoffice;
        
        
        return service;

        
        // Branchoffice functions
        
        function GetAllBranchoffices() {
            return $http.get('/SpringMVC/branchoffice/getall').then(handleSuccess, handleError);
        }
        
        function GetBranchofficeById(id) {
            return $http.get('/SpringMVC/branchoffice/getbyid?id=' + id).then(handleSuccess, handleError);
        }
        
        function InsertBranchoffice(branchoffice) {
            return $http.post('/SpringMVC/branchoffice/insert', branchoffice).then(handleSuccess, handleError);
        }
        
        function DeleteBranchoffice(id) {
            return $http.delete('/SpringMVC/branchoffice/delete?id=' + id).then(handleSuccess, handleError);
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

(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];

    function UserService($http) {
        var service = {};

        service.GetAllUsers = GetAllUsers;
        service.GetUserById = GetUserById;
        service.DeleteUser = DeleteUser;
        
        service.GetAllClients = GetAllClients;
        service.InsertClient = InsertClient;
        service.GetByDocument = GetByDocument;
        service.ValidateUser= ValidateUser;
        
        service.GetAllEmployees = GetAllEmployees;
        service.InsertEmployee = InsertEmployee;
        
        return service;

        //User functions
        
        function GetAllUsers() {
            return $http.get('/SpringMVC/user/getall').then(handleSuccess, handleError);
        }
        
        function GetUserById(id) {
            return $http.get('/SpringMVC/user/getbyid?id=' + id).then(handleSuccess, handleError);
        }
        
        function DeleteUser(id) {
            return $http.delete('/SpringMVC/user/delete?id=' + id).then(handleSuccess, handleError);
        }
        
        
        //Client functions

        function GetAllClients() {
            return $http.get('/SpringMVC/client/getall').then(handleSuccess, handleError);
        }
        
        function InsertClient(client) {
            return $http.post('/SpringMVC/client/insert', client).then(handleSuccess, handleError);
        }
        
        function GetByDocument(id) {
            return $http.get('/SpringMVC/client/getbydocument?id=' + id).then(handleSuccess, handleError);
        }
        
        function ValidateUser(id) {
            return $http.get('/SpringMVC/login/recoverpassword?email=' + id).then(handleSuccess, handleError);
        }

        //Employee functions
        
        function GetAllEmployees() {
            return $http.get('/SpringMVC/employee/getall').then(handleSuccess, handleError);
        }
        
        function InsertEmployee(employee) {
            return $http.post('/SpringMVC/employee/insert', employee).then(handleSuccess, handleError);
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

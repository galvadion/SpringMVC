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
        
        service.GetAllClients = GetAllClients;
        service.CreateClient = CreateClient;
        service.UpdateClient = UpdateClient;
        service.DeleteClient = DeleteClient;
        
        service.GetAllEmployees = GetAllEmployees;
        service.CreateEmployee = CreateEmployee;
        service.UpdateEmployee = UpdateEmployee;
        service.DeleteEmployee = DeleteEmployee;
        
        return service;

        //User functions
        
        function GetAllUsers() {
            return $http.get('/SpringMVC/user/getall').then(handleSuccess, handleError);
        }
        
        function GetUserById(id) {
            return $http.get('/SpringMVC/user/getbyid' + id).then(handleSuccess, handleError);
        }
        
        //Client functions

        function GetAllClients() {
            return $http.get('/SpringMVC/client/getall').then(handleSuccess, handleError);
        }
        
        function CreateClient(client) {
            return $http.post('/SpringMVC/client/register', client).then(handleSuccess, handleError);
        }
        
        function UpdateClient(client) {
            return $http.put('/SpringMVC/client/update', client).then(handleSuccess, handleError);
        }
        
        function DeleteClient(id) {
            return $http.delete('/SpringMVC/client/delete', id).then(handleSuccess, handleError);
        }

        
        //Employee functions
        
        function GetAllEmployees() {
            return $http.get('/SpringMVC/employee/getall').then(handleSuccess, handleError);
        }
        
        function CreateEmployee(employee) {
            return $http.post('/SpringMVC/employee/register', employee).then(handleSuccess, handleError);
        }
        
        function UpdateEmployee(employee) {
            return $http.put('/SpringMVC/employee/update', employee).then(handleSuccess, handleError);
        }
        
        function DeleteEmployee(id) {
            return $http.delete('/SpringMVC/employee/delete', id).then(handleSuccess, handleError);
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

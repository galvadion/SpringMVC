(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];

    function UserService($http) {
        var service = {};

        service.CheckEmail = CheckEmail;
        
        service.GetAllUsers = GetAllUsers;
        service.GetUserById = GetUserById;
        
        service.CreateClient = CreateClient;
        service.UpdateClient = UpdateClient;
        service.DeleteClient = DeleteClient;
        
        service.CreateEmployee = CreateEmployee;
        service.UpdateEmployee = UpdateEmployee;
        service.DeleteEmployee = DeleteEmployee;
        
        return service;

        //User functions
        
        function CheckEmail(email) {
            return $http.post('/SpringMVC/user/checkemail', email).then(handleSuccess, handleError('Error checking user email'));
        }
        
        function GetAllUsers() {
            return $http.get('/SpringMVC/user/getall').then(handleSuccess, handleError('Error getting all users'));
        }
        
        function GetUserById(id) {
            return $http.get('/SpringMVC/user/getbyid' + id).then(handleSuccess, handleError('Error getting user by id'));
        }
        
        //Client functions

        function CreateClient(client) {
            return $http.post('/SpringMVC/client/register', client).then(handleSuccess, handleError('Error creating client'));
        }
        
        function UpdateClient(client) {
            return $http.put('/SpringMVC/client/update', client).then(handleSuccess, handleError('Error updating client'));
        }
        
        function DeleteClient(id) {
            return $http.delete('/SpringMVC/client/delete', id).then(handleSuccess, handleError('Error deleting client'));
        }

        
        //Employee functions
        
        function CreateEmployee(employee) {
            return $http.post('/SpringMVC/employee/register', employee).then(handleSuccess, handleError('Error creating employee'));
        }
        
        function UpdateEmployee(employee) {
            return $http.put('/SpringMVC/employee/update', employee).then(handleSuccess, handleError('Error employee client'));
        }
        
        function DeleteEmployee(id) {
            return $http.delete('/SpringMVC/employee/delete', id).then(handleSuccess, handleError('Error deleting client'));
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

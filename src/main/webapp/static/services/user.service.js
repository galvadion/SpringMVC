(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];

    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.CreateUser = CreateUser;
        service.CreateClient = CreateClient;
        service.Update = Update;
        service.Delete = Delete;
        service.ValidateUser = ValidateUser;
        service.RolByUserId = RolByUserId;
        service.CheckEmail = CheckEmail;
        return service;

        function GetAll() {
            return $http.get('/users/users').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/users/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http.get('/users/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function CheckEmail(email) {
            var data = {function:"checkEmail",email:email};
            return $http.post('/users/users/', data).then(handleSuccess, handleError('Error checking user email'));
        }

        function RolByUserId(ids){
            var data = {function:"getrol",id:ids};
            return $http.post('users/users/',data).then(handleSuccess,handleError("Error getting Rol By User Id"));
        }


        function ValidateUser(username) {
            return  $http({
                url: '/forgot/restore/',
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                data: $.param({mail:username})
                }).then(handleSuccess, handleError('Error getting user by username'));
            
        }

        function CreateClient(client) {
            return $http.post('/SpringMVC/client/register', client).then(handleSuccess, handleError('Error creating client'));
        }
        
        function CreateUser(client) {
            return $http.post('/users/users', JSON.stringify(client)).then(handleSuccess, handleError('Error creating client'));
        }

        function Update(user) {
            return $http.put('/users/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }
        function Delete(id) {
            return $http.delete('/users/users/' + id).then(handleSuccess, handleError('Error deleting user'));
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

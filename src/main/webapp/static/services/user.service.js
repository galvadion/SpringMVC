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
        service.Create = Create;
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

        function Create(name,lastname,email,pass,address,avatar,rol,register) {
            var user = {name:name,lastname:lastname,email:email,password:pass,address:address,avatarURL:avatar,rol:rol,register:register};
                return $http.post('/users/users', user).then(handleSuccess, handleError('Error creating user'));
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

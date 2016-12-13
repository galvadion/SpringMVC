(function () {
    'use strict';

    angular
        .module('app')
        .factory('BookedService', BookedService);

    BookedService.$inject = ['$http'];

    function BookedService($http) {
        var service = {};

        service.GetAllBookeds = GetAllBookeds;
        service.InsertBooked = InsertBooked;
        service.DeleteBooked = DeleteBooked;
        service.GetBookedById = GetBookedById;
        service.ValidatePromotion=ValidatePromotion;
        
        
        return service;

        
        // Booked functions
        
        function GetAllBookeds() {
            return $http.get('/SpringMVC/booked/getall').then(handleSuccess, handleError);
        }
        
        function GetBookedById(id) {
            return $http.get('/SpringMVC/booked/getbyid?id=' + id).then(handleSuccess, handleError);
        }
        
        function InsertBooked(booked) {
            return $http.post('/SpringMVC/booked/insert', booked).then(handleSuccess, handleError);
        }
        
        function DeleteBooked(id) {
            return $http.delete('/SpringMVC/booked/delete', id).then(handleSuccess, handleError);
        }
        
        function ValidatePromotion(id) {
            return $http.post('/SpringMVC/promotion/validatecode',id).then(handleSuccess, handleError);
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

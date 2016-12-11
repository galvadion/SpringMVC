(function () {
    'use strict';

    angular
        .module('app')
        .factory('RentService', RentService);

    RentService.$inject = ['$http'];

    function RentService($http) {
        var service = {};

        service.GetAllRents = GetAllRents;
        service.GetRentById = GetRentById;
        service.InsertRent = InsertRent;
        service.DeleteRent = DeleteRent;
        service.confirmReturn=confirmReturn;
        
        
        return service;

        
        // Rent functions
        
        function GetAllRents() {
            return $http.get('/SpringMVC/rent/getall').then(handleSuccess, handleError);
        }
        
        function GetRentById(id){
        	return $http.get('/SpringMVC/rent/getbyid?id='+id).then(handleSuccess, handleError);
        }
        
        function InsertRent(rent) {
            return $http.post('/SpringMVC/rent/confirmRent',rent).then(handleSuccess, handleError);
        }
        
        function confirmReturn(rent) {
            return $http.post('/SpringMVC/rent/confirmReturn',rent).then(handleSuccess, handleError);
        }
        
        function DeleteRent(id) {
            return $http.delete('/SpringMVC/rent/delete', id).then(handleSuccess, handleError);
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

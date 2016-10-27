(function () {
    'use strict';

    angular
        .module('app')
        .factory('TariffService', TariffService);

    TariffService.$inject = ['$http'];

    function TariffService($http) {
        var service = {};

        service.GetAllTariffs = GetAllTariffs;
        service.InsertTariff = InsertTariff;
        service.DeleteTariff = DeleteTariff;
        
        
        return service;

        
        // Tariff functions
        
        function GetAllTariffs() {
            return $http.get('/SpringMVC/tariff/getall').then(handleSuccess, handleError);
        }
        
        function InsertTariff(tariff) {
            return $http.post('/SpringMVC/tariff/insert', tariff).then(handleSuccess, handleError);
        }
        
        function DeleteTariff(id) {
            return $http.delete('/SpringMVC/tariff/delete', id).then(handleSuccess, handleError);
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

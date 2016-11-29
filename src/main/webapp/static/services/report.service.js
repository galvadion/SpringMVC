(function () {
    'use strict';

    angular
        .module('app')
        .factory('ReportService', ReportService);

    ReportService.$inject = ['$http'];

    function ReportService($http) {
        var service = {};

        service.getReturnedToday = getReturnedToday;
        service.getPickedToday = getPickedToday;
        
        
        return service;

        
        // Rent functions
        
        function getReturnedToday() {
            return $http.post('/SpringMVC/report/getall').then(handleSuccess, handleError);
        }
        
        function getPickedToday() {
            return $http.post('/SpringMVC/report/insert').then(handleSuccess, handleError);
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

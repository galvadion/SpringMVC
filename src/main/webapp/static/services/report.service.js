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
        service.getBetweenDates = getBetweenDates;
        
        
        return service;

        
        // Rent functions
        
        function getReturnedToday(date) {
            return $http.get('/SpringMVC/report/getreturned?date='+date).then(handleSuccess, handleError);
        }
        
        function getPickedToday(date) {
            return $http.get('/SpringMVC/report/getpickup?date='+date).then(handleSuccess, handleError);
        }
        
        function getBetweenDates(search){
        	return $http.post('/SpringMVC/report/getBookedBetweenDates',search).then(handleSuccess, handleError);
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

(function () {
    'use strict';

    angular
        .module('app')
        .factory('SessionService', SessionService);

    SessionService.$inject = ['$rootScope','$http','$location','FlashService','$timeout'];

    function SessionService($rootScope,$http,$location,FlashService,$timeout) {
        var service = {};
        service.initService = initService;

       

        return service;

        function initService() {
            return $http.get('/SpringMVC/users/loged', brand).then(handleSuccess, handleError('Error comprabando estado sesion'));
        }

        
        // private functions

        function handleSuccess(data) {
        	var response = {};
        	response.success = true;
            return response;
        }
        
        function handleError(data) {
        	var response = {};
    		response.success = false;
    		
    		FlashService.Error("Su sesion ha expirado. Por favor ingresa nuevamente!",true);
            $timeout(function() {
                $location.path('/login');
            },2000);
        }

    }

})();
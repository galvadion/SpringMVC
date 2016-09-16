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
            var id =1;
            return $http.get('/users/users/'+id).then(handle);
        }

        

        function handle(data) {
            if(data.data ==1){
                return true;
            }else{
                handleError(data);
            }
        }

        function handleError(error) {
            FlashService.Error("Your session has expired. Please Log in again!",true);
            $timeout(function() {
                $location.path('/login');
            },2000);
            
        }

    }

})();
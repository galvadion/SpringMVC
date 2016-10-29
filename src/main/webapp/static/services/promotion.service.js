(function () {
    'use strict';

    angular
        .module('app')
        .factory('PromotionService', PromotionService);

    PromotionService.$inject = ['$http'];

    function PromotionService($http) {
        var service = {};

        service.GetAllPromotions = GetAllPromotions;
        service.InsertPromotion = InsertPromotion;
        service.DeletePromotion = DeletePromotion;
        
        
        return service;

        
        // Promotion functions
        
        function GetAllPromotions() {
            return $http.get('/SpringMVC/promotion/getall').then(handleSuccess, handleError);
        }
        
        function InsertPromotion(promotion) {
            return $http.post('/SpringMVC/promotion/insert', promotion).then(handleSuccess, handleError);
        }
        
        function DeletePromotion(id) {
            return $http.delete('/SpringMVC/promotion/delete', id).then(handleSuccess, handleError);
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

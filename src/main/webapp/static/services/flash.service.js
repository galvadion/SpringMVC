(function () {
    'use strict';

    angular
        .module('app')
        .factory('FlashService', FlashService);

    FlashService.$inject = ['$rootScope'];
    function FlashService($rootScope) {
        var service = {};

        service.Success = Success;
        service.Error = Error;
        service.clearFlashMessage = clearFlashMessage;

        initService();

        return service;

        function initService() {
            clearFlashMessage();
            $rootScope.$on('$locationChangeStart', function () {
                clearFlashMessage();
            });

            
        }

        function clearFlashMessage() {
                var flash = $rootScope.flash;

                if (flash) {
                    
                    if (!flash.keepAfterLocationChange) {
                        delete $rootScope.flash;
                    } else {
                        // only keep for a single location change
                        flash.keepAfterLocationChange = false;
                    }
                }
        }
        function Success(message, keepAfterLocationChange) {
            angular.element('#flash-service').fadeIn();
            $rootScope.flash = {
                message: message,
                type: 'success', 
                keepAfterLocationChange: keepAfterLocationChange
            };
        }

        function Error(message, keepAfterLocationChange) {
            angular.element('#flash-service').fadeIn();
            $rootScope.flash = {
                message: message,
                type: 'error',
                keepAfterLocationChange: keepAfterLocationChange
            };
        }
    }

})();
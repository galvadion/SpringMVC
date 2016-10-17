(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModelController', ModelController);

    ModelController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','FileUploader'];
    
    function ModelController($location,UserService, $rootScope, $scope,$timeout,SessionService,FileUploader) {

        var vm = this;
        initController();
        vm.roladmin = $rootScope.roladmin;

        function initController() {
            NProgress.start();

            NProgress.done();
        }
        
        var file = $scope.file = new FileUploader({
            url: 'front.justmob/upload.php',
            queueLimit: 1,  
            removeAfterUpload: true
        });

        
        
        //File uploader methods
        
        file.filters.push({
            name: 'imageFilter',
            fn: function(item,options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpeg|jpg|png|gif|'.indexOf(type) !== -1;
            }
        });

        file.onWhenAddingFileFailed = function(item ,filter, options) {
            
            $rootScope.doFlashMessage("Error File upload Allowed files: gif, png, jpg. Max file size 1Mb",'','error');
        };
        
        file.onAfterAddingAll = function(addedFileItems) {
            if(!angular.isUndefined($scope.file.queue[0])){
                var newFileName = "";
            var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            for( var i=0; i < 16; i++ ){
                newFileName += possible.charAt(Math.floor(Math.random() * possible.length));
            }
            //var extension = $scope.file.queue[0].file.name.split('.').pop();
            //$scope.file.queue[0].file.name = newFileName + '.' + extension;
            $scope.file.queue[0].file.nam = vm.user.id + "_" + $scope.file.queue[0].file.name;
            $scope.file.queue[0].upload();
            }
         };

        file.onSuccessItem = function(fileItem, response, status, headers) {
            if(response.answer=='completed'){
                vm.user.avatarURL = window.location.protocol + "//" + window.location.host + "/front.justmob/uploads/" +$scope.file.queue[0].file.name;  
            }
        };
        
        //End - File uploader methods

    }

})();
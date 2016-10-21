﻿(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModelController', ModelController);

    ModelController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','FileUploader','BrandService','ModelService'];
    
    function ModelController($location,UserService, $rootScope, $scope,$timeout,SessionService,FileUploader,BrandService,ModelService) {

        var vm = this;
        
        vm.roladmin = $rootScope.roladmin;
        vm.requestModel = {};
        vm.allBrands = [];
        vm.allModels = [];
        
        
        initController();
        
        function initController() {
            NProgress.start();
            getAllBrands();
            getAllModels();
        }
        
        function getAllBrands(){
        	BrandService.GetAllBrands(vm.brand).then(function (response) {
        		if(response.success){
        			vm.allBrands = response.data;
        		}
        		else{
        			vm.allBrands = [];
        		}
        		NProgress.done();
        	});
        }
        
        function getAllModels(){
        	ModelService.GetAllModels().then(function (response) {
        		if(response.success){
        			vm.allModels = response.data;
        		}
        		else{
        			vm.allModels = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveModel = function() {
        	NProgress.start();
        	console.log(vm.requestModel);
        	ModelService.CreateModel(vm.requestModel).then(function (response) {
        		console.log(response);
        		
        		NProgress.done();
        	});
        };
        
        
        
        //File uploader methods
        
        var file = $scope.file = new FileUploader({
            url: 'front.justmob/upload.php',
            queueLimit: 1,  
            removeAfterUpload: true
        });

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
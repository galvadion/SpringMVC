﻿(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModelController', ModelController);

    ModelController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','FileUploader','BrandService','ModelService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','$routeParams'];
    
    function ModelController($location,UserService, $rootScope, $scope,$timeout,SessionService,FileUploader,BrandService,ModelService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder,$routeParams) {

        var vm = this;
        
        vm.roladmin = $rootScope.roladmin;
        vm.requestModel = {};
        vm.allBrands = [];
        vm.allModels = [];
        vm.lastYears = [];
        vm.cylinders=[];
        vm.fuelType=[];
        vm.category=[];
        
        vm.dtOptions = DTOptionsBuilder.newOptions().withDOM('dfrtip')
        .withPaginationType('simple_numbers')
        .withDisplayLength(10)
        .withLanguage({
            "sEmptyTable":     "No hay entradas disponibles",
            "sInfo":           "Mostrando _START_ - _END_ de _TOTAL_ entradas",
            "sInfoEmpty":      "Mostrando 0 a 0 de 0 entradas",
            "sInfoFiltered":   "(filtrando desde _MAX_ entradas totales)",
            "sInfoPostFix":    "",
            "sInfoThousands":  ",",
            "sLengthMenu":     "_MENU_",
            "sLoadingRecords": "Cargando...",
            "sProcessing":     "Procesando...",
            "sSearch":         "",
            "sZeroRecords":    "No se encontraron resultados",
            'sSearchPlaceholder': "Buscar...",
            "paginate": {
                "first":      "Primera",
                "last":       "Última",
                "next":       "Siguiente",
                "previous":   "Anterior"
            },
        });

        vm.DTColumnDefs = [
            DTColumnDefBuilder.newColumnDef(3).notSortable(),
            DTColumnDefBuilder.newColumnDef(4).notSortable(),
            DTColumnDefBuilder.newColumnDef(5).notSortable(),
            DTColumnDefBuilder.newColumnDef(6).notSortable(),
            DTColumnDefBuilder.newColumnDef(7).notSortable(),
            DTColumnDefBuilder.newColumnDef(8).notSortable(),
            DTColumnDefBuilder.newColumnDef(9).notSortable(),
            DTColumnDefBuilder.newColumnDef(10).notSortable(),
            DTColumnDefBuilder.newColumnDef(11).notSortable(),
            DTColumnDefBuilder.newColumnDef(12).notSortable(),
            DTColumnDefBuilder.newColumnDef(13).notSortable(),
            DTColumnDefBuilder.newColumnDef(14).notSortable(),
        ];
		
        $scope.dzOptions = {
			url : '/uploadMultipleFile',
			acceptedFiles : 'image/jpeg, images/jpg, image/png',
			paramName : 'photo',
			maxFilesize : '2',
			parallelUploads : 3,
			addRemoveLinks : true,
			autoProcessQueue : true,
			dictDefaultMessage : 'Click o arrastra para agregar fotos',
			dictRemoveFile : 'Eliminar foto',
			dictResponseError : 'Error al subir foto'
		};
		
		$scope.dzMethods = {};
		
		$scope.dzCallbacks = {
			'addedfile' : function(file){
				//cosas que pasan cuando se agregan fotos
				$scope.dzMethods.processQueue();
			},
			'error' : function(file, xhr){
				console.warn('Error al subir foto', file, xhr);
			}
		};
        
        initController();
        
        function initController() {
            NProgress.start();
            vm.location = $location.path().split('/',3);
            if(vm.location[2] == "edit"){
        		getModelById($routeParams.id);
                getAllBrands();
                getLastYears();
                getCylinders();
                getFuelTypes();
                getCategories();
        	}
            else if(vm.location[2] == "create"){
            	vm.requestModel.id = null;
            	getAllBrands();
            	getLastYears();
                getCylinders();
                getFuelTypes();
                getCategories();
            }
            else{
            	getAllModels();
            }
            NProgress.done();
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
        
        function getModelById(id){
        	ModelService.GetModelById(id).then(function (response) {
        		if(response.success){
        			vm.requestModel = response.data;
        			vm.requestModel.airConditioner=vm.requestModel.airConditioner.toString();
        			
        		}
        		else{
        			vm.requestModel = [];
        		}
        		
        		NProgress.done();
        	});
        }
        
        function getLastYears(){
        	var today = new Date();        	
            vm.lastYears.push({'year':today.getFullYear()});
            vm.lastYears.push({'year':today.getFullYear()-1});
            vm.lastYears.push({'year':today.getFullYear()-2});
            vm.lastYears.push({'year':today.getFullYear()-3});
            vm.lastYears.push({'year':today.getFullYear()-4});
        }
        
        function getCylinders(){
        	vm.cylinders.push({'value':800});
        	vm.cylinders.push({'value':1000});
        	vm.cylinders.push({'value':1200});
        	vm.cylinders.push({'value':1400});
        	vm.cylinders.push({'value':1600});
        	vm.cylinders.push({'value':1800});
        	vm.cylinders.push({'value':2000});
        }
        function getCategories(){
        	vm.category.push({'id':1,'value':'S'});
        	vm.category.push({'id':2,'value':'A'});
        	vm.category.push({'id':3,'value':'B'});
        	vm.category.push({'id':4,'value':'C'});
        	vm.category.push({'id':5,'value':'D'});
        }
        function getFuelTypes(){
        	vm.fuelType.push({'id':1,'value':'Nafta'});
        	vm.fuelType.push({'id':2,'value':'Gas Oil'});
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
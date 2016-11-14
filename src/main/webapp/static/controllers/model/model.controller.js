(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModelController', ModelController);

    ModelController.$inject = ['$location','$rootScope','$scope','$timeout','SessionService','FileUploader','BrandService','ModelService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','$routeParams','TariffService','ngDialog'];
    
    function ModelController($location, $rootScope, $scope,$timeout,SessionService,FileUploader,BrandService,ModelService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder,$routeParams,TariffService,ngDialog) {

        var vm = this;
        
        vm.roladmin = $rootScope.roladmin;
        vm.requestModel = {};
        vm.allBrands = [];
        vm.allModels = [];
        vm.lastYears = [];
        vm.cylinders=[];
        vm.fuelType=[];
        vm.category=[];
        vm.images = [];
        
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
        	TariffService.getAllCategories().then(function (response) {
        		if(response.success){
        			vm.category = response.data;
        		}
        		else{
        			vm.category = [];
        		}
        		NProgress.done();
        	});
        }
        function getFuelTypes(){
        	TariffService.getAllFuelTypes().then(function (response) {
        		if(response.success){
        			vm.fuelType = response.data;
        		}
        		else{
        			vm.fuelType = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveModel = function() {
        	NProgress.start();
        	ModelService.CreateModel(vm.requestModel).then(function (response) {
        		if(response.success){
        			if(!angular.isUndefined($scope.file.queue[0])){
        				$scope.file.queue[0].id = response.data.id;
        				$scope.file.queue[0].name = response.data.id + "-" +response.data.name;
        				console.log($scope.file.queue[0]);
        				$scope.file.queue[0].upload();
        			}
        			$rootScope.doFlashMessage("Modelo guardado con exito",'/model','success');
        		}
        		else{
        			$rootScope.doFlashMessage("Error al guardar",'','error');
        		}
        		NProgress.done();
        	});
        };
        
        
        
        //File uploader methods
        
        var file = $scope.file = new FileUploader({
            url: '/SpringMVC/upload/uploadFile',
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
            $rootScope.doFlashMessage("Error, formatos permitidos: gif, png, jpg. Tamaño máximo 1Mb",'','error');
        };
        
        //End - File uploader methods

        
        $scope.deleteModel = function (id) {
        	NProgress.start();
        	ModelService.DeleteModel(id).then(function (response) {
        		if(response.success){
        			$rootScope.doFlashMessage('Modelo eliminado con éxito','','success');
        			initController();
        		}
        		else{
        			$rootScope.doFlashMessage("Error, intente nuevamente",'','error');
        		}
        		NProgress.done();
        	});
        };
        
        $scope.openDialog = function (model) {
        	vm.auxModel = angular.copy(model);
            ngDialog.openConfirm({
                template: 'modalDialog',
                className: 'ngdialog-theme-default',
                scope: $scope,
            }).then(function (value) {}, function (reason) {});
        };
        
    }

})();
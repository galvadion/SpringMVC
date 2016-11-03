(function () {
    'use strict';

    angular
        .module('app')
        .controller('BranchofficeController', BranchofficeController);

    BranchofficeController.$inject = ['$location','$rootScope','$routeParams','$scope','$timeout','SessionService','BranchofficeService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','ngDialog'];
    
    function BranchofficeController($location, $rootScope, $routeParams, $scope, $timeout, SessionService, BranchofficeService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder, ngDialog) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.branchoffice = {};
        vm.auxBranchoffice = {};
        vm.allBranchoffices = [];
        vm.location = "";
        
        $scope.map = {
        		center: { 
        			latitude: -34.901113,
        			longitude: -56.164531
        		},
        		zoom: 14,
        };
        
        $scope.options = {
        		scrollwheel: false
	    };
        
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
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3).notSortable(),
            DTColumnDefBuilder.newColumnDef(4).notSortable(),
            DTColumnDefBuilder.newColumnDef(5).notSortable(),
            DTColumnDefBuilder.newColumnDef(6).notSortable(),
            DTColumnDefBuilder.newColumnDef(7).notSortable(),
        ];
        
        initController();
        
        function initController() {
            NProgress.start();
            
            vm.location = $location.path().split('/',3);
            
            if(vm.location[2] == "edit"){
        		getBranchofficeById($routeParams.id);
        	}
            else if(vm.location[2] != "create"){
            	getAllBranchoffices();
            }
            
            NProgress.done();
        }
        
        function getAllBranchoffices(){
        	BranchofficeService.GetAllBranchoffices().then(function (response) {
        		if(response.success){
        			vm.allBranchoffices = response.data;
        		}
        		else{
        			vm.allBranchoffices = [];
        		}
        		NProgress.done();
        	});
        }
        
        function getBranchofficeById(id){
        	BranchofficeService.GetBranchofficeById(id).then(function (response) {
        		if(response.success){
        			vm.branchoffice = response.data;
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveBranchoffice = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.branchoffice.id){
        		mgsSuccess = "Marca editada con éxito";
        		mgsError = "Error al editar marca";
        		
        	}
        	else{
        		mgsSuccess = "Marca creada con éxito";
        		mgsError = "Marca ya existente";
        	}
        	
        	BranchofficeService.InsertBranchoffice(vm.branchoffice).then(function (response) {
        		NProgress.start();
        		if(response.success){
        			getAllBranchoffices();
        			$rootScope.doFlashMessage(mgsSuccess,'','success');
        			$scope.cleanInput();
        		}
        		else{
        			$rootScope.doFlashMessage(mgsError,'','error');
        		}
        		NProgress.done();
        	});
        };
        
        $scope.deleteBranch = function (id) {
        	NProgress.start();
        	BranchofficeService.DeleteBranchoffice(id).then(function (response) {
        		if(response.success){
        			$rootScope.doFlashMessage("Sucursal eliminada con éxito",'','success');
        			initController();
        		}
        		else{
        			$rootScope.doFlashMessage("Error, intente nuevamente",'','error');
        		}
        		NProgress.done();
        	});
        };
        
        $scope.openDialog = function (branchoffice) {
        	vm.auxBranchoffice = angular.copy(branchoffice);
            ngDialog.openConfirm({
                template: 'modalDialog',
                className: 'ngdialog-theme-default',
                scope: $scope,
            }).then(function (value) {}, function (reason) {});
        };

    }

})();
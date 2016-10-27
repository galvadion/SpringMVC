(function () {
    'use strict';

    angular
        .module('app')
        .controller('BranchofficeController', BranchofficeController);

    BranchofficeController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','BranchofficeService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    
    function BranchofficeController($location, UserService, $rootScope, $scope, $timeout, SessionService, BranchofficeService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.branchoffice = {};
        vm.allBranchoffices = [];
        
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
            DTColumnDefBuilder.newColumnDef(1).notSortable(),
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3).notSortable(),
        ];
        
        initController();
        
        function initController() {
            NProgress.start();
            getAllBranchoffices();
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
        
        $scope.cleanInput = function() {
        	vm.branchoffice = {};
        	$scope.form.$setPristine();
        };
        
        $scope.editBranchoffice = function(brand) {
        	vm.branchoffice = angular.copy(brand);
        	$scope.scrollTo( "#wrap");
        };
        
        $scope.deleteBranchoffice = function(id) {
        	NProgress.start();
        	BranchofficeService.DeleteBranchoffice(id).then(function (response) {
        		if(response.success){
        			getAllBranchoffices();
        			$rootScope.doFlashMessage('Marca eliminada','','success');
        		}
        		else{
        			$rootScope.doFlashMessage('Error al eliminar','','error');
        		}
        		NProgress.done();
        	});
        };
        
        $scope.scrollTo = function(element) {
            $( 'html, body').animate({
                scrollTop: $(element).offset().top
            }, 500);
        }

    }

})();
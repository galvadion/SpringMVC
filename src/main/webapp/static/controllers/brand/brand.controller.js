(function () {
    'use strict';

    angular
        .module('app')
        .controller('BrandController', BrandController);

    BrandController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','BrandService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    
    function BrandController($location, UserService, $rootScope, $scope, $timeout, SessionService, BrandService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.brand = {};
        vm.allBrands = [];
        
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
            getAllBrands();
        }
        
        function getAllBrands(){
        	BrandService.GetAllBrands().then(function (response) {
        		if(response.success){
        			vm.allBrands = response.data;
        		}
        		else{
        			vm.allBrands = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveBrand = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.brand.id){
        		mgsSuccess = "Marca editada con éxito";
        		mgsError = "Error al editar marca";
        		
        	}
        	else{
        		mgsSuccess = "Marca creada con éxito";
        		mgsError = "Marca ya existente";
        	}
        	
        	BrandService.InsertBrand(vm.brand).then(function (response) {
        		if(response.success){
        			getAllBrands();
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
        	vm.brand = {};
        	$scope.form.$setPristine();
        };
        
        $scope.editBrand = function(brand) {
        	vm.brand = angular.copy(brand);
        	$scope.scrollTo( "#wrap");
        };
        
        $scope.deleteBrand = function(id) {
        	NProgress.start();
        	BrandService.DeleteBrand(id).then(function (response) {
        		if(response.success){
        			getAllBrands();
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
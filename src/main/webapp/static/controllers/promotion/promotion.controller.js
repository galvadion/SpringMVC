(function () {
    'use strict';

    angular
        .module('app')
        .controller('PromotionController', PromotionController);

    PromotionController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','PromotionService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    
    function PromotionController($location, UserService, $rootScope, $scope, $timeout, SessionService, PromotionService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
        vm.promotion = {};
        vm.allPromotions = [];
        
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
            getAllPromotions();
        }
        
        function getAllPromotions(){
        	PromotionService.GetAllPromotions().then(function (response) {
        		if(response.success){
        			vm.allPromotions = response.data;
        		}
        		else{
        			vm.allPromotions = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.savePromotion = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.promotion.id){
        		mgsSuccess = "Marca editada con éxito";
        		mgsError = "Error al editar marca";
        		
        	}
        	else{
        		mgsSuccess = "Marca creada con éxito";
        		mgsError = "Marca ya existente";
        	}
        	
        	PromotionService.InsertPromotion(vm.promotion).then(function (response) {
        		if(response.success){
        			getAllPromotions();
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
        	vm.promotion = {};
        	$scope.form.$setPristine();
        };
        
        $scope.editPromotion = function(brand) {
        	vm.promotion = angular.copy(brand);
        	$scope.scrollTo( "#wrap");
        };
        
        $scope.deletePromotion = function(id) {
        	NProgress.start();
        	PromotionService.DeletePromotion(id).then(function (response) {
        		if(response.success){
        			getAllPromotions();
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
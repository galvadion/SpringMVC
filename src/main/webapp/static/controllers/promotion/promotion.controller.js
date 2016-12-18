(function () {
    'use strict';

    angular
        .module('app')
        .controller('PromotionController', PromotionController);

    PromotionController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','PromotionService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','ModelService','BranchofficeService'];
    
    function PromotionController($location, UserService, $rootScope, $scope, $timeout, SessionService, PromotionService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder,ModelService,BranchofficeService) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
        vm.promotion = {};
        vm.allPromotions = [];
        vm.allModels= [];
        vm.allOffices = [];
        
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
        
		var localDate = new Date();
		localDate = localDate.getFullYear() + '-' + (localDate.getMonth() + 1)
				+ '-' + localDate.getDate();
		$('.date').datetimepicker({
			language : 'es',
			weekStart : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			startDate : localDate
		});

        vm.DTColumnDefs = [
            DTColumnDefBuilder.newColumnDef(4).notSortable(),
            DTColumnDefBuilder.newColumnDef(5).notSortable(),
            DTColumnDefBuilder.newColumnDef(6).notSortable(),
        ];
        
        initController();
        
        function initController() {
            NProgress.start();
            getAllPromotions();
            getAllModels();
            getAllOffices();
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
        
        function getAllOffices() {
			BranchofficeService.GetAllBranchoffices().then(function(response) {
				if (response.success) {
					if (response.data.length > 0) {
						vm.allOffices = response.data;
					}
				} else {
					vm.allOffices = [];
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
        
        $scope.savePromotion = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.promotion.id){
        		mgsSuccess = "Promocion modificada con éxito";
        		mgsError = "Error al editar marca";
        		
        	}
        	else{
        		mgsSuccess = "Promocion creada con éxito";
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
        $scope.generateText = function (){
        	console.log("its generating");
        	vm.promotion.promotionCode=randomString(8,'0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
        }
        
        function randomString(length, chars) {
            var result = '';
            for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
            return result;
        }
        var rString = randomString(8, '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');

    }

})();
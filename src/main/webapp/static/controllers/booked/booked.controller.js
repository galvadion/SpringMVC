(function () {
    'use strict';

    angular
        .module('app')
        .controller('BookedController', BookedController);

    BookedController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','BookedService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','$routeParams','ModelService','BranchofficeService'];
    
    function BookedController($location, UserService, $rootScope, $scope, $timeout, SessionService, BookedService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder,$routeParams,ModelService,BranchofficeService) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.booked = {};
        vm.allBookeds = [];
        vm.search = {};
        vm.searchResult = [];
        vm.allOffices = [];
        
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

		$scope.checkEndDate = function() {
			var firstDate = moment(vm.search.beginDate, "dd/mm/yyyy");
			var lastDate = moment(vm.search.endDate, "dd/mm/yyyy");
			var isAfter = firstDate.isAfter(lastDate);
			if (isAfter) {
				$scope.endDateError = true;
			} else {
				$scope.endDateError = false;
			}
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
            DTColumnDefBuilder.newColumnDef(1).notSortable(),
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3).notSortable(),
        ];
        
        $scope.searchModels = function() {
        	console.log(vm.search);
        	ModelService.SearchModels(vm.search).then(function (response) {
        		if(response){
        			vm.searchResult = response.data;
        			console.log(vm.searchResult);
        		}
        	});
        };
        
        initController();
        
        function initController() {
            NProgress.start();
            
            vm.location = $location.path().split('/',3);
            
            if(vm.location[1] == "search"){
            	getAllOffices();
            	vm.search.officeOriginId = parseInt($routeParams.origin);
            	vm.search.officeEndId = parseInt($routeParams.destination);
            	vm.search.beginDate = $routeParams.from;
            	vm.search.endDate = $routeParams.to;
            	
            	if(!vm.search.airConditioner){
            		vm.search.airConditioner = true;
            	}
            	if(!vm.search.passangers){
            		vm.search.passangers = 0;
            	}
            	if(!vm.search.luggage){
            		vm.search.luggage = 0;
            	}
            	$scope.searchModels();
            }
            else if(vm.location[2] == "edit"){
        		getBookedById($routeParams.id);
        	}
            else if(vm.location[2] == "create"){
            	vm.user.id = null;
                vm.user.active = true;
            }
            else{
            	getAllBookeds();
            }
            
            NProgress.done();
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
        
        function getAllBookeds(){
        	BookedService.GetAllBookeds().then(function (response) {
        		if(response.success){
        			vm.allBookeds = response.data;
        		}
        		else{
        			vm.allBookeds = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveBooked = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.booked.id){
        		mgsSuccess = "Marca editada con éxito";
        		mgsError = "Error al editar marca";
        		
        	}
        	else{
        		mgsSuccess = "Marca creada con éxito";
        		mgsError = "Marca ya existente";
        	}
        	
        	BookedService.InsertBooked(vm.booked).then(function (response) {
        		if(response.success){
        			getAllBookeds();
        			$rootScope.doFlashMessage(mgsSuccess,'','success');
        			$scope.cleanInput();
        		}
        		else{
        			$rootScope.doFlashMessage(mgsError,'','error');
        		}
        		NProgress.done();
        	});
        };
        
        $scope.deleteBooked = function(id) {
        	NProgress.start();
        	BookedService.DeleteBooked(id).then(function (response) {
        		if(response.success){
        			getAllBookeds();
        			$rootScope.doFlashMessage('Marca eliminada','','success');
        		}
        		else{
        			$rootScope.doFlashMessage('Error al eliminar','','error');
        		}
        		NProgress.done();
        	});
        };

    }

})();
(function () {
    'use strict';

    angular
        .module('app')
        .controller('BookedController', BookedController);

    BookedController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','BookedService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','$routeParams','ModelService','BranchofficeService'];
    
    function BookedController($location, UserService, $rootScope, $scope, $timeout, SessionService, BookedService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder,$routeParams,ModelService,BranchofficeService) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
        vm.booked = {};
        vm.allBookeds = [];
        vm.search = {};
        vm.searchResult = [];
        vm.allOffices = [];
        vm.noResult = true;
        vm.promo={};
        
        var today = new Date();
        today = today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getFullYear();
		
        $('#beginDate').datetimepicker({
			language : 'es',
			weekStart : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			startDate : today
		});
       
		
        var tomorrow = new Date();
        tomorrow = (tomorrow.getDate() + 1) + '/' + (tomorrow.getMonth() + 1) + '/' + tomorrow.getFullYear();

		$('#endDate').datetimepicker({
			language : 'es',
			weekStart : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			startDate : tomorrow
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
		
		$scope.scrollTo = function(element) {
        	$( 'html, body').animate({
        		scrollTop: $(element).offset().top
        	}, 2000);
        }
		
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
            DTColumnDefBuilder.newColumnDef(9).notSortable(),
            DTColumnDefBuilder.newColumnDef(10).notSortable(),
            DTColumnDefBuilder.newColumnDef(11).notSortable(),
        ];
        
        vm.DTColumnDefsClient = [
           DTColumnDefBuilder.newColumnDef(11).notSortable(),
           DTColumnDefBuilder.newColumnDef(12).notSortable(),
           DTColumnDefBuilder.newColumnDef(13).notSortable(),
       ];
        
        $scope.searchModels = function(searchObject) {
        	NProgress.start();
        	ModelService.SearchModels(searchObject).then(function (response) {
        		if(response){
        			if(response.data.length > 0){
        				vm.searchResult = response.data;
        				vm.noResult = false;
        				$scope.scrollTo( "#searchResult");
        			}
        			else{
        				vm.searchResult = [];
        				vm.noResult = true;
        			}
        		}
        		NProgress.done();
        	});
        };
        
        initController();
        
        function initController() {
            NProgress.start();
            
            vm.location = $location.path().split('/',3);
            
            if(vm.location[1] == "search"){
            	getAllOffices();
            	if($routeParams.origin){
            		vm.search.officeOriginId = parseInt($routeParams.origin);
            	}
            	if($routeParams.destination){
            		vm.search.officeEndId = parseInt($routeParams.destination);
            	}
            	if($routeParams.from){
            		vm.search.beginDate = $routeParams.from;
            	}
            	if($routeParams.to){
            		vm.search.endDate = $routeParams.to;
            	}
            	if(!vm.search.airConditioner){
            		vm.search.airConditioner = true;
            	}
            	if(!vm.search.passangers){
            		vm.search.passangers = 0;
            	}
            	if(!vm.search.luggage){
            		vm.search.luggage = 0;
            	}
            	if($routeParams.origin && $routeParams.destination && $routeParams.from && $routeParams.to){
            		$scope.searchModels(vm.search);
            	}
            	else{
            		vm.search.officeOriginId = 1;
                    vm.search.officeEndId = 1;
                    vm.search.beginDate = today;
                    vm.search.endDate = tomorrow;
                    vm.search.airConditioner = true;
                    vm.search.passangers = 0;
                    vm.search.luggage = 0;
            		$scope.searchModels(vm.search);
            		
            	}
            }
            else if(vm.location[2] == "edit"){
        		getBookedById($routeParams.id);
        	}
            else if(vm.location[2] == "create"){
            	vm.user.id = null;
                vm.user.active = true;
            }
            else if(vm.location[2] == "details"){
        		getBookedById($routeParams.id);
        	}
            else{
            	if(vm.roladmin || vm.rolemployee){
            		getAllBookeds();
            	}
            	else if(vm.rolclient){
            		getBookedsByClient();
            	}
            	
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
        
        function getBookedsByClient(){
        	BookedService.GetBookedsByClient($rootScope.globals.currentUser.id).then(function (response) {
        		if(response.success){
        			vm.allBookeds = response.data;
        		}
        		else{
        			vm.allBookeds = [];
        		}
        		NProgress.done();
        	});
        }
        
        function getBookedById(){
        	BookedService.GetBookedById($routeParams.id).then(function (response) {
        		if(response.success){
        			vm.booked = response.data;
        		}
        		else{
        			vm.booked = {};
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
        
        $scope.reservar = function(modelId){
        	$rootScope.modelId = modelId;
        	$rootScope.dateInitial = vm.search.beginDate;
        	$rootScope.dateEnding = vm.search.endDate;
        	$rootScope.officeInitial = vm.search.officeOriginId;
        	$rootScope.officeEnding = vm.search.officeEndId;
        	
        	$location.path("/payment/booking");
        }
        
        $scope.cancelBooking = function(id){
        	alert('You shall not pass !!');
        }

    }

})();
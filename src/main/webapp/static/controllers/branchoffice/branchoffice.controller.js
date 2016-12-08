(function () {
    'use strict';

    angular
        .module('app')
        .controller('BranchofficeController', BranchofficeController);

    BranchofficeController.$inject = ['$location','$rootScope','$routeParams','$scope','$timeout','SessionService','BranchofficeService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder','ngDialog'];
    
    function BranchofficeController($location, $rootScope, $routeParams, $scope, $timeout, SessionService, BranchofficeService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder, ngDialog) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
        vm.branchoffice = {};
        vm.branchoffice.location= {};
        vm.auxBranchoffice = {};
        vm.allBranchoffices = [];
        vm.location = "";
        
        $scope.map = {
        		center : {
    				latitude : -32.75948505325886,
    				longitude : -56.165714263916016
    			},
        		zoom: 6,
        		markers: [],
        	    events: {
        	        click: function(map, eventName, originalEventArgs) {
        	        	var e = originalEventArgs[0];
                        var lat = e.latLng.lat(),lon = e.latLng.lng();
                        var marker = {
                            id: Date.now(),
                            coords: {
                                latitude: lat,
                                longitude: lon
                            }
                        };
                        vm.branchoffice.location.longitude=lon;
                        vm.branchoffice.location.latitude=lat;
                    /*    document.getElementById('coordinateX').value=lon;
                        document.getElementById('coordinateY').value=lat;*/
                        $scope.map.markers.pop();
                        $scope.map.markers.push(marker);
                        $scope.$apply();
        	        }
        	     }
        };
        
        $scope.options = {
        		scrollwheel: true
	    };
        
       /* google.maps.event.addListener($scope.map, 'click', function( event ){
            
            document.getElementById('coordinateX').value=event.latLng.lat();
            document.getElementById('coordinateY').value=event.latLng.lng();
            var marcador = new google.maps.LatLng(event.latLng.lat(),event.latLng.lng());
            var marker = new google.maps.Marker({
          position: marcador,
          draggable:true,
          animation: google.maps.Animation.DROP,
          map: map,
          title: ''
      });
      markers.push(marker);
      if(markers.length==1){
          markers[0].setMap(map);
      }else{
          markers[0].setMap(null);
          markers.splice(0,1);
          marker.setMap(map);    
      }
      
            
        });*/
        
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
        			$scope.map.markers.push({
						id : response.data.id,
						coords : {
							latitude : response.data.location.latitude,
							longitude : response.data.location.longitude
						},
						title : response.data.name
					});
        		}
        		NProgress.done();
        	});
        }
        
        $scope.saveBranchOffice = function() {
        	NProgress.start();
        	var mgsSuccess = "";
        	var mgsError = "";
        	
        	if(vm.branchoffice.id){
        		mgsSuccess = "Oficina editada con éxito";
        		mgsError = "Error al editar oficina";
        		
        	}
        	else{
        		mgsSuccess = "Oficina creada con éxito";
        		mgsError = "Oficina ya existente";
        	}
        	
        	BranchofficeService.InsertBranchoffice(vm.branchoffice).then(function (response) {
        		NProgress.start();
        		if(response.success){
        			getAllBranchoffices();
        			$rootScope.doFlashMessage(mgsSuccess,'/branchoffice','success');
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
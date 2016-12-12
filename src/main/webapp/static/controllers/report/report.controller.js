(function () {
    'use strict';

    angular
        .module('app')
        .controller('ReportController', ReportController);

    ReportController.$inject = ['$location','UserService','$rootScope','$scope','$timeout','SessionService','ReportService','DTOptionsBuilder','DTColumnBuilder','DTColumnDefBuilder'];
    
    function ReportController($location, UserService, $rootScope, $scope, $timeout, SessionService, ReportService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder) {

        var vm = this;
        vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
        vm.pickedToday = [];
        vm.returnedToday =[];
        vm.resultList=[];
        vm.search={};
        
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
        

		$('.date').datetimepicker({
			language : 'es',
			weekStart : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});

        vm.DTColumnDefs = [
            DTColumnDefBuilder.newColumnDef(1).notSortable(),
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3).notSortable(),
        ];
        
        initController(new Date());
        
        function initController(date) {
            NProgress.start();
            var localDate = date;
            getPickedToday(formatDate(localDate));
            getReturnedToday(formatDate(localDate));
            $scope.yesterdayPick =new Date();
            $scope.yesterdayReturn=new Date();
            $scope.tomorrowPick=new Date();
            $scope.tomorrowReturn=new Date();
            $scope.tomorrowPick.setDate(localDate.getDate()+1);
            $scope.tomorrowReturn.setDate(localDate.getDate()+1);
            $scope.yesterdayPick.setDate(localDate.getDate()-1);
            $scope.yesterdayReturn.setDate(localDate.getDate()-1);
            console.log($scope.yesterdayPick);
            console.log(localDate)
            $scope.hoy="hoy";
            $scope.hoyRet="hoy";
        }
        
        
        
        function formatDate(date) {
            var d = new Date(date),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate(),
                year = d.getFullYear();

            if (month.length < 2) month = '0' + month;
            if (day.length < 2) day = '0' + day;

            return [year, month, day].join('-');
        }
        
        function getPickedToday(date){
        	ReportService.getPickedToday(date).then(function (response) {
        		if(response.success){
        			vm.pickedToday = response.data;
        		}
        		else{
        			vm.pickedToday = [];
        		}
        		NProgress.done();
        	});
        }
        
        $scope.pickedup = function(date){
        	var aux=date;
        	getPickedToday(formatDate(date));$scope.hoy=formatDate(date);
        	$scope.yesterdayPick.setDate(date.getDate()-1);
        	$scope.tomorrowPick.setDate(aux.getDate()+1);
        }
        
        $scope.returned = function(date){
        	var aux=date;
        	getReturnedToday(formatDate(date));$scope.hoyRet=formatDate(date);
        	$scope.yesterdayReturn.setDate(date.getDate()-1);
        	$scope.tomorrowReturn.setDate(aux.getDate()+1);
        }
        
        $scope.getBetweenDates = function (){
        	ReportService.getBetweenDates(vm.search).then(function (response) {
        		if(response.success){
        			vm.resultList = response.data;
        		}
        		else{
        			vm.resultList = [];
        		}
        		NProgress.done();
        	});
        }
        
        
        
        
        function getReturnedToday(date){
        	ReportService.getReturnedToday(date).then(function (response) {
        		if(response.success){
        			vm.returnedToday = response.data;
        		}
        		else{
        			vm.returnedToday = [];
        		}
        		NProgress.done();
        	});
        }
        
        
        
        $scope.scrollTo = function(element) {
            $( 'html, body').animate({
                scrollTop: $(element).offset().top
            }, 500);
        }

    }

})();
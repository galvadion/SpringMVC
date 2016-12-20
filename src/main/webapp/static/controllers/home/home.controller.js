(function() {
	'use strict';

	angular.module('app').controller('HomeController', HomeController);

	HomeController.$inject = [ '$location', 'UserService', '$rootScope',
			'$scope', '$timeout', 'SessionService', 'BranchofficeService','ReportService' ];

	function HomeController($location, UserService, $rootScope, $scope,
			$timeout, SessionService, BranchofficeService,ReportService) {

		var vm = this;
		vm.roladmin = $rootScope.roladmin;
        vm.rolemployee = $rootScope.rolemployee;
        vm.rolclient = $rootScope.rolclient;
		vm.allOffices = [];
		vm.search = {};
		vm.pickedToday = [];
		

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
			var firstDate = moment(vm.search.beginDate, "DD-MM-YYYY");
			var lastDate = moment(vm.search.endDate, "DD-MM-YYYY");
			var isAfter = firstDate.isAfter(lastDate);
			if (isAfter) {
				$scope.endDateError = true;
			} else {
				$scope.endDateError = false;
			}
		};

		$scope.map = {
			center : {
				latitude : -32.75948505325886,
				longitude : -56.165714263916016
			},
			zoom : 6,
			markers : [],
		};

		$scope.options = {
			scrollwheel : false
		};

		$scope.searchModels = function() {
			NProgress.start();
			$location.path("/search/origin=" + vm.search.officeOriginId + "&destination=" + vm.search.officeEndId + "&from=" + vm.search.beginDate.replace(/[/]/g , "-") + "&to=" + vm.search.endDate.replace(/[/]/g , "-"));
		};

		$scope.onClick = function(marker, eventName, model) {
			model.show = !model.show;
		};

		$scope.closeClick = function() {
			$scope.windowOptions.visible = false;
		};

		initController();

		function initController() {
			NProgress.start();
			getAllOffices();
			var localDate = new Date();
            getPickedToday(formatDate(localDate));
            $scope.tomorrowPick=new Date();
			$scope.yesterdayPick =new Date();
			$scope.tomorrowPick.setDate(localDate.getDate()+1);
            $scope.yesterdayPick.setDate(localDate.getDate()-1);
            $scope.hoy="hoy";
			NProgress.done();
		}
		
	       $scope.pickedup = function(date){
	        	var aux=date;
	        	console.log(date)
	        	console.log(aux)
	        	getPickedToday(formatDate(date));$scope.hoy=formatDate(date);
	        	$scope.yesterdayPick.setDate(date.getDate()-1);
	        	$scope.tomorrowPick.setDate(aux.getDate()+1);
	        	console.log(date.getDate()-1)
	        	console.log(aux.getDate()+1)
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

		function getAllOffices() {
			BranchofficeService.GetAllBranchoffices().then(function(response) {
				if (response.success) {
					if (response.data.length > 0) {
						vm.allOffices = response.data;
						angular.forEach(vm.allOffices, function(value, key) {
							$scope.map.markers.push({
								id : value.id,
								coords : {
									latitude : value.location.latitude,
									longitude : value.location.longitude
								},
								title : value.name
							});
						});
						$scope.$apply();
					}
				} else {
					vm.allOffices = [];
				}
				NProgress.done();
			});
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
	}

})();
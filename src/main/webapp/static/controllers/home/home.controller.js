(function() {
	'use strict';

	angular.module('app').controller('HomeController', HomeController);

	HomeController.$inject = [ '$location', 'UserService', '$rootScope',
			'$scope', '$timeout', 'SessionService', 'BranchofficeService' ];

	function HomeController($location, UserService, $rootScope, $scope,
			$timeout, SessionService, BranchofficeService) {

		var vm = this;
		vm.roladmin = $rootScope.roladmin;
		vm.allOffices = [];
		vm.search = {};
		vm.searchResult = [];

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
			$location.path("/search/origin=" + vm.search.officeOriginId
					+ "&destination=" + vm.search.officeEndId + "&from="
					+ vm.search.beginDate + "&to=" + vm.search.endDate);
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

			getAllOffers();
			getAllOffices();
			NProgress.done();
		}

		function getAllOffers() {

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
	}

})();
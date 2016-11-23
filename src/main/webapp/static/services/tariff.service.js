(function () {
	'use strict';

	angular
	.module('app')
	.factory('TariffService', TariffService);

	TariffService.$inject = ['$http'];

	function TariffService($http) {
		var service = {};

		service.getAllFuelTypes = getAllFuelTypes;
		service.getAllCategories =getAllCategories;
		service.InsertCategory = InsertCategory;
		service.InsertFuelType = InsertFuelType;
		service.DeleteTariff = DeleteTariff;
		service.getAllExtras = getAllExtras;
		service.InsertExtra =InsertExtra;


		return service;


		// Tariff functions

		function getAllFuelTypes() {
			return $http.get('/SpringMVC/fuelType/getall').then(handleSuccess, handleError);
		}

		function getAllCategories() {
			return $http.get('/SpringMVC/category/getall').then(handleSuccess, handleError);
		}

		function InsertFuelType(tariff) {
			return $http.post('/SpringMVC/fuelType/insert', tariff).then(handleSuccess, handleError);
		}

		function InsertCategory(tariff) {
			return $http.post('/SpringMVC/category/insert', tariff).then(handleSuccess, handleError);
		}

		function DeleteTariff(id) {
			return $http.delete('/SpringMVC/tariff/delete', id).then(handleSuccess, handleError);
		}
		function getAllExtras() {
			return $http.get('/SpringMVC/extras/getall').then(handleSuccess, handleError);
		}
		function InsertExtra(tariff) {
			return $http.post('/SpringMVC/extras/insert', tariff).then(handleSuccess, handleError);
		}


		// private functions

		function handleSuccess(data) {
			var response = {};
			response.success = true;
			response.data = data.data;
			return response;
		}

		function handleError(data) {
			var response = {};
			response.success = false;
			response.data = data.data;
			return response;
		}
	}

})();

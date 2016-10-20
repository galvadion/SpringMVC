(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);        

    UserController.$inject = ['$routeParams','$timeout','$rootScope','$scope','$location', 'UserService', 'FlashService','FileUploader','SessionService','ngDialog'];
    function UserController($routeParams,$timeout,$rootScope,$scope,$location, UserService, FlashService,FileUploader,SessionService,ngDialog) {
        
        var vm = this;

        vm.user = {};
        vm.master ={};
        vm.auxuser = {};
        vm.users = {};
        vm.roladmin = $rootScope.roladmin;
        vm.rolclient = false;
        vm.edit = true;
        vm.saveUserFlag = false;
        vm.newpassword = "";
        vm.rol = "";
        
        var currentUserRol;
        

        initController();

        function initController(){
            NProgress.start();
            //SessionService.initService();

            NProgress.done();
        }

        function getAllUsers(){
        	UserService.GetAll().then(function (response) {
        		if(response.users.length > 1){
        			vm.users = response.users;
        		}
        		angular.forEach(vm.users, function(value, key){
                    if(value.id == vm.user.id){
                    	delete vm.users[key];
                    }
                 });
        	});
        }
        
        
        $scope.deleteUser = function(coll,idx){
            NProgress.start();
                var index = -1;
                var aux = eval(coll);
                for(var i = 0; i < aux.length; i++){
                    if(aux[i].id === coll[idx].id){
                        index = i;
                        break;
                    }
                }
                if( index === -1 ) {
                    $rootScope.doFlashMessage('Something gone wrong','','error');
                }
                else{      
                        vm.user.id = parseInt(coll[index].id);
                        UserService.Delete(vm.user.id).then(function (response) {
                        if (response.statusText == "OK") {
                            $rootScope.doFlashMessage('Delete User successful','','success');
                            coll.splice(idx, 1);
                        }
                        else {
                            $rootScope.doFlashMessage(response.error,'','error',3000);
                            vm.dataLoading = false;
                        }
                    });
                        NProgress.done();
                }
        }


        
    }

})();

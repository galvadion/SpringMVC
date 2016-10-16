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
        vm.roladmin = false;
        vm.rolclient = false;
        vm.edit = true;
        vm.saveUserFlag = false;
        vm.newpassword = "";
        vm.rol = "";
        
        var currentUserRol;
        

        initController();

//DE ACA HASTA LA LINEA HAY QUE DEJAR ANDANDO
        function initController(){
            NProgress.start();
            //SessionService.initService();

            UserService.GetUserById().then(function (response) {
            	vm.user = response.user;
            	if(vm.user/*.rol*/ == "admin"){
            		getAllUsers();
                	vm.roladmin = true;
            	}
                else{
                     vm.edit = false;
                }
                vm.auxuser = angular.copy(vm.user);
            });
            
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
////////////////////////////////////////////////////////////////YO SOY LA LINEA
        
        
        ///View Functions///
        
        $scope.scrollTo = function(element) {
            $( 'html, body').animate({
                scrollTop: $(element).offset().top
            }, 500);
        }
        
        $scope.checkClient = function () {
            if(vm.rol == "client") {
                vm.rolclient = true;
            } else {
                vm.rolclient = false;
            }
        }
        
        $scope.checkPaswords = function () {
            if(vm.user.password == vm.newpassword) {
                vm.saveUserFlag = false;
            } else {
                vm.saveUserFlag = true;
            }
        }
        
        $scope.enablePasswordEdit = function() {         
            vm.saveUserFlag = true;
            angular.element('input[type=password]').removeAttr('disabled','false');
        }
        
        $scope.newUser = function(){
            $scope.form.$setPristine();
            vm.user  = {};
            vm.user.id = null;
            vm.edit = false;
        }
        
        $scope.resetUser = function(){
            if($rootScope.globals.currentUser){
                vm.user = angular.copy(vm.auxuser);
            }
            return false;
        }
        
        $scope.editUser = function(user){
            $scope.form.$setPristine();
            vm.edit = false;
            vm.user  = angular.copy(vm.master);
            angular.forEach(vm.users, function(value, key){
                if(value.id == user.id){
                    vm.user = value;
                    //vm.user.password = '';
                    $scope.scrollTo( ".pageheader");
                }   
            });
        }

        
        
      ///ABM Functions///
        
        $scope.saveUser = function() {
            NProgress.start();
            if(!vm.user.id){
                UserService.CheckEmail(vm.user.email).then(function (response) {
                    if(response.data.exist != true){
                        vm.saveUserFlag = true;
                        vm.user.rol = vm.rol;
                        UserService.Create(vm.user.name,vm.user.lastname,vm.user.email,vm.user.password,vm.user.address,vm.user.rol).then(function (response) {
                             if (response.data.id >0) {
                                 $rootScope.doFlashMessage('Your user was created successfully','/profile/'+$routeParams.userId,'success');
                                getAllUsers();
                                vm.user  = angular.copy(vm.master);
                                vm.editUser = true;
                                vm.user = angular.copy(vm.auxuser);
                                } else {
                                $rootScope.doFlashMessage(response.error,'','error');
                                vm.dataLoading = false;
                            }
                            vm.saveUserFlag = false;
                            $scope.form.$setPristine();
                            NProgress.done();
                        });
                    }
                    else{
                        $rootScope.doFlashMessage('Email already exist.','','error', 5000);
                        NProgress.done();
                    }
                });
            } else {
                vm.saveUserFlag = true;
                var dateObj = new Date();var month = dateObj.getUTCMonth() + 1;var day = dateObj.getUTCDate();var year = dateObj.getUTCFullYear();
                vm.user.update = year + "-" + month + "-" + day;
                vm.dataLoading = true;
                vm.user.rol = vm.rol;
                 UserService.Update(vm.user).then(function (response) {
                     if (response.data.success =="Updated!") {
                        $rootScope.doFlashMessage('Your user was Updated successfully','','success');
                    } else {
                        $rootScope.doFlashMessage(response.error,'','error');
                        vm.dataLoading = false;
                    }
                    vm.saveUserFlag = false;
                    $scope.form.$setPristine();
                    NProgress.done();
                });
            }
        }
        
        

        $scope.changeUserStatus = function ($event,user) {
            var el = $event.currentTarget;
            if(user.status == 0) {                
                user.status = 1;
                var setClass = 'fa fa-unlock'; 
                var setParentClass = 'list-group-item ng-binding ng-scope';
            } else {                
                var setClass = 'fa fa-lock';
                var setParentClass = 'list-group-item ng-binding ng-scope  bg-active-user';
                user.status = 0;
            }
            
            UserService.Update(user).then(function (response) {
                    if (response.data.success =="Updated!") {
                        $rootScope.doFlashMessage('Status Has Been Changed','','success');
                    } else {
                        $rootScope.doFlashMessage(response.error,'','error');
                    }
                    
                    angular.element(el).find('i').attr('class',setClass);
                    console.log(angular.element(el).parent().parent().attr('class',setParentClass));
                    //.attr('class',setClass);
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

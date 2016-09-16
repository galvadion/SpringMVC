(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);        

    UserController.$inject = ['$routeParams','$timeout','$rootScope','$scope','$location', 'UserService', 'FlashService','FileUploader','SessionService','ngDialog'];
    function UserController($routeParams,$timeout,$rootScope,$scope,$location, UserService, FlashService,FileUploader,SessionService,ngDialog) {
        
        var vm = this;
        vm.linkUserAdvertiser = linkUserAdvertiser;
        vm.unlinkUserAdvertiser = unlinkUserAdvertiser;
        vm.linkUserPublisher = linkUserPublisher;
        vm.unlinkUserPublisher = unlinkUserPublisher;
        vm.rol = {};
        vm.saveUserFlag = false;

        vm.user = {};
        vm.auxuser = {};
        vm.users = {};
        vm.roladmin = false;
        vm.master ={};
        vm.edit = true;
        var currentUserRol;


        initController();
        

        //get Rol By User Id
        function getRolByUserId(id){
             UserService.RolByUserId(id).then(function (rol) {
                  if(rol.data){
                     vm.rol.id = rol.data[0].id;
                     vm.rol.name = rol.data[0].name;
                     currentUserRol = vm.rol.id;
                 }
              });
        }
        

        $scope.editUser = function(user){
            $scope.form.$setPristine();
            vm.saveBanFlag = false;
            vm.edit = false;
            vm.user  = angular.copy(vm.master);
            angular.forEach(vm.users, function(value, key){
                if(value.id == user.id){
                    getRolByUserId(user.id);
                    vm.user = value;
                    vm.user.password = '';
                    $scope.scrollTo( ".pageheader");
                }   
            });
        }
       
        $scope.resetUser = function(){
            if($rootScope.globals.currentUser){
                vm.user = angular.copy(vm.auxuser);
            }
            return false;
        } 
        $scope.scrollTo = function(element) {
            $( 'html, body').animate({
                scrollTop: $(element).offset().top
            }, 500);
        }

        $scope.newUser = function(){
            $scope.form.$setPristine();
            vm.user  = {};
            vm.user.avatarURL = window.location.protocol + "//" + window.location.host + "/assets/images/avatar.png"; 
            vm.user.id = null;
            vm.rol.name = null;
            vm.edit = false;
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

        function initController(){
            NProgress.start();
            SessionService.initService();
            $rootScope.checkCurrentRol();

                UserService.GetAll().then(function (response) {
                  if(response.data.length>1){
                     vm.users = response.data;
                     vm.roladmin = true;

                     angular.forEach(vm.users, function(value, key){
                         if(value.id == $routeParams.userId){
                            vm.user = value;
                            vm.edit = false;
                            vm.user.password = '';
                            getRolByUserId(vm.user.id);
                         }
                     });
                  }else{
                     vm.user = response.data[0];
                     vm.roladmin = false;
                     vm.edit = false;
                     getRolByUserId(vm.user.id);
                  }
                  vm.auxuser = angular.copy(vm.user);
               });                
               NProgress.done();
            }

        function getAllUsers(){
            UserService.GetAll().then(function (response) {
                  if(response.data.length>1){
                     vm.users = response.data;
                     vm.roladmin = true;
                    }
               });
        }

         var file = $scope.file = new FileUploader({
            url: 'assets/upload.php',
            queueLimit: 1,  
            removeAfterUpload: true
        });

        file.filters.push({
            name: 'imageFilter',
            fn: function(item,options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpeg|jpg|png|gif|'.indexOf(type) !== -1;
            }
        });

        file.onWhenAddingFileFailed = function(item ,filter, options) {
            
            $rootScope.doFlashMessage("Error File upload Allowed files: gif, png, jpg. Max file size 1Mb",'','error');
        };
        
        file.onAfterAddingAll = function(addedFileItems) {
            if(!angular.isUndefined($scope.file.queue[0])){
                var newFileName = "";
            var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            for( var i=0; i < 16; i++ ){
                newFileName += possible.charAt(Math.floor(Math.random() * possible.length));
            }
            //var extension = $scope.file.queue[0].file.name.split('.').pop();
            //$scope.file.queue[0].file.name = newFileName + '.' + extension;
            $scope.file.queue[0].file.nam = vm.user.id + "_" + $scope.file.queue[0].file.name;
            $scope.file.queue[0].upload();
            }
         };

        file.onSuccessItem = function(fileItem, response, status, headers) {
            if(response.answer=='completed'){
                vm.user.avatarURL = window.location.protocol + "//" + window.location.host + "/uploads/" +$scope.file.queue[0].file.name;  
            }
        };
        $scope.checkPaswords = function () {
            if(vm.user.password.length >=5 && vm.user.password == vm.user.newpassword) {
                vm.saveUserFlag = false;
            } else {
                vm.saveUserFlag = true;
            }
        }
         $scope.openConfirmEditPassword = function () {
                    ngDialog.openConfirm({
                        template: 'modalDialogCampaign',
                        className: 'ngdialog-theme-default',
                        scope: $scope
                }).then(function (value) {}, function (reason) {});
        };
        $scope.enablePasswordEdit = function() {         
            vm.saveUserFlag = true;
            angular.element('input[type=password]').removeAttr('disabled','false');
        }
        $scope.saveUser = function() {
            NProgress.start();
            if(!vm.user.id){
                UserService.CheckEmail(vm.user.email).then(function (response) {
                    if(response.data.exist != true){
                        vm.saveUserFlag = true;
                        if(!vm.user.avatarURL){
                            vm.user.avatarURL = window.location.protocol + "//" + window.location.host + "/assets/images/avatar.png";
                        }
                        vm.user.rol = vm.rol.id;
                        UserService.Create(vm.user.name,vm.user.lastname,vm.user.email,vm.user.password,vm.user.address,vm.user.avatarURL,vm.user.rol).then(function (response) {
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
                vm.user.rol = vm.rol.id;
                 UserService.Update(vm.user).then(function (response) {
                     if (response.data.success =="Updated!") {
                        if(!vm.roladmin){
                            $rootScope.globals.currentUser.avatarURL = vm.user.avatarURL;
                        }else if($rootScope.globals.currentUser.id == vm.user.id){
                            $rootScope.globals.currentUser.avatarURL = vm.user.avatarURL;
                        }
                        if(currentUserRol != vm.rol.id){
                            $rootScope.checkCurrentRol();
                        }
                        else{
                            $rootScope.doFlashMessage('Your user was Updated successfully','','success');
                        }
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
        


        //Ema
        /*Method for asociate user with advetiser or Publisher */

        function linkUserAdvertiser(uid, advid) {
            vm.dataLoading = true;
            UserService.SetAdv(uid, advid).then(function (response) {
                 if (response.data.success == true) {
                    FlashService.Success(response.data.message, true);
                    //$location.path('/');
                } else {
                    FlashService.Error(response.data.message);
                    vm.dataLoading = false;
                }
            });
        };

        function unlinkUserAdvertiser(uid, advid) {
            vm.dataLoading = true;
            UserService.UnsetAdv(uid, advid).then(function (response) {
                 if (response.data.success == true) {
                    FlashService.Success(response.data.message, true);
                    //$location.path('/');
                } else {
                    FlashService.Error(response.data.message);
                    vm.dataLoading = false;
                }
            });
        };

        function linkUserPublisher(uid, pubid) {
            vm.dataLoading = true;
            UserService.SetPub(uid, pubid).then(function (response) {
                 if (response.data.success == true) {
                    FlashService.Success(response.data.message, true);
                    //$location.path('/');
                } else {
                    FlashService.Error(response.data.message);
                    vm.dataLoading = false;
                }
            });
        };

        function unlinkUserPublisher(uid, pubid) {
            vm.dataLoading = true;
            UserService.UnsetPub(uid, pubid).then(function (response) {
                 if (response.data.success == true) {
                    FlashService.Success(response.data.message, true);
                    //$location.path('/');
                } else {
                    FlashService.Error(response.data.message);
                    vm.dataLoading = false;
                }
            });
        };
        /* EndMethod for asociate user with advetiser or Publisher */
        
    }

})();

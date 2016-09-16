﻿(function () {
    'use strict';


    /***App Module Definition***/

    angular
        .module('app', ['ngRoute', 'ngCookies','jkuri.datepicker','angularFileUpload','angular-confirm','ui.bootstrap','ngDialog','ngAnimate'/*,'simpleGrid','googlechart','datatables','datatables.buttons'*/])
        .config(config)
        .run(run)
        .run(['$location', '$rootScope', function($location, $rootScope) {
            $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
                if (current.hasOwnProperty('$$route')) {
                    //console.log(current.$$route.title);
                    $rootScope.title = current.$$route.title;
                }
            });
        }]);



    /***Routes Definitions***/

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/home', {
                controller: 'HomeController',
                templateUrl: 'pages/home/home.view.html',
                title: 'Demo - Dashboard',
                /*resolve:{
                    'AllAdvertiserData':
                        ['$http', function($http) { return $http.get('/adv/adv'); }],
                    'EmptyData':
                        ['$http', function($http) { return ''; }],
                },*/
                controllerAs: 'vm'            
             })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'pages/login/login.html',
                title: 'Demo - Login',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'pages/register/register.html',
                title: 'Demo - Register',
                controllerAs: 'vm'
            })

            .when('/forgot', {
                controller: 'ForgotController',
                templateUrl: 'pages/forgot/forgot.html',
                title: 'Demo - Forgot Password',
                controllerAs: 'vm'
            })

            .when('/user', {
                controller: 'UserController',
                templateUrl: 'pages/user/user.html',
                title: 'Demo - Profile',
                controllerAs: 'vm'
            })

            .when('/profile/:userId', {
                controller: 'UserController',
                templateUrl: 'pages/user/user.html',
                title: 'Demo - Profile',
                controllerAs: 'vm'
            })

            .when('/terms', {
                templateUrl: 'pages/terms/terms.html',
                title: 'Demo - Terms & Conditions'
            })

            .otherwise({ redirectTo: '/login' });
    }



    /***Run Function ($routeScope Functions)***/

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http','FlashService', 'UserService', '$window', 'AuthenticationService'];
    function run($rootScope, $location, $cookieStore, $http,FlashService, UserService, $window, AuthenticationService) {
        
        //Session persistance: keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        $rootScope.loggedRol;

        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        //Session security: redirect to login page if not logged in and trying to access a restricted page
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var restrictedPage = $location.path();
            var loggedIn = $rootScope.globals.currentUser;
            if(restrictedPage.indexOf('/login') == -1 && restrictedPage.indexOf('/register') == -1 && restrictedPage.indexOf('/forgot') == -1 && restrictedPage.indexOf('/terms') == -1 && !loggedIn) {
                $location.path('/login');
            }
        });

        //Flashmessage function
        $rootScope.doFlashMessage = function(msg,location,type,timeToSwitch,reload) {
            if(angular.isUndefined(timeToSwitch)) {
                timeToSwitch = 3000;
            }

            if(!angular.isUndefined(reload)) {
                setTimeout(function(){
                        $window.location.reload();
                },timeToSwitch);
            }
            else{
                if(type!='error') {
                    FlashService.Success(msg, true);
                } else {
                    FlashService.Error(msg, true);
                }

                setTimeout(function(){
                    angular.element('#flash-service').fadeOut();
                    FlashService.clearFlashMessage();                    
                },timeToSwitch);

                
                if(location!='') {
                    $location.path(location);
                }
            }

        };

        $rootScope.checkCurrentRol = function() {
            $rootScope.getLoggedRol();
            var id = $rootScope.globals.currentUser.id;
            UserService.RolByUserId(id).then(function (rol) {
                //console.log($rootScope.loggedRol+'||'+rol.data[0].id);
                if(rol.data){
                    if(rol.data[0].id != $rootScope.loggedRol){
                        $rootScope.doFlashMessage('Something changed, please relog.','','error', 4000, true);
                        AuthenticationService.ClearCredentials();
                    }
                }
            });
        };

        $rootScope.getLoggedRol = function() {
            var c = document.cookie.split('; ');
            var cookies = {};

            for(var i=c.length-1; i>=0; i--){
               var C = c[i].split('=');
               cookies[C[0]] = C[1];
            }

            if(!angular.isUndefined(cookies['globals'])){
                var cookieContent = JSON.parse(decodeURIComponent(cookies['globals']));                      
                //console.log(cookieContent.currentUser.rol);
                $rootScope.loggedRol = cookieContent.currentUser.rol;
            } else {
                setTimeout(function()  { $rootScope.getLoggedRol();},1000) ;
            } 
        };

    }

})();
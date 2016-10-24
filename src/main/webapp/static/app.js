(function () {
    'use strict';


    /***App Module Definition***/
    angular
        .module('app', ['ngRoute', 'ngCookies','jkuri.datepicker','angularFileUpload','angular-confirm','ui.bootstrap','ngDialog','ngAnimate','datatables', 'datatables.buttons'])
        .config(config)
        .run(run)
        .run(['$location', '$rootScope', function($location, $rootScope) {
            $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
                if (current.hasOwnProperty('$$route')) {
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
                templateUrl: 'home/home',
                title: 'Demo - Inicio',
                /*resolve:{
                    'AllAdvertiserData':
                        ['$http', function($http) { return $http.get('/adv/adv'); }],
                    'EmptyData':
                        ['$http', function($http) { return ''; }],
                },*/
                controllerAs: 'vm'            
             })
             
             .when('/', {
                controller: 'HomeController',
                templateUrl: 'home/home',
                title: 'Demo - Inicio',
                controllerAs: 'vm'            
             })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login',
                title: 'Demo - Ingresar',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'login/register',
                title: 'Demo - Registrarse',
                controllerAs: 'vm'
            })

            .when('/forgot', {
                controller: 'ForgotController',
                templateUrl: 'login/forgot',
                title: 'Demo - Recuperar Contraseña',
                controllerAs: 'vm'
            })
            
            .when('/terms', {
                templateUrl: 'login/terms',
                title: 'Demo - Terminos & Condiciones'
            })

            .when('/profile', {
                controller: 'UserController',
                templateUrl: 'user/user',
                title: 'Demo - Perfil',
                controllerAs: 'vm'
            })

            .when('/brand', {
                controller: 'BrandController',
                templateUrl: 'brand',
                title: 'Demo - Marcas',
                controllerAs: 'vm'
            })
            
            .when('/model', {
                controller: 'ModelController',
                templateUrl: 'model',
                title: 'Demo - Modelos',
                controllerAs: 'vm'
            })

            .when('/model/create', {
                controller: 'ModelController',
                templateUrl: 'model/create',
                title: 'Demo - Crear Modelo',
                controllerAs: 'vm'
            })
            
            .when('/vehicle', {
                controller: 'VehicleController',
                templateUrl: 'vehicle',
                title: 'Demo - Vehiculos',
                controllerAs: 'vm'
            })

            .when('/vehicle/create', {
                controller: 'VehicleController',
                templateUrl: 'vehicle/create',
                title: 'Demo - Crear Vehiculo',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/' });
    }


    /***Run Function ($routeScope Functions)***/

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http','FlashService', 'UserService', '$window', 'AuthenticationService'];
    function run($rootScope, $location, $cookieStore, $http,FlashService, UserService, $window, AuthenticationService) {

    	$rootScope.roladmin = false;
    	$rootScope.rolclient = false;
    	
        //Session persistance: keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};

        if ($rootScope.globals.currentUser) {

        	if($rootScope.globals.currentUser.rol == "Admin"){
        		$rootScope.roladmin = true;
        	}
        	else if($rootScope.globals.currentUser.rol == "Client"){
        		$rootScope.rolclient = true;
        	}
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        //Session security: redirect to login page if not logged in and trying to access a restricted page
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var restrictedPage = $location.path();
            var loggedIn = $rootScope.globals.currentUser;
            if(restrictedPage.indexOf('/profile') == 0 && !loggedIn) {
                //$location.path('/login');
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
                    $('#flash-service').fadeOut();
                    FlashService.clearFlashMessage();                    
                },timeToSwitch);

                
                if(location!='') {
                    $location.path(location);
                }
            }

        };
        
        $rootScope.getClass = function (path) {
        	var currentLocation = $location.path().split('/',2);
        	if(currentLocation[1] == ''){
        		if(path == 'home'){
        			return 'custom-active';
        		} 
        	}
        	else if(currentLocation[1] == path){
        		return 'custom-active';
        	}
        	else{
        		return '';
        	}
        }
        
        $rootScope.logOut = function () {
        	AuthenticationService.ClearCredentials();
        	$rootScope.roladmin = false;
        	$location.path('/home');
        }

    }

})();
﻿(function () {
    'use strict';


    /***App Module Definition***/
    angular
        .module('app', ['ngFileUpload','ngRoute', 'ngCookies','angular-confirm','ui.bootstrap','ngDialog','ngAnimate','datatables', 'datatables.buttons','uiGmapgoogle-maps', 'checklist-model', 'isteven-multi-select'])
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
                title: 'Rent-UY - Inicio',
                controllerAs: 'vm'            
             })
             
             .when('/', {
                controller: 'HomeController',
                templateUrl: 'home/home',
                title: 'Rent-UY - Inicio',
                controllerAs: 'vm'            
             })
             
             .when('/about', {
                controller: 'HomeController',
                templateUrl: 'home/about',
                title: 'Rent-UY - Nosotros',
                controllerAs: 'vm'            
             })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login',
                title: 'Rent-UY - Ingresar',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'login/register',
                title: 'Rent-UY - Registrarse',
                controllerAs: 'vm'
            })

            .when('/forgot', {
                controller: 'ForgotController',
                templateUrl: 'login/forgot',
                title: 'Rent-UY - Recuperar Contraseña',
                controllerAs: 'vm'
            })
            
            .when('/terms', {
                templateUrl: 'login/terms',
                title: 'Rent-UY - Términos & Condiciones'
            })

            .when('/profile', {
                controller: 'UserController',
                templateUrl: 'user/profile',
                title: 'Rent-UY - Perfil',
                controllerAs: 'vm'
            })
            
            .when('/employee', {
                controller: 'UserController',
                templateUrl: 'employee',
                title: 'Rent-UY - Empleados',
                controllerAs: 'vm'
            })
            
            .when('/employee/create', {
                controller: 'UserController',
                templateUrl: 'employee/create',
                title: 'Rent-UY - Crear Empleado',
                controllerAs: 'vm'
            })
            
            .when('/employee/edit/:id', {
                controller: 'UserController',
                templateUrl: 'employee/edit',
                title: 'Rent-UY - Editar Empleado',
                controllerAs: 'vm'
            })
            
            .when('/client', {
                controller: 'UserController',
                templateUrl: 'client',
                title: 'Rent-UY - Clientes',
                controllerAs: 'vm'
            })
            
            .when('/client/create', {
                controller: 'UserController',
                templateUrl: 'client/create',
                title: 'Rent-UY - Crear Cliente',
                controllerAs: 'vm'
            })
            
            .when('/client/confirm/:id', {
                controller: 'UserController',
                templateUrl: 'client/confirm',
                title: 'Rent-UY - Confirmar correo',
                controllerAs: 'vm'
            })
            
            .when('/client/edit/:id', {
                controller: 'UserController',
                templateUrl: 'client/edit',
                title: 'Rent-UY - Editar Cliente',
                controllerAs: 'vm'
            })

            .when('/brand', {
                controller: 'BrandController',
                templateUrl: 'brand',
                title: 'Rent-UY - Marcas',
                controllerAs: 'vm'
            })
            
            .when('/model', {
                controller: 'ModelController',
                templateUrl: 'model',
                title: 'Rent-UY - Modelos',
                controllerAs: 'vm'
            })
            
            .when('/model/properties/:id', {
                controller: 'ModelController',
                templateUrl: 'model/properties',
                title: 'Rent-UY - Propiedades de Modelo',
                controllerAs: 'vm'
            })

            .when('/model/create', {
                controller: 'ModelController',
                templateUrl: 'model/create',
                title: 'Rent-UY - Crear Modelo',
                controllerAs: 'vm'
            })
            
            .when('/model/edit/:id', {
                controller: 'ModelController',
                templateUrl: 'model/edit',
                title: 'Rent-UY - Editar Modelo',
                controllerAs: 'vm'
            })
            
            .when('/vehicle', {
                controller: 'VehicleController',
                templateUrl: 'vehicle',
                title: 'Rent-UY - Vehículos',
                controllerAs: 'vm'
            })

            .when('/vehicle/create', {
                controller: 'VehicleController',
                templateUrl: 'vehicle/create',
                title: 'Rent-UY - Crear Vehículo',
                controllerAs: 'vm'
            })
            
            .when('/vehicle/edit/:id', {
                controller: 'VehicleController',
                templateUrl: 'vehicle/edit',
                title: 'Rent-UY - Editar Vehículo',
                controllerAs: 'vm'
            })
            
            .when('/vehicle/details/:id', {
                controller: 'VehicleController',
                templateUrl: 'vehicle/details',
                title: 'Rent-UY - Detalles de Vehículo',
                controllerAs: 'vm'
            })
            
            .when('/branchoffice', {
                controller: 'BranchofficeController',
                templateUrl: 'branchoffice',
                title: 'Rent-UY - Sucursales',
                controllerAs: 'vm'
            })

            .when('/branchoffice/create', {
                controller: 'BranchofficeController',
                templateUrl: 'branchoffice/create',
                title: 'Rent-UY - Crear Sucursal',
                controllerAs: 'vm'
            })
            
            .when('/branchoffice/edit/:id', {
                controller: 'BranchofficeController',
                templateUrl: 'branchoffice/edit',
                title: 'Rent-UY - Editar Sucursal',
                controllerAs: 'vm'
            })
            
            .when('/tariff', {
                controller: 'TariffController',
                templateUrl: 'tariff',
                title: 'Rent-UY - Tarifas',
                controllerAs: 'vm'
            })
            
            .when('/promotion', {
                controller: 'PromotionController',
                templateUrl: 'promotion',
                title: 'Rent-UY - Promociones',
                controllerAs: 'vm'
            })
            
            .when('/promotion/create', {
                controller: 'PromotionController',
                templateUrl: 'promotion/create',
                title: 'Rent-UY - Promociones',
                controllerAs: 'vm'
            })
            
            .when('/rent', {
                controller: 'RentController',
                templateUrl: 'rent',
                title: 'Rent-UY - Alquileres',
                controllerAs: 'vm'
            })

            .when('/rent/confirm/:id', {
                controller: 'RentController',
                templateUrl: 'rent/confirm',
                title: 'Rent-UY - Alquiler',
                controllerAs: 'vm'
            })
            
            .when('/rent/return/:id', {
                controller: 'RentController',
                templateUrl: 'rent/return',
                title: 'Rent-UY - Alquiler',
                controllerAs: 'vm'
            })
            
            .when('/rent/details/:id', {
                controller: 'RentController',
                templateUrl: 'rent/details',
                title: 'Rent-UY - Detalles de alquiler',
                controllerAs: 'vm'
            })
            
            .when('/booked', {
                controller: 'BookedController',
                templateUrl: 'booked',
                title: 'Rent-UY - Reservas',
                controllerAs: 'vm'
            })

            .when('/booked/create', {
                controller: 'BookedController',
                templateUrl: 'booked/create',
                title: 'Rent-UY - Crear Reserva',
                controllerAs: 'vm'
            })
            
            .when('/booked/edit/:id', {
                controller: 'BookedController',
                templateUrl: 'booked/edit',
                title: 'Rent-UY - Editar Reserva',
                controllerAs: 'vm'
            })
            
            .when('/search', {
                controller: 'BookedController',
                templateUrl: 'booked/search',
                title: 'Rent-UY - Buscar',
                controllerAs: 'vm'
            })
            
            .when('/booked/details/:id', {
                controller: 'BookedController',
                templateUrl: 'booked/details',
                title: 'Rent-UY - Detalles de Reserva',
                controllerAs: 'vm'
            })
            
            .when('/search/origin=:origin&destination=:destination&from=:from&to=:to', {
                controller: 'BookedController',
                templateUrl: 'booked/search',
                title: 'Rent-UY - Buscar',
                controllerAs: 'vm'
            })
            
            .when('/payment/booking', {
            	controller: 'PaymentController',
            	templateUrl:'payment/booking',
            	title: 'Rent-UY - Reserva',
            	controllerAs: 'vm'
            })
            .when('/report', {
                controller: 'ReportController',
                templateUrl: 'report',
                title: 'Rent-UY - Alquileres',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/' });
    }


    /***Run Function ($routeScope Functions)***/

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http','FlashService', 'UserService', '$window', 'AuthenticationService'];
    function run($rootScope, $location, $cookieStore, $http,FlashService, UserService, $window, AuthenticationService) {

    	$rootScope.roladmin = false;
        $rootScope.rolcemployee = false;
    	$rootScope.rolclient = false;
    	
    	$rootScope.modelId;
    	$rootScope.dateInitial;
    	$rootScope.dateEnding;
    	$rootScope.officeInitial;
    	$rootScope.officeEnding;
    	
        //Session persistance: keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};

        if ($rootScope.globals.currentUser) {

        	if($rootScope.globals.currentUser.rol == "Admin"){
        		$rootScope.roladmin = true;
        	}
            else if($rootScope.globals.currentUser.rol == "Employee"){
                $rootScope.rolemployee = true;
            }
        	else if($rootScope.globals.currentUser.rol == "Client"){
        		$rootScope.rolclient = true;
        	}
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        //Session security: redirect to login page if not logged in and trying to access a restricted page
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var currentPage = $location.path().split('/',3);
            if($rootScope.globals.currentUser){
	            var loggedRol = $rootScope.globals.currentUser.rol;
	            
	            if(loggedRol == "Admin"){
	            	
	            }
	            if(loggedRol == "Employee"){
	            	
	            }
	            if(loggedRol == "Client"){
	            	
	            }
            }
            else{
            	//Usuario no logeado
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
            $rootScope.rolemployee = false;
        	$location.path('/home');
        	setTimeout(function(){
                $window.location.reload();
        	},600);
        }
        
        $rootScope.chat = false;

        $rootScope.initChat = function () {
        	var userName = '';
        	if ($rootScope.globals.currentUser) {        	
            	if($rootScope.globals.currentUser.rol == "Admin"){//            		
            		userName ='Emp_'+$rootScope.globals.currentUser.name;
            	}
            	else if($rootScope.globals.currentUser.rol == "Client"){//            		
            		userName ='Cli_'+$rootScope.globals.currentUser.name;
            	}else if($rootScope.globals.currentUser.rol == "Employee"){
            		userName ='Emp_'+$rootScope.globals.currentUser.name;
            	}
        	}else {
	            var possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	            for( var i=0; i < 4; i++ ){
	                userName += possible.charAt(Math.floor(Math.random() * possible.length));
	            }
	            userName='Unk_'+userName;
        	}
    		$.cometChat.onLoad({memberListContainerID:'members'});
    		join(userName);
            $rootScope.chat = true;
        }

    }

})();
    	
    	
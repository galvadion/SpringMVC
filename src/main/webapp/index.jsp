<!DOCTYPE html>
<html ng-app="app">
    <head>
        <!--Meta-->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="description" content="">
        
        <!--Page Data-->
        <link rel="icon" type="image/png" href="static/images/favicon.png">
        <title ng-bind="title">Angular Demo</title>

        <!--Styles-->
        <link rel="stylesheet" type="text/css" href="static/css/vendor/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="static/css/ngDatepicker.css">
        <link rel="stylesheet" type="text/css" href="static/css/nprogress.css">
        <link rel="stylesheet" type="text/css" href="static/css/ngDialog.css">
        <link rel="stylesheet" type="text/css" href="static/css/ngDialog-theme-default.css">
        <link rel="stylesheet" type="text/css" href="static/css/ngDialog-theme-plain.css">
        <link rel="stylesheet" type="text/css" href="static/css/ngDialog-custom-width.css">
        <link rel="stylesheet" type="text/css" href="static/css/ui-multiselect.css">
        <link rel="stylesheet" type="text/css" href="static/css/autocomplete.css">
        <link rel="stylesheet" type="text/css" href="static/css/main.css">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,400italic">
        <link rel="stylesheet" type="text/css" href="static/fonts/font-awesome/css/font-awesome.min.css">
        

    </head>
    <body>
    	<div id="wrap" class="animsition hz-menu">
        	<div ng-class="{ 'alert': flash, 'alert-success': flash.type === 'success', 'alert-lightred ': flash.type === 'error' }" ng-if="flash" ng-bind="flash.message" id="flash-service"></div>
        </div>
        
        <!--Extensions-->
        <script src="//code.angularjs.org/1.5.5/angular.js"></script>
        <script src="//code.angularjs.org/1.5.5/angular-route.js"></script>
        <script src="//code.angularjs.org/1.5.5/angular-cookies.js"></script>
        <script src="//code.angularjs.org/1.5.5/angular-aria.js"></script>
        <script src="//code.angularjs.org/1.5.5/angular-animate.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <!--script type="text/javascript" src="static/js/main.js"></script-->
        
        <script type="text/javascript" src="static/app.js"></script>

        <script type="text/javascript" src="static/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="static/js/ui-bootstrap-tpls.js"></script>
        <script type="text/javascript" src="static/js/angular-ui-router.js"></script>
        <script type="text/javascript" src="static/js/angular-file-upload.js"></script>
        <script type="text/javascript" src="static/js/ngDatepicker.min.js"></script>
        <script type="text/javascript" src="static/js/angular-confirm.js"></script>
        <script type="text/javascript" src="static/js/nprogress.js"></script>
        <script type="text/javascript" src="static/js/ngDialog.js"></script>
        <script type="text/javascript" src="static/js/ui-multiselect.js"></script>



        <!--Services-->
        <script src="static/services/authentication.service.js"></script>
        <script src="static/services/flash.service.js"></script>
        <script src="static/services/session.service.js"></script>
        <script src="static/services/user.service.js"></script>
        <script src="static/services/brand.service.js"></script>
        <script src="static/services/model.service.js"></script>
        <script src="static/services/vehicle.service.js"></script>

        <!--Controllers-->
        <script src="static/controllers/forgot/forgot.controller.js"></script>
        <script src="static/controllers/login/login.controller.js"></script>
        <script src="static/controllers/register/register.controller.js"></script>
        <script src="static/controllers/user/user.controller.js"></script>
        <script src="static/controllers/home/home.controller.js"></script>
        <script src="static/controllers/brand/brand.controller.js"></script>
        <script src="static/controllers/model/model.controller.js"></script>
        <script src="static/controllers/vehicle/vehicle.controller.js"></script>

        <div id="wrap">
          <div ng-view></div>
        </div>

    </body>
</html>
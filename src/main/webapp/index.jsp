<!DOCTYPE html>
<html ng-app="app">
    <head>
        <!--Meta-->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="description" content="">
        
        <!--Page Data-->
        <link rel="icon" type="image/png" href="static/images/favicon.png">
        <title ng-bind="title">Rent-UY</title>

        <!--Styles-->
        <link rel="stylesheet" type="text/css" href="static/css/vendor/bootstrap.min.css">
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
        <link rel="stylesheet" type="text/css" href="static/js/dista/plugins/bootstrap/datatables.bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="static/js/dista/css/angular-datatables.css">
        <link rel="stylesheet" type="text/css" href="static/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" type="text/css" href="static/css/isteven-multi-select.css">
        <link rel="stylesheet" type="text/css" href="static/js/chat/comet.chat.css">
        <link rel="stylesheet" type="text/css" href="static/css/bootstrap-switch.min.css">
        <link rel="stylesheet" type="text/css" href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" >

    </head>
    <body>
    	<div id="wrap" class="animsition hz-menu">
        	<div ng-class="{ 'alert': flash, 'alert-success': flash.type === 'success', 'alert-lightred ': flash.type === 'error' }" ng-if="flash" ng-bind="flash.message" id="flash-service"></div>
        </div>
        
        <!--Core-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="//code.angularjs.org/1.4.8/angular.js"></script>
        <script src="//code.angularjs.org/1.4.8/angular-route.js"></script>
        <script src="//code.angularjs.org/1.4.8/angular-cookies.js"></script>
        <script src="//code.angularjs.org/1.4.8/angular-animate.js"></script>
        <script type="text/javascript" src="static/app.js"></script>


		<!--Extensions-->
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="static/js/bootstrap-datetimepicker.es.js"></script>
        <script type="text/javascript" src="static/js/ui-bootstrap-tpls.js"></script>
        <script type="text/javascript" src="static/js/angular-ui-router.js"></script>
        <script type="text/javascript" src="static/js/vendor/ng-file-upload/ng-file-upload-shim.min.js"></script>
        <script type="text/javascript" src="static/js/vendor/ng-file-upload/ng-file-upload.min.js"></script>
        <script type="text/javascript" src="static/js/angular-confirm.js"></script>
        <script type="text/javascript" src="static/js/nprogress.js"></script>
        <script type="text/javascript" src="static/js/ngDialog.js"></script>
        <script type="text/javascript" src="static/js/ui-multiselect.js"></script>
		<script type="text/javascript" src="static/js/moment.min.js"> </script>
		<script type="text/javascript" src="static/js/bootstrap-switch.min.js"></script>
		<script type="text/javascript" src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
		<script type="text/javascript" src="static/js/isteven-multi-select.js"> </script>
		
		<!--Maps-->
		<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCToOBKLzgMq3u02g7OO_v-txL_LnfEO_g&callback=initMap" type="text/javascript"></script>
		<script type="text/javascript" src="static/js/maps/lodash.core.js"></script>
		<script type="text/javascript" src="static/js/maps/angular-simple-logger.js"></script>
		<script type="text/javascript" src="static/js/maps/angular-google-maps.min.js"></script>
		
		<!-- Data Tablbes -->
		<script type="text/javascript" src="static/js/vendor/datatables/media/js/jquery.dataTables.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-colreorder/js/dataTables.colReorder.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-colvis/js/dataTables.colVis.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-tabletools/js/dataTables.tableTools.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-responsive/js/dataTables.responsive.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-scroller/js/dataTables.scroller.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-columnfilter/js/dataTables.columnFilter.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-light-columnfilter/dista/dataTables.lightColumnFilter.min.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-fixedcolumns/js/dataTables.fixedColumns.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-fixedheader/js/dataTables.fixedHeader.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-buttons/js/dataTables.buttons.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-buttons/js/buttons.colVis.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-buttons/js/buttons.flash.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-buttons/js/buttons.html5.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-buttons/js/buttons.print.js"></script>
		<script type="text/javascript" src="static/js/vendor/datatables-select/js/dataTables.select.js"></script>
		<script type="text/javascript" src="static/js/src/angular-datatables.js"></script>
		<script type="text/javascript" src="static/js/src/angular-datatables.util.js"></script>
		<script type="text/javascript" src="static/js/src/angular-datatables.options.js"></script>
		<script type="text/javascript" src="static/js/src/angular-datatables.instances.js"></script>
		<script type="text/javascript" src="static/js/src/angular-datatables.factory.js"></script>
		<script type="text/javascript" src="static/js/src/angular-datatables.renderer.js"></script>
		<script type="text/javascript" src="static/js/src/angular-datatables.directive.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/bootstrap/angular-datatables.bootstrap.options.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/bootstrap/angular-datatables.bootstrap.colvis.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/bootstrap/angular-datatables.bootstrap.tabletools.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/bootstrap/angular-datatables.bootstrap.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/colvis/angular-datatables.colvis.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/colreorder/angular-datatables.colreorder.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/tabletools/angular-datatables.tabletools.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/scroller/angular-datatables.scroller.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/columnfilter/angular-datatables.columnfilter.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/light-columnfilter/angular-datatables.light-columnfilter.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/fixedcolumns/angular-datatables.fixedcolumns.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/fixedheader/angular-datatables.fixedheader.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/buttons/angular-datatables.buttons.js"></script>
		<script type="text/javascript" src="static/js/src/plugins/select/angular-datatables.select.js"></script>
		<script type="text/javascript" src="static/js/angular-checklist-model.js"></script>

		<!-- Chat -->
		<script type="text/javascript" src="static/js/chat/org/cometd.js"></script>
		<script type="text/javascript" src="static/js/chat/org/cometd/AckExtension.js"></script>
		<script type="text/javascript" src="static/js/chat/org/cometd/ReloadExtension.js"></script>
		 
		<script type="text/javascript" src="static/js/chat/jquery/jquery.cookie.js"></script>
		<script type="text/javascript" src="static/js/chat/jquery/jquery.cometd.js"></script>
		<script type="text/javascript" src="static/js/chat/jquery/jquery.cometd-reload.js"></script>
		<script type="text/javascript" src="static/js/chat/chat.window.js"></script>
		<script type="text/javascript" src="static/js/chat/comet.chat.js"></script>

        <!--Services-->
        <script src="static/services/authentication.service.js"></script>
        <script src="static/services/flash.service.js"></script>
        <script src="static/services/session.service.js"></script>
        <script src="static/services/user.service.js"></script>
        <script src="static/services/brand.service.js"></script>
        <script src="static/services/model.service.js"></script>
        <script src="static/services/vehicle.service.js"></script>
        <script src="static/services/booked.service.js"></script>
        <script src="static/services/branchoffice.service.js"></script>
        <script src="static/services/promotion.service.js"></script>
        <script src="static/services/rent.service.js"></script>
        <script src="static/services/tariff.service.js"></script>
        <script src="static/services/report.service.js"></script>
        <script src="static/services/payment.service.js"></script>


        <!--Controllers-->
        <script src="static/controllers/forgot/forgot.controller.js"></script>
        <script src="static/controllers/login/login.controller.js"></script>
        <script src="static/controllers/register/register.controller.js"></script>
        <script src="static/controllers/user/user.controller.js"></script>
        <script src="static/controllers/home/home.controller.js"></script>
        <script src="static/controllers/brand/brand.controller.js"></script>
        <script src="static/controllers/model/model.controller.js"></script>
        <script src="static/controllers/vehicle/vehicle.controller.js"></script>
        <script src="static/controllers/booked/booked.controller.js"></script>
        <script src="static/controllers/branchoffice/branchoffice.controller.js"></script>
        <script src="static/controllers/promotion/promotion.controller.js"></script>
        <script src="static/controllers/rent/rent.controller.js"></script>
        <script src="static/controllers/tariff/tariff.controller.js"></script>
        <script src="static/controllers/report/report.controller.js"></script>
        <script src="static/controllers/payment/payment.controller.js"></script>
        
        <script src="static/models/shoppingCart.js"></script>
        

        <div id="wrap" class="wrap">
          <div ng-view></div>
        </div>

    </body>
</html>

<script type="text/javascript">
    
    var chatWindowArray = [];
    
    var config = {
        contextPath: '${pageContext.request.contextPath}'
    };
	
	function getChatWindowByUserPair(loginUserName, peerUserName) {
		
		var chatWindow;
		
		for(var i = 0; i < chatWindowArray.length; i++) {
			var windowInfo = chatWindowArray[i];
			if (windowInfo.loginUserName == loginUserName && windowInfo.peerUserName == peerUserName) {
				chatWindow =  windowInfo.windowObj;
			}
		}
		
		return chatWindow;
	}
	
	function createWindow(loginUserName, peerUserName) {
		
		var chatWindow = getChatWindowByUserPair(loginUserName, peerUserName);
		
		if (chatWindow == null) { //Not chat window created before for this user pair.
			chatWindow = new ChatWindow(); //Create new chat window.
			chatWindow.initWindow({
				loginUserName:loginUserName, 
				peerUserName:peerUserName,
				windowArray:chatWindowArray});
			
			//collect all chat windows opended so far.
			var chatWindowInfo = { peerUserName:peerUserName, 
					               loginUserName:loginUserName,
					               windowObj:chatWindow 
					             };
			
			chatWindowArray.push(chatWindowInfo);
    	}
		
		chatWindow.show();
		
		return chatWindow;
	}
	function join(userName){
		$.cometChat.join(userName);
	}
</script>
<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div ng-if="vm.location[2] == 'create'" class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Sucursales </strong> Crear</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Dashboard</a></li>
                <li><a href="#/branchoffice">Sucursales</a></li>
                <li class="breadcrumb-active">Nueva Sucursal</li>
            </ol>
        </div>
        
        <div ng-if="vm.location[2] == 'edit'" class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Sucursales </strong> Editar</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Dashboard</a></li>
                <li><a href="#/branchoffice">Sucursales</a></li>
                <li class="breadcrumb-active">Editar Sucursal</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile body -->
                    <div class="tile-body">
            			
            			<form  form-on-change="checkFields()" name='form' class="" role="form" ng-submit="saveBranchOffice()">

                        <div class="form-group" ng-class="{ 'has-error': form.name.$dirty && form.name.$error.required }">
                            <label for="name" class="control-label">* Nombre</label>
                            <input type="text" name="name" id="name" class="form-control" id="name" ng-model="vm.branchoffice.name" placeholder="Nombre" required>
                            <span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Nombre es requerido</span>
                        </div>

						<div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.city.$dirty && form.city.$error.required }">
	                            <label for="city" class="control-label">* Ciudad</label>
                            	<input type="text" name="city" id="city" class="form-control" ng-model="vm.branchoffice.city" placeholder="Ciudad" required>
                            	<span ng-show="form.city.$dirty && form.city.$error.required" class="help-block">Ciudad es requerida</span>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.address.$dirty && form.address.$error.required }">
	                            <label for="address" class="control-label">* Dirección</label>
                            	<input type="text" name="address" id="address" class="form-control" ng-model="vm.branchoffice.address" placeholder="Dirección" required>
                            	<span ng-show="form.address.$dirty && form.address.$error.required" class="help-block">Dirección es requerida</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.apertureHour.$dirty && form.apertureHour.$error.required }">
	                            <label for="apertureHour" class="control-label">* Hora de apeertura</label>
                            	<input type="text" name="apertureHour" id="apertureHour" class="form-control" ng-model="vm.branchoffice.apertureHour" placeholder="hh:mm" required>
                            	<span ng-show="form.apertureHour.$dirty && form.apertureHour.$error.required" class="help-block">Apertura es requerido</span>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.closingHour.$dirty && form.closingHour.$error.required }">
	                            <label for="closingHour" class="control-label">* Hora de cierre</label>
                            	<input type="text" name="closingHour" id="closingHour" class="form-control" ng-model="vm.branchoffice.closingHour" placeholder="hh:mm" required>
                            	<span ng-show="form.closingHour.$dirty && form.closingHour.$error.required" class="help-block">Cierre es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                    	<div class="form-group col-sm-12" class="form-group" ng-class="{ 'has-error': !vm.branchoffice.location}">
			                    <label for="location" class="control-label">* Localización</label>
                            	
                            	<ui-gmap-google-map center="map.center" zoom="map.zoom" draggable="true" options="options" name="location" id="location">
			                    	
								</ui-gmap-google-map>
	                    	
	                    		<span ng-show="!vm.branchoffice.location" class="help-block">Localización es requerida</span>
	                    	</div>
	                    </div>
	            			
	            		</div>

                        <!-- Buttons -->

                        <div class="form-group text-right">
                            <a href="#/branchoffice" class="btn btn-lightred">Cancelar</a>
                            <button type="submit" id="submit" ng-disabled="form.$invalid" class="btn  btn-orange">Guardar</button>
                        </div>

                    </form>
            	
            	</section>
            </div>
		<!-- /row -->
        </div>

    </div>

</section>
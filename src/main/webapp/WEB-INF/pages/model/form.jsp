<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Modelos </strong> Crear</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li><a href="#/model">Modelos</a></li>
                <li class="breadcrumb-active">Nuevo Modelo</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile body -->
                    <div class="tile-body">
            			
            			<form  form-on-change="checkFields()" name='form' class="" role="form" ng-submit="saveModel()" enctype="multipart/form-data">

                        <div class="form-group" ng-class="{ 'has-error': form.name.$dirty && form.name.$error.required }">
                            <label for="name" class="control-label">* Nombre</label>
                            <input type="text" name="name" id="name" class="form-control" id="name" ng-model="vm.requestModel.name" placeholder="Nombre" required>
                            <span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Nombre es requerido</span>
                        </div>

						<div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.brand.$dirty && form.brand.$error.required }">
	                            <label for="brand" class="control-label">* Marca</label>
	                            <select ng-model="vm.requestModel.brand.id" ng-options="item.id as item.name for item in vm.allBrands" name="brand" id="brand" class="form-control" required>
									<option value=""> Seleccione una marca</option> 
									<!-- <option ng-repeat="(key, value) in vm.allBrands" value="{{value.id}}">{{value.name}}</option>  -->
								</select>
								<span ng-show="form.brand.$dirty && form.brand.$error.required" class="help-block">Marca es requerida</span>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.year.$dirty && form.year.$error.required }">
	                            <label for="year" class="control-label">* Año</label>
	                            <select ng-model="vm.requestModel.year" ng-options="item.year as item.year for item in vm.lastYears"  name="year" id="year" class="form-control" required>
									<option value=""> Seleccione el año</option> 
								<!--	<option ng-repeat="(key, value) in vm.lastYears" value="{{value}}">{{value}}</option> -->
								</select>
								<span ng-show="form.year.$dirty && form.year.$error.required" class="help-block">Año es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.category.$dirty && form.category.$error.required }">
	                            <label for="category" class="control-label">* Categoria</label>
	                            <select ng-model="vm.requestModel.category.id" name="category" ng-options="item.id as item.name for item in vm.category" id="category" class="form-control" required>
									<option value=""> Seleccione una categoria</option> 
								<!-- 	<option value="1">S</option>
									<option value="2">A</option>
									<option value="3">B</option>
									<option value="4">C</option>
									<option value="5">D</option>  -->
								</select>
								<span ng-show="form.category.$dirty && form.category.$error.required" class="help-block">Marca es requerida</span>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.passangers.$dirty && form.passangers.$error.required }">
	                            <label for="passangers" class="control-label">* Cantidad de pasajeros</label>
	                            <input type="number" min="2" ng-model="vm.requestModel.passangers" name="passangers" id="passangers" class="form-control" placeholder="2" required>
								<span ng-show="form.passangers.$dirty && form.passangers.$error.required" class="help-block">Cantidad de pasajeros es requerido</span>
	                        </div>
	                    </div>

                      	<div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.cylinders.$dirty && form.cylinders.$error.required }">
	                            <label for="cylinders" class="control-label">* Cilindrada</label>
	                            <select ng-model="vm.requestModel.cylinders" name="cylinders" id="cylinders" ng-options="item.value as item.value for item in vm.cylinders" class="form-control" required>
									<option value=""> Seleccione tipo</option> 
								<!-- 	<option value="800">800</option>
									<option value="1000">1000</option>
									<option value="1200">1200</option>
									<option value="1300">1300</option>
									<option value="1400">1400</option>
									<option value="1600">1600</option>
									<option value="1800">1800</option>
									<option value="2000">2000</option> -->
								</select>
								<span ng-show="form.cylinders.$dirty && form.cylinders.$error.required" class="help-block">Cilindrada es requerido</span>
	                        </div>
	                        
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.luggage.$dirty && form.luggage.$error.required }">
	                            <label for="luggage" class="control-label">* Cantidad de valijas</label>
	                            <input type="number" min="1" ng-model="vm.requestModel.luggage" name="luggage" id="luggage" class="form-control" placeholder="1" required>
								<span ng-show="form.luggage.$dirty && form.luggage.$error.required" class="help-block">Cantidad de valias es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.fuel.$dirty && form.fuel.$error.required }">
	                            <label for="fuel" class="control-label">* Tipo Combustible</label>
	                            <select ng-model="vm.requestModel.fuel.id" name="fuel" id="fuel" ng-options="item.id as item.fuelType for item in vm.fuelType" class="form-control" required>
									<option value=""> Seleccione un tipo de combustible</option> 
								<!-- 	<option value="1">Nafta</option>
									<option value="2">Gas Oil</option>  -->
								</select>
								<span ng-show="form.fuel.$dirty && form.fuel.$error.required" class="help-block">Tipo de combustible es requerido</span>
	                        </div>
	                        
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.fullTank.$dirty && form.fullTank.$error.required }">
	                            <label for="fullTank" class="control-label">* Capacidad de lts. del tanque</label>
	                            <input type="number" min="1" ng-model="vm.requestModel.fullTank" name="fullTank" id="fullTank" class="form-control" placeholder="1 lts" required>
								<span ng-show="form.fullTank.$dirty && form.fullTank.$error.required" class="help-block">Capacidad del tanque es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.transmission.$dirty && form.transmission.$error.required }">
	                            <label for="transmission" class="control-label">* Transmision</label>
	                            <select ng-model="vm.requestModel.transmission" name="transmission" id="transmission" class="form-control" required>
									<option value=""> Seleccione tipo</option> 
									<option value="M">Manual</option>
									<option value="A">Automatico</option>
								</select>
								<span ng-show="form.transmission.$dirty && form.transmission.$error.required" class="help-block">Tipo de transmision es requerido</span>
	                        </div>
	                        
	                        
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.insurance.$dirty && form.insurance.$error.required }">
	                            <label for="insurance" class="control-label">* Precio del seguro</label>
	                            <input type="number" min="0" ng-model="vm.requestModel.insurance" name="insurance" id="insurance" class="form-control" placeholder="$0" required>
								<span ng-show="form.insurance.$dirty && form.insurance.$error.required" class="help-block">Precio del seguro es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                    	<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.airConditioner.$dirty && form.airConditioner.$error.required }">
	                            <label for="airConditioner" class="control-label">* Aire acondicionado</label>
	                            <select ng-model="vm.requestModel.airConditioner" name="airConditioner" id="airConditioner" class="form-control" required>
									<option value=""> Seleccione tipo</option> 
									<option value="true">Con aire acondicionado</option>
									<option value="false">Sin  aire acondicionado</option>
								</select>
								<span ng-show="form.airConditioner.$dirty && form.airConditioner.$error.required" class="help-block">Tipo de aire acondicionado es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group col-md-12 legend"> <h4><strong>Imagenes</strong></h4><p></p> </div>
	                    
	                    <div class="row" ng-if="!vm.requestModel.images[2]">
	                    	<div  class="form-group col-sm-6">
								  <input type="file" ngf-select ng-model="vm.file" name="file" accept="image/*" ngf-max-size="2MB" required ngf-model-invalid="errorFile" ngf-multiple="true"> 
								<br/>
								<span class="help-block" style="padding-left:0px">
									<strong>Archivos compatibles: gif, png, jpg. Tamaño Maximo 1Mb</strong>
								</span>
							</div>
	                    </div>
	                    
	                    <br/><br/>
	                    
	                    <div class="row" ng-if="vm.requestModel.images[0]" style="padding: 15px;">
	                    	<div  class="form-group col-sm-2" ng-repeat="(key, value) in vm.requestModel.images">
	                    		<img src="images/{{value.fileLocation}}" style="height: 160px; float: left;">
	                    		<div class="text-center delete remove-image" style="float: left; position: absolute; background-color: #EDEDED;">
	                    			<a ng-click="deleteImage(value)" style="cursor: pointer;" title="Eliminar">
	                    				<span class="glyphicon glyphicon-remove"></span>
	                    			</a>
	                    		</div>
	                    	</div>
	                    </div>

                        <!-- Buttons -->

                        <div class="form-group text-right" style="padding: 15px;">
                            <a href="#/model" class="btn btn-lightred">Cancelar</a>
                            <button type="submit" id="submit" ng-disabled="form.$invalid" class="btn  btn-orange">Guardar</button>
                        </div>
                        
            		</form>
            		</div>
            	</section>
            </div>
		<!-- /row -->
        </div>

    </div>

</section>
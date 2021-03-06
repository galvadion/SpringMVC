<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Reservas </strong> Crear</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Dashboard</a></li>
                <li><a href="#/booked">Reservas</a></li>
                <li class="breadcrumb-active">Nueva Reserva</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile body -->
                    <div class="tile-body">
            			
            			<form  form-on-change="checkFields()" name='form' class="" role="form" ng-submit="saveModel()">

                        <div class="form-group" ng-class="{ 'has-error': form.name.$dirty && form.name.$error.required }">
                            <label for="name" class="control-label">* Nombre</label>
                            <input type="text" name="name" id="name" class="form-control" id="name" ng-model="vm.model.name" placeholder="Nombre" required>
                            <span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Nombre es requerido</span>
                        </div>

						<div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.brand.$dirty && form.brand.$error.required }">
	                            <label for="brand" class="control-label">* Marca</label>
	                            <select ng-model="vm.model.brand" name="brand" id="brand" class="form-control" required>
									<option value=""> Seleccione una marca</option> 
									<option value="1">Fiat</option>
									<option value="2">Cherry</option>
									<option value="3">Mercedes</option>
								</select>
								<span ng-show="form.brand.$dirty && form.brand.$error.required" class="help-block">Marca es requerida</span>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.year.$dirty && form.year.$error.required }">
	                            <label for="year" class="control-label">* A�o</label>
	                            <select ng-model="vm.model.year" name="year" id="year" class="form-control" required>
									<option value=""> Seleccione el a�o</option> 
									<option value="2016">2016</option>
									<option value="2015">2015</option>
									<option value="2014">2014</option>
									<option value="2013">2013</option>
								</select>
								<span ng-show="form.year.$dirty && form.year.$error.required" class="help-block">A�o es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.category.$dirty && form.category.$error.required }">
	                            <label for="category" class="control-label">* Categoria</label>
	                            <select ng-model="vm.model.category" name="category" id="category" class="form-control" required>
									<option value=""> Seleccione una categoria</option> 
									<option value="1">S</option>
									<option value="2">A</option>
									<option value="3">B</option>
									<option value="4">C</option>
									<option value="4">D</option>
								</select>
								<span ng-show="form.category.$dirty && form.category.$error.required" class="help-block">Marca es requerida</span>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.passangers.$dirty && form.passangers.$error.required }">
	                            <label for="passangers" class="control-label">* Cantidad de pasajeros</label>
	                            <input type="number" min="1" ng-model="vm.model.passangers" name="passangers" id="passangers" class="form-control" placeholder="1" required>
								<span ng-show="form.passangers.$dirty && form.passangers.$error.required" class="help-block">Cantidad de pasajeros es requerido</span>
	                        </div>
	                    </div>

                      	<div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.cylinders.$dirty && form.cylinders.$error.required }">
	                            <label for="cylinders" class="control-label">* Cilindrada</label>
	                            <select ng-model="vm.model.cylinders" name="cylinders" id="cylinders" class="form-control" required>
									<option value=""> Seleccione tipo</option> 
									<option value="800">800</option>
									<option value="1000">1000</option>
									<option value="1200">1200</option>
									<option value="1300">1300</option>
									<option value="1400">1400</option>
									<option value="1600">1600</option>
									<option value="1800">1800</option>
									<option value="2000">2000</option>
								</select>
								<span ng-show="form.cylinders.$dirty && form.cylinders.$error.required" class="help-block">Cilindrada es requerido</span>
	                        </div>
	                        
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.luggage.$dirty && form.luggage.$error.required }">
	                            <label for="luggage" class="control-label">* Cantidad de valijas</label>
	                            <input type="number" min="0" ng-model="vm.model.luggage" name="luggage" id="luggage" class="form-control" placeholder="0" required>
								<span ng-show="form.luggage.$dirty && form.luggage.$error.required" class="help-block">Cantidad de valias es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.fuel.$dirty && form.fuel.$error.required }">
	                            <label for="fuel" class="control-label">* Tipo Combustible</label>
	                            <select ng-model="vm.model.fuel" name="fuel" id="fuel" class="form-control" required>
									<option value=""> Seleccione un tipo de combustible</option> 
									<option value="1">Nafta</option>
									<option value="2">Gas Oil</option>
								</select>
								<span ng-show="form.fuel.$dirty && form.fuel.$error.required" class="help-block">Tipo de combustible es requerido</span>
	                        </div>
	                        
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.fullTank.$dirty && form.fullTank.$error.required }">
	                            <label for="fullTank" class="control-label">* Capacidad de lts. del tanque</label>
	                            <input type="number" min="1" ng-model="vm.model.fullTank" name="fullTank" id="fullTank" class="form-control" placeholder="1 lts" required>
								<span ng-show="form.fullTank.$dirty && form.fullTank.$error.required" class="help-block">Capacidad del tanque es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.transmission.$dirty && form.transmission.$error.required }">
	                            <label for="transmission" class="control-label">* Transmision</label>
	                            <select ng-model="vm.model.transmission" name="transmission" id="transmission" class="form-control" required>
									<option value=""> Seleccione tipo</option> 
									<option value="manual">Manual</option>
									<option value="automatico">Automatico</option>
								</select>
								<span ng-show="form.transmission.$dirty && form.transmission.$error.required" class="help-block">Tipo de transmision es requerido</span>
	                        </div>
	                        
	                        
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.insurance.$dirty && form.insurance.$error.required }">
	                            <label for="insurance" class="control-label">* Precio del seguro</label>
	                            <input type="number" min="1" ng-model="vm.model.insurance" name="insurance" id="insurance" class="form-control" placeholder="$0" required>
								<span ng-show="form.insurance.$dirty && form.insurance.$error.required" class="help-block">Precio del seguro es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                    	<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.airConditioner.$dirty && form.airConditioner.$error.required }">
	                            <label for="airConditioner" class="control-label">* Aire acondicionado</label>
	                            <select ng-model="vm.model.airConditioner" name="airConditioner" id="airConditioner" class="form-control" required>
									<option value=""> Seleccione tipo</option> 
									<option value="1">Con aire acondicionado</option>
									<option value="0">Sin  aire acondicionado</option>
								</select>
								<span ng-show="form.airConditioner.$dirty && form.airConditioner.$error.required" class="help-block">Tipo de aire acondicionado es requerido</span>
	                        </div>
	                    </div>

                        <!-- Buttons -->

                        <div class="form-group text-right">
                            <a href="#/vehicle" class="btn btn-lightred">Cancelar</a>
                            <button type="submit" id="submit" ng-disabled="form.$invalid" class="btn  btn-orange">Crear</button>
                        </div>

                    </form>
            			
            		</div>
            	
            	</section>
            </div>
		<!-- /row -->
        </div>

    </div>

</section>
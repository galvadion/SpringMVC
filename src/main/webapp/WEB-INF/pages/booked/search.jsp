<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>B�squeda</strong></h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">B�squeda</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-5">
            	<section class="tile">
					<br/>
					<br/>
					<form name='form' class="" role="form" ng-submit="searchModels(vm.search)">

						<div class="row" style="padding: 0 10px 0 10px;">

							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="beginDate" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label for="beginDate" class="control-label">Fecha de recogida</label>
									<input class="form-control" size="16" type="text" name="beginDate" placeholder="DD/MM/YYYY" ng-model="vm.search.beginDate" ng-change="checkEndDate()" readonly required>
									<span class="input-group-addon customAddonSize">
									<span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>

							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeOriginId.$dirty && form.officeOriginId.$error.required }">
								<label for="officeOriginId" class="control-label">Sucursal de recogida</label>
								<select ng-options="item.id as item.name for item in vm.allOffices" ng-model="vm.search.officeOriginId" name="officeOriginId" id="officeOriginId" class="form-control" required>
									<option value="">Seleccione sucursal</option>
								</select>
								<span ng-show="form.officeOriginId.$dirty && form.officeOriginId.$error.required" class="help-block">Sucursal es requerido</span>
							</div>
						</div>
						<div class="row" style="padding: 0 10px 0 10px;">

							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="endDate" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label for="endDate" class="control-label">Fecha de devolucion</label>
									<input class="form-control" size="16" type="text" name="endDate" placeholder="DD/MM/YYYY" ng-model="vm.search.endDate" ng-change="checkEndDate()" readonly required>
									<span class="input-group-addon customAddonSize">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
								<span ng-show="endDateError" class="help-block" style="color: red">Fecha de entrega debe ser posterior a la de recogida</span>
							</div>

							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeEndId.$dirty && form.officeEndId.$error.required }">
								<label for="officeEndId" class="control-label">Sucursal de devolucion</label>
									<select ng-options="item.id as item.name for item in vm.allOffices" ng-model="vm.search.officeEndId" name="officeEndId" id="officeEndId" class="form-control" required>
										<option value="">Seleccione sucursal</option>
									</select>
								<span ng-show="form.officeEndId.$dirty && form.officeEndId.$error.required" class="help-block">Sucursal es requerido</span>
							</div>
						</div>
						
						<br/>
						
						<div class="tile-header dvd dvd-btm">
                            <h1 class="custom-font"><strong>B�squeda </strong> Avanzada</h1>
                            <button type="button" class="btn" data-toggle="collapse" data-target="#demo"><span class="glyphicon glyphicon-chevron-down"></span></button>
                        </div>
                        
                        <br/>
					
						<div class="row collapse" style="padding: 0 10px 0 10px;" id="demo">
							<div class="form-group col-sm-3" class="form-group">
								<label for="passangers" class="control-label">Pasajeros</label>
									<input type="number" min="0" ng-model="vm.search.passangers" name="passangers" id="passangers" class="form-control" placeholder="0">
							</div>
							
							<div class="form-group col-sm-3" class="form-group">
								<label for="luggage" class="control-label">Valijas</label>
									<input type="number" min="0" ng-model="vm.search.luggage" name="luggage" id="luggage" class="form-control" placeholder="0">
							</div>
							
							<div class="form-group col-sm-3" class="form-group">
								<label for="airConditioner" class="control-label">Aire acondicionado</label>
								<br/>
								<input type="checkbox" ng-model="vm.search.airConditioner" name="airConditioner" id="airConditioner" class="">
							</div>
						</div>

						<br/>

						<!-- Buttons -->
						<div class="form-group text-right" style="margin-right: 15px;">
							<button type="submit" id="submit" ng-disabled="form.$invalid || endDateError" class="btn  btn-orange">Buscar ahora</button>
						</div>
						
						<br/>
					</form>

				</section>
            	
            </div>
		
        	<div class="col-md-7">
            	<section class="tile">
					
					<div class="tile-header dvd dvd-btm" id="searchResult">
                        <h1 class="custom-font"><strong>Tus resultados </strong></h1>
                    </div>

                    <br/>
                    	<h4 class="text-center" ng-if="vm.noResult">No se encontraron veh�culos</h4>
	                    <div ng-repeat="(key, value) in vm.searchResult"  class="row" style="padding: 15px;">
	                    	<div class="col-sm-6">
								<img ng-if="!value.images[0]" src="static/images/noimage.png" style="height: 150px; max-width: 240px;">
								<img ng-if="value.images[0]" src="images/{{value.images[0].fileLocation}}" style="height: 150px; max-width: 240px;">
	                    	</div>
	                    	<div class="col-sm-6">
	                    		<table class="table borderless">
	                    			<tr>
	                    				<td>
	                    					<p>Marca: {{value.brand.name}}</p>
	                    				</td>
	                    				<td>
	                    					<p>Modelo: {{value.name}}</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>A�o: {{value.year}}</p>
	                    				</td>
	                    				<td>
	                    					<p>Categoria: {{value.category.name}}</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Transmision: {{value.transmission}}</p>
	                    				</td>
	                    				<td>
	                    					<p>Combustible: {{value.fuel.fuelType}}</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Pasajeros: {{value.passangers}}</p>
	                    				</td>
	                    				<td>
	                    					<p>Valijas: {{value.luggage}}</p>
	                    				</td>
	                    			</tr>

	                    		</table>
	                    	</div>
	                    	
	                    	<div class="col-sm-12">
	                    		<a href class="btn  btn-orange pull-right" ng-click="reservar(value.id)"><strong>Reservar (USD{{value.category.basePrice + value.fuel.fuelPrice }} base por dia )</strong></a>
	                    	</div>
	                    </div>
	                    
	                    <hr class="line-dashed line-full"/>

				</section>
            </div>
        </div>
        
        
    </div>

</section>
<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Búsqueda</strong></h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Búsqueda</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	<section class="tile">
					<br/>
					<br/>
					<form name='form' class="" role="form" ng-submit="searchModels()">

						<div class="row" style="padding: 0 10px 0 10px;">

							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="beginDate" data-date="" data-date-format="dd-mm-yyyy" data-link-field="dtp_input2" data-link-format="dd-mm-yyyy">
									<label for="beginDate" class="control-label">Fecha de recogida</label>
									<input class="form-control" size="16" type="text" name="beginDate" placeholder="DD-MM-YYYY" ng-model="vm.search.beginDate" ng-change="checkEndDate()" readonly required>
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
								<div class="input-group date" id="endDate" data-date="" data-date-format="dd-mm-yyyy" data-link-field="dtp_input2" data-link-format="dd-mm-yyyy">
									<label for="endDate" class="control-label">Fecha de entrega</label>
									<input class="form-control" size="16" type="text" name="endDate" placeholder="DD-MM-YYYY" ng-model="vm.search.endDate" ng-change="checkEndDate()" readonly required>
									<span class="input-group-addon customAddonSize">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
								<span ng-show="endDateError" class="help-block" style="color: red">Fecha de entrega debe ser posterior a la de recogida</span>
							</div>

							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeEndId.$dirty && form.officeEndId.$error.required }">
								<label for="officeEndId" class="control-label">Sucursal de entrega</label>
									<select ng-options="item.id as item.name for item in vm.allOffices" ng-model="vm.search.officeEndId" name="officeEndId" id="officeEndId" class="form-control" required>
										<option value="">Seleccione sucursal</option>
									</select>
								<span ng-show="form.officeEndId.$dirty && form.officeEndId.$error.required" class="help-block">Sucursal es requerido</span>
							</div>
						</div>
						
						<br/>
						
						<div class="tile-header dvd dvd-btm">
                            <h1 class="custom-font"><strong>Búsqueda </strong> Avanzada</h1>
                            <button type="button" class="btn" data-toggle="collapse" data-target="#demo"><span class="glyphicon glyphicon-chevron-down"></span></button>
                        </div>
                        
                        <br/>
					
						<div class="row collapse" style="padding: 0 10px 0 10px;" id="demo">
							<div class="form-group col-sm-3" class="form-group">
								<label for="passangers" class="control-label">Cantidad de pasajeros</label>
									<input type="number" min="0" ng-model="vm.search.passangers" name="passangers" id="passangers" class="form-control" placeholder="0">
							</div>
							
							<div class="form-group col-sm-3" class="form-group">
								<label for="luggage" class="control-label">Disponibilidad de valijas</label>
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
		<!-- /row -->
        </div>
        
        <br/>
        
        <div class="row">
        	<div class="col-md-12">
            	<section class="tile">
					
					<div class="tile-header dvd dvd-btm" id="searchResult">
                        <h1 class="custom-font"><strong>Tus resultados </strong></h1>
                    </div>
                    
                    <br/>
                    
                    <!-- <div class="row" ng-repeat="(key, value) in vm.searchResult"> -->
	                    <div class="row" style="padding: 15px;">
	                    	<div class="col-sm-3">
	                    		<img src="static/images/logo-wide-transparent.png" style="height:150px; max-width:240px;">
	                    	</div>
	                    	<div class="col-sm-2">
	                    		<table>
	                    			<tr>
	                    				<td>
	                    					<p>Marca: Fiat</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Modelo: Fiorino</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Año: 2014</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Categoria: B</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Transmision: Manual</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Combustible: Nafta</p>
	                    				</td>
	                    			</tr>
	                    		</table>
	                    	</div>
	                    	<div class="col-sm-6">
	                    		<table>
	                    			<tr>
	                    				<td>
	                    					<p>Marca: Fiat</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Modelo: Fiorino</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Año: 2014</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Categoria: B</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Transmision: Manual</p>
	                    				</td>
	                    			</tr>
	                    			<tr>
	                    				<td>
	                    					<p>Combustible: Nafta</p>
	                    				</td>
	                    			</tr>
	                    		</table>
	                    	</div>
	                    	<div class="col-sm-1">
	                    		<a href class="btn  btn-orange"><strong>Reservar</strong></a>
	                    	</div>
	                    </div>
	                    
	                    <hr class="line-dashed line-full"/>
	                    
	                    <div class="row" style="padding: 15px;">
	                    	<div class="col-sm-3">
	                    		<img src="static/images/logo-wide-transparent.png" style="height:130px; max-width:240px;">
	                    	</div>
	                    	<div class="col-sm-8">
	                    		<table>
	                    			<tr><td>1</td></tr>
	                    			<tr><td>2</td></tr>
	                    			<tr><td>3</td></tr>
	                    			<tr><td>4</td></tr>
	                    			<tr><td>5</td></tr>
	                    			<tr><td>6</td></tr>
	                    		</table>
	                    	</div>
	                    	<div class="col-sm-1">
	                    		<a href class="btn  btn-orange"><strong>Reservar</strong></a>
	                    	</div>
	                    </div>
	                    
					<!-- </div> -->

				</section>
            </div>
        </div>
        
        
    </div>

</section>
<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="row">
			<div class="col-md-12">
				<section class="tile text-center" id="slyder">
					Slyder
				</section>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<section class="tile">
					<br/>
					<h3 class="text-center">Encuentra tu vehiculo</h3>
					<br/>
					<form name='form' class="" role="form" ng-submit="searchModels()">
						
						<div class="row" style="padding: 0 10px 0 10px;">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.beginDate.$dirty && form.beginDate.$error.required }">
	                            <label for="beginDate" class="control-label">Fecha de recogida</label>
	                            <input type="text" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" name="beginDate" class="form-control" placeholder="YYYY-MM-DD" ng-model="vm.search.beginDate" required />
								<span ng-show="form.beginDate.$dirty && form.beginDate.$error.required" class="help-block">Fecha es requerido</span>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeOriginId.$dirty && form.officeOriginId.$error.required }">
	                            <label for="officeOriginId" class="control-label">Sucursal de recogida</label>
	                            <select ng-model="vm.search.officeOriginId" name="officeOriginId" id="officeOriginId" class="form-control" required>
									<option value=""> Seleccione sucursal</option> 
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
								<span ng-show="form.officeOriginId.$dirty && form.officeOriginId.$error.required" class="help-block">Sucursal es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row" style="padding: 0 10px 0 10px;">
	                        <div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.endDate.$dirty && form.endDate.$error.required }">
	                            <label for="endDate" class="control-label">Fecha de entrega</label>
	                            <input type="text" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" name="endDate" class="form-control" placeholder="YYYY-MM-DD" ng-model="vm.search.endDate" required />
								<span ng-show="form.endDate.$dirty && form.endDate.$error.required" class="help-block">Fecha es requerido</span>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeEndId.$dirty && form.officeEndId.$error.required }">
	                            <label for="officeEndId" class="control-label">Sucursal de entrega</label>
	                            <select ng-model="vm.search.officeEndId" name="officeEndId" id="officeEndId" class="form-control" required>
									<option value=""> Seleccione sucursal</option> 
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
								<span ng-show="form.officeEndId.$dirty && form.officeEndId.$error.required" class="help-block">Sucursal es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <br/>
	                    
	                    <!-- Buttons -->
                        <div class="form-group text-right">
                            <button type="submit" id="submit" ng-disabled="form.$invalid" class="btn  btn-orange">Buscar</button>
                        </div>
		        	</form>
					
				</section>
			</div>
			
			<div class="col-md-1"></div>
			
			<div class="col-md-5">
				<section class="tile text-center">
					<div id="mapa"></div>
				</section>
			</div>
		</div>

		<br/>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile header -->
            		<a href="localhost:8443/SpringMVC/payment/start-paypal">
            			<div class="tile-header dvd dvd-btm">
                        	<h1 class="custom-font"><strong>Listado </strong>de promociones</h1>
                    	</div>
            		</a>
                    
                    <!-- /tile header -->

                    <!-- tile body -->
                    <div class="tile-body table-custom">
                    	
						<div class="table-responsive">
            				
            				<table datatable="ng" class="table mb-0 table-custom" id="VehicleList" dt-options="vm.dtOptions" dt-column-defs="vm.DTColumnDefs">
                                <thead>
                                   <tr>
                                   		<th>Marca</th>
                                   		<th>Modelo</th>
                                   		<th>Combustible</th>
                                   		<th>Ano</th>
                                   		<th>Pasajeros</th>
                                   		<th>Valijas</th>
                                   		<th>Cilindrada</th>
                                   		<th>Aire acondicionado</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="(key, value) in vm.searchResult">
                                    	<td>
                                            {{value.brand.name}}
                                        </td>
                                        <td>
                                            {{value.name}}
                                        </td>
                                        <td>
                                            {{value.fuel.fuelType}}
                                        </td>
                                        <td>
                                            {{value.year}}
                                        </td>
                                        <td>
                                            {{value.passangers}}
                                        </td>
                                        <td>
                                            {{value.luggage}}
                                        </td>
                                        <td>
                                            {{value.cylinders}}
                                        </td>
                                        <td>
                                            {{value.airConditioner}}
                                        </td>
                                   	</tr>
                                </tbody>
                            </table>
                         </div>

            		</div>
            	
            	</section>
            </div>
		<!-- /row -->
		
        </div>
        
        
        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                        <h1 class="custom-font"><strong>NICO </strong></h1>
                    </div>
                    <!-- /tile header -->

                    <!-- tile body -->
                    <form method="post" action="/SpringMVC/payment/start-paypal">
                    
                    <button type="submit" ><img src="https://www.paypalobjects.com/webstatic/en_US/i/btn/png/blue-rect-paypal-34px.png" alt="PayPal"></button> 
                    </form>
                    
            	
            	</section>
            </div>
		<!-- /row -->
		
        </div>

    </div>

</section>
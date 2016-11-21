<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Vehículos </strong> Crear
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Dashboard</a></li>
				<li><a href="#/vehicle">Vehículos</a></li>
				<li class="breadcrumb-active">Nuevo Vehículo</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">

				<section class="tile">

					<!-- tile body -->
					<div class="tile-body">

						<form form-on-change="checkFields()" name='form' class=""
							role="form" ng-submit="saveVehicle()">
							<div class="row">
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.licensePlate.$dirty && form.licensePlate.$error.required }">
									<label for="licensePlate" class="control-label">*
										Matricula</label> <input type="text"
										ng-model="vm.vehicle.licensePlate" name="licensePlate"
										id="licensePlate" class="form-control" placeholder="XXX- ####"
										required> <span
										ng-show="form.licensePlate.$dirty && form.licensePlate.$error.required"
										class="help-block">Es necesario ingresar la matricula</span>
								</div>
							
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.branchOffice.$dirty && form.branchOffice.$error.required }">
									<label for="branchOffice" class="control-label">Sucursal
										donde estara el vehículo</label> <select
										ng-model="vm.vehicle.branchOffice.id" name="branchOffice"
										id="branchOffice" ng-options="item.id as item.name for item in vm.allOffices" class="form-control" required>
										<option value="">Seleccione sucursal</option>
										
									</select> <span
										ng-show="form.branchOffice.$dirty && form.branchOffice.$error.required"
										class="help-block">Sucursal es requerido</span>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.brand.$dirty && form.brand.$error.required }">
									<label for="brand" class="control-label">* Marca</label>
									 <select ng-model="vm.brand.id" name="brand" id="brand"
										ng-change="getModelsByBrand()" ng-options="item.id as item.name for item in vm.allBrands" class="form-control" required>
										<option value="">Seleccione una marca</option>
									</select> <span
										ng-show="form.brand.$dirty && form.brand.$error.required"
										class="help-block">Marca es requerida</span>
								</div>
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.model.$dirty && form.model.$error.required }">
									<label for="model" class="control-label">* Modelo</label> 
									<select	ng-model="vm.vehicle.model.id" ng-options="item.id as item.name for item in vm.modelsByBrand" name="model" id="model"
										class="form-control" required>
										<option value="">Seleccione un modelo</option>
									</select> <span
										ng-show="form.model.$dirty && form.model.$error.required"
										class="help-block">Modelo es requerida</span>
								</div>
							</div>
							<div class="row">

								<div class="form-group customDatePickers col-sm-6">
									<div class="input-group date" id="licensePlateExpirationDate"
										data-date="" data-date-format="dd-mm-yyyy"
										data-link-field="dtp_input2" data-link-format="dd-mm-yyyy">
										<label for="licensePlateExpirationDate" class="control-label">Vencimiento
											de patente</label> <input class="form-control" size="16" type="text"
											name="licensePlateExpirationDate" placeholder="DD-MM-YYYY"
											ng-model="vm.vehicle.licensePlateExpirationDate"
											ng-change="checklicensePlateExpirationDate()" readonly
											required> <span
											class="input-group-addon customAddonSize"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
									<span ng-show="licensePlateExpirationDateError"
										class="help-block" style="color: red">Fecha de entrega
										debe ser posterior a la de recogida</span>
								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.motorNr.$dirty && form.motorNr.$error.required }">
									<label for="motorNr" class="control-label">* Número de
										motor</label> <input type="text" ng-model="vm.vehicle.motorNr"
										name="motorNr" id="motorNr" class="form-control"
										placeholder="Número de motor" required> <span
										ng-show="form.motorNr.$dirty && form.motorNr.$error.required"
										class="help-block">Es necesario ingresar el número de
										motor</span>
								</div>
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.chasisNr.$dirty && form.chasisNr.$error.required }">
									<label for="chasisNr" class="control-label">* Número de
										chasis</label> <input type="text" ng-model="vm.vehicle.chasisNr"
										name="chasisNr" id="chasisNr" class="form-control"
										placeholder="Número de chasis" required> <span
										ng-show="form.chasisNr.$dirty && form.chasisNr.$error.required"
										class="help-block">Es necesario ingresar el número de
										chasis</span>
								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.kilometers.$dirty && form.kilometers.$error.required }">
									<label for="kilometers" class="control-label">* Kilómetros
										ya recorridos</label> <input type="number"
										ng-model="vm.vehicle.kilometers" name="kilometers"
										id="kilometers" class="form-control" min="1"
										placeholder="Kilómetros" required> <span
										ng-show="form.kilometers.$dirty && form.kilometers.$error.required"
										class="help-block">Es necesario ingresar los kilómetros
										recorridos</span>
								</div>
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.color.$dirty && form.color.$error.required }">
									<label for="color" class="control-label">* Color</label> <input
										type="text" ng-model="vm.vehicle.color" name="color"
										id="color" class="form-control" placeholder="color" required>
									<span ng-show="form.color.$dirty && form.color.$error.required"
										class="help-block">Es necesario ingresar el color</span>
								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-12" class="form-group"
									ng-class="{ 'has-error': form.description.$dirty && form.description.$error.required }">
									<label for="description" class="control-label">* Descripción</label>
										 <textarea type="text" ng-model="vm.vehicle.description"
										name="description" id="description" class="form-control"
										placeholder="Descripción del vehículo" required /> <span
										ng-show="form.description.$dirty && form.description.$error.required"
										class="help-block">Es necesario escribir una descripción para el usuario</span>
								</div>
								</div>
								<div class="row">
								<div class="form-group col-sm-12" class="form-group">
									<label for="observations" class="control-label">Observaciones</label>
									<textarea type="text" ng-model="vm.vehicle.observations"
										name="observations" id="observations" class="form-control"
										placeholder="Observaciones" />
								</div>
							</div>

							<!-- Buttons -->

							<div class="form-group text-right">
								<a href="#/vehicle" class="btn btn-lightred">Cancelar</a>
								<button type="submit" id="submit" ng-disabled="form.$invalid"
									class="btn  btn-orange">Guardar</button>
							</div>

						</form>

					</div>

				</section>
			</div>
			<!-- /row -->
		</div>

	</div>

</section>
<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Vehículos </strong> Detalles
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Dashboard</a></li>
				<li><a href="#/vehicle">Vehículos</a></li>
				<li class="breadcrumb-active">Detalles de vehiculo</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">

				<section class="tile">

					<!-- tile body -->
					<div class="tile-body">

						<div class="row vehicleDesc">
							<div class="col-md-4" style="margin-left: 20px; font-size: 15px">
								<div class="row">
									<label><b>Matrícula:</b> {{vm.vehicle.licensePlate}}</label>
								</div>
								<div class="row">
									<label><b>Sucursal de origen:</b> {{vm.vehicle.branchOffice.name}}</label>
								</div>
								<div class="row">
									<label><b>Marca:</b> {{vm.vehicle.model.brand.name}}</label>
								</div>
								<div class="row">
									<label><b>Modelo:</b> {{vm.vehicle.model.name}}</label>
								</div>
								<div class="row">
									<label><b>Vencimiento de patente:</b> {{vm.vehicle.licensePlateExpirationDate}}</label>
								</div>
								<div class="row">
									<label><b>Número de motor:</b> {{vm.vehicle.motorNr}}</label>
								</div>
							</div>
							<div class="col-md-4" style="margin-left: 15px; font-size: 15px">
								<div class="row">
									<label><b>Número de chasis:</b> {{vm.vehicle.chasisNr}}</label>
								</div>
								<div class="row">
									<label><b>Kilómetros ya recorridos:</b> {{vm.vehicle.kilometers}}</label>
								</div>
								<div class="row">
									<label><b>Color:</b> {{vm.vehicle.color}}</label>
								</div>
								<div class="row">
									<label><b>Descripción:</b> {{vm.vehicle.description}}</label>
								</div>
								<div class="row">
									<label><b>Observaciones:</b> {{vm.vehicle.observations}}</label>
								</div>
							</div>
						</div>
					
					</div>

				</section>
				
				<br/>

				<section class="tile">
					<div class="tile-header dvd dvd-btm" id="searchResult">
                        <h1 class="custom-font"><strong>Mantenimiento </strong></h1>
                    </div>

                    <br/>
					
					<div class="tile-body">
						<form form-on-change="checkFields()" name='form' class="" role="form">
							<div class="row">
								<div class="form-group customDatePickers col-sm-6">
									<div class="input-group date" id="date1" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
										<label for="date1" class="control-label">Fecha 1</label> <input class="form-control" size="16" type="text" name="date1" placeholder="DD/MM/YYYY" ng-model="vm.date1" ng-change="checkDetailsExpirationDate()" readonly required>
										<span
											class="input-group-addon customAddonSize"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
									<span ng-show="licensePlateExpirationDateError" class="help-block" style="color: red">
										Fecha 1 debe ser anterior a la de fecha 2
									</span>
								</div>
								
								<div class="form-group customDatePickers col-sm-6">
									<div class="input-group date" id="date2" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
										<label for="date2" class="control-label">Fecha 2</label> <input class="form-control" size="16" type="text" name="date2" placeholder="DD/MM/YYYY" ng-model="vm.date2" ng-change="checkDetailsExpirationDate()" readonly required>
										<span
											class="input-group-addon customAddonSize"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
									<span ng-show="licensePlateExpirationDateError" class="help-block" style="color: red">
										Fecha 2 debe ser posterior a la de fecha 1
									</span>
								</div>
							</div>
							
							<br/>
							
							<div class="row">
								<!-- Buttons -->
								<div class="form-group text-right" style="margin-right: 15px;">
									<a href="#/vehicle" class="btn btn-lightred">Cancelar</a>
									<button type="submit" id="submit" ng-disabled="form.$invalid" class="btn  btn-orange" ng-click="">Guardar</button>
								</div>
							</div>
						</form>
					</div>
				</section>
				
			</div>
			<!-- /row -->
		</div>

	</div>

</section>
<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Alquileres </strong>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Dashboard</a></li>
				<li><a href="#/rent">Alquileres</a></li>
				<li class="breadcrumb-active">Confirmar alquiler</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">

				<section class="tile">
					<div class="tile-body">
						
						<div class="row">
							<div class="col-sm-6">
								<div class="table-responsive">
									<table>
										<tr>
											<td>
												<p>Fecha de inicio: {{vm.booked.beginbookedDate}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Fecha de devolucion: {{vm.booked.lastbookedDate}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Vehiculo: {{vm.booked.vehicle.licensePlate}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Devuelve en: {{vm.booked.endOffice.name}}</p>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="table-responsive">
									<table>
										<tr>
											<td>
												<p>Nombre: {{vm.booked.client.name}}
													{{vm.booked.client.lastName}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Telefono: {{vm.booked.client.phone}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Direccion: {{vm.booked.client.address}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Fecha de nacimiento: {{vm.booked.client.birthDate}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Vencimiento de licencia:
													{{vm.booked.client.licenseExpirationDate}}</p>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						
						<hr class="line-dashed line-full"/>
						
						<div class="row">
							<div class="form-group col-sm-6">
								<label class="control-label"><strong>Nombre conductor:</strong></label>
								<input type="text" class="form-control" ng-model="vm.driver.driversName" placeholder="Ingrese el nombre">
							</div>
							<div class="form-group col-sm-6">
								<label class="control-label"><strong>Documento:</strong></label>
								<input type="text" class="form-control"  ng-model="vm.driver.driversDocument" placeholder="Documento de identidad o pasaporte">
							</div>
						</div>
						
						<div class="row">
							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label class="control-label"><strong>Fecha de nacimiento:</strong></label>
									<input class="form-control underline-input" size="16" type="text" id="birthDate" placeholder="Fecha de nacimiento DD/MM/YYYY" ng-model="vm.driver.birthDate" readonly required> 
									<span class="input-group-addon customAddonSize">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>

							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label for="beginDate" class="control-label"><strong>Fecha de vencimiento libreta</strong></label>
									<input class="form-control underline-input" size="16" id="licenseExpirationDate" type="text" placeholder="Fecha de vencimiento de licencia DD/MM/YYYY" ng-model="vm.driver.licenseExpirationDate" readonly required>
									<span class="input-group-addon customAddonSize">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group text-left" style="padding: 15px;">
								<button class="btn btn-success pull-left" id="addFine" ng-click="addDriver()" ng-disabled="!vm.driver.driversName || !vm.driver.driversDocument || !vm.driver.birthDate || !vm.driver.licenseExpirationDate">Agregar conductor</button>
							</div>
						</div>
						
					</div>
			</section>

			<div class="row">
				<div class="col-md-12">
	
					<section class="tile">
	
						<!-- tile header -->
						<div class="tile-header dvd dvd-btm">
							<h1 class="custom-font">
								<strong>Conductores </strong> asignados
							</h1>
						</div>
						<!-- /tile header -->
						
						<!-- tile body -->
						<div class="tile-body table-custom">
							<div class="row" id="drivers">
								<h4 style="margin-left: 15px; color: #449D44" ng-repeat="(key,value) in vm.rent.driversList">{{value.driversName}} - {{value.driversDocument}}</h4>
							</div>
							
							<div class="row">
								<div class="form-group text-right" style="padding: 15px;">
									<button class="btn btn-orange pull-right" ng-click="confirmRent()"><strong>Confirmar retiro de vehiculo</strong></button>
								</div>
							</div>
						</div>
	
					</section>
				</div>
			</div>
			
		</div>
	</div>
	
	</div>

</section>


<!-- /row -->

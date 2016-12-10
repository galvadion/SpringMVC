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

					<!-- /tile header -->

					<!-- tile body -->
					<div class="tile-header dvd dvd-btm row">
						<div class="row">
							<div class="col-sm-6">
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
							<div class="col-sm-6">
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
						<div class="row">
							<div class="form-horizontal" role="form">
								<div class="form-group col-sm-6">
									<label class="control-label col-sm-3" for="text"><strong>Nombre
											conductor:</strong></label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id='codigo'
											ng-model="vm.driver.driversName"
											placeholder="Ingrese el nombre">
									</div>
								</div>
								<div class="form-group col-sm-6">

									<label class="control-label col-sm-3" for="text"><strong>Documento:</strong></label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id='monto'
											ng-model="vm.driver.driversDocument"
											placeholder="Documento de identidad o pasaporte">
									</div>
								</div>
								</div>
								<div class="row">
								<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="beginDate" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label for="beginDate" class="control-label">Fecha de nacimiento</label>
										<input class="form-control underline-input" size="16"
											type="text" name="birthDate"
											placeholder="Fecha de nacimiento DD/MM/YYYY"
											ng-model="vm.driver.birthDate" readonly required> 
											<span class="input-group-addon customAddonSize">
									<span class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>

								<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="beginDate" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label for="beginDate" class="control-label">Fecha de vencimiento libreta</label>
										<input class="form-control underline-input" size="16"
											type="text" name="licenseExpirationDate"
											placeholder="Fecha de vencimiento de licencia DD/MM/YYYY"
											ng-model="vm.driver.licenseExpirationDate" readonly required>
										<span class="input-group-addon customAddonSize">
									<span class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<button class="btn btn-success pull-right" id="addFine"
									ng-click="addDriver()">Agregar conductor</button>
							</div>
						</div>
					</div>
			</div>

			<div id="drivers">
				<p ng-repeat="(key,value) in vm.rent.drivers">{{value.name}}
					{{value.document}}</p>
			</div>
			<button ng-click="confirmRent()" class="btn  btn-orange">
				<strong>Confirmar retiro de vehiculo</strong>
			</button>
		</div>
	</div>

</section>


<!-- /row -->



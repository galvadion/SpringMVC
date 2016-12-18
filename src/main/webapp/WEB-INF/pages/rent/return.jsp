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
				<li class="breadcrumb-active">Validar devolucion</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">

				<section class="tile">
					<div class="tile-body">
						<!-- /tile header -->

						<!-- tile body -->
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
											<p>Fecha de devolucion prevista:
												{{vm.booked.lastbookedDate}}</p>
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
									<tr>
										<td>
											<p>Observaciones del vehiculo previas:
												{{vm.booked.vehicle.observations}}</p>
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
											<p>Document: {{vm.booked.client.document}}</p>
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

						<hr class="line-dashed line-full" />


						<div class="row">
							<div class="form-group col-sm-6">
								<label class="control-label" for="text"><strong>Detalle:</strong></label>

								<input type="text" class="form-control" id='codigo'
									ng-model="vm.detail.detail" placeholder="Ingrese el detalle">
							</div>
							<div class="form-group col-sm-6">
								<label class="control-label col-sm-1" for="text"><strong>Precio:</strong></label>

								<input type="number" class="form-control" id='monto'
									ng-model="vm.detail.amount" placeholder="Precio" value="0">
							</div>
						</div>
						<div class="row">
							<div class="form-group text-left" style="padding: 15px;">
								<button class="btn btn-success pull-left" id="addFine"
									ng-click="addFine()"
									ng-disabled="!vm.detail.amount || !vm.detail.detail">Agregar
									concepto de recargo</button>
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
									<strong>Conceptos de recargo </strong>
								</h1>
							</div>
							<!-- /tile header -->

							<!-- tile body -->
							<div class="tile-body table-custom">
								<div class="row" id="drivers">
									<h4 style="margin-left: 15px; color: #449D44"
										ng-repeat="(key,value) in vm.rent.rentLine">{{value.detail}}
										- {{value.amount}}</h4>
								</div>

								<div class="row">
									<div class="form-group text-right" style="padding: 15px;">
									<div ng-show="vm.rent.rentLine[0]">
									<label for="name" class="col-xs-offset-1 col-xs-4">Numero de factura in-situ</label>
                            		<input type="text" name="promoCode" id="promoCode" class="col-xs-3" ng-model="vm.rent.transactionNr" placeholder="">
										</div>
										<button class="btn btn-orange pull-right"
											ng-click="confirmReturn()" >
											<strong ng-show="!vm.rent.rentLine[0]">Confirmar devolucion</strong>
											<strong ng-show="vm.rent.rentLine[0]">Realizar cobro</strong>
										</button>
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
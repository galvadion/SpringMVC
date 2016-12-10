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
						<div class="row">
							<div class="form-horizontal" role="form">
								<div class="form-group">
									<label class="control-label col-sm-1" for="text"><strong>Detalle:</strong></label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id='codigo' ng-model="vm.detail.detail"
											placeholder="Ingrese el detalle">
									</div>
									<label class="control-label col-sm-1" for="text"><strong>Precio:</strong></label>
									<div class="col-sm-2">
										<input type="number" class="form-control" id='monto' ng-model="vm.detail.amount"
											placeholder="Precio" value="0">
									</div>
									<div class="col-sm-3">
										<button class="btn btn-success pull-right" id="addFine"
											onclick="addFine()">Agregar multa de existir</button>
									</div>
								</div>
							</div>
						</div>
							<div id="fines">
				<p ng-repeat="(key,value) in vm.rent.rentLines">{{value.detail}}
					{{value.amount}}</p>
			</div>
			<button ng-click="doReturn()" class="btn  btn-orange">
				<strong>Ir a devolucion de vehiculo</strong>
			</button>
							
						
					</div>

				</section>
			</div>

			<!-- /row -->
		</div>

	</div>

</section>
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

						<div class="col-sm-4">
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
						<div class="col-sm-4">
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
					<a ng-click="confirmRent()" class="btn  btn-orange"><strong>Confirmar</strong></a>
					<p>Agregar conductor: <button type="button" onclick="addDriver()" class="btn btn-info"><span class="glyphicon glyphicon-plus" id="conductor" ></span></button></p>
					 <div id="drivers">
					 </div>
					</div>

				</section>
			</div>

			<!-- /row -->
		</div>

	</div>

</section>
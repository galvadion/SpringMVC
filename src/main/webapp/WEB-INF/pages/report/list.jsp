<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Actividades</strong>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Inicio</a></li>
				<li class="breadcrumb-active">Actividades</li>
			</ol>
		</div>
		
		<div ng-show="vm.roladmin" class="row">
			<div class="col-md-12">
				<section class="tile">

					<div class="tile-header dvd dvd-btm" id="searchResult">
						<div class="text-center">
							<h1 class="custom-font">
								<strong>Buscar entre fechas </strong>
							</h1>
						</div>
						<br>
						<div class="row">
							<div class="form-group customDatePickers col-sm-4">
								<div class="input-group date" id="beginDate" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label for="beginDate" class="control-label">Fecha inicial</label>
									<input class="form-control" size="16" type="text" name="beginDate" placeholder="DD/MM/YYYY" ng-model="vm.search.beginDate" readonly required>
									<span class="input-group-addon customAddonSize">
									<span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
							<div class="form-group customDatePickers col-sm-4">
								<div class="input-group date" id="endDate" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label for="endDate" class="control-label">Fecha final</label>
									<input class="form-control" size="16" type="text" name="endDate" placeholder="DD/MM/YYYY" ng-model="vm.search.endDate" readonly  required>
									<span class="input-group-addon customAddonSize">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group text-right" style="margin-right: 15px;">
								<button ng-click="getBetweenDates()" class="btn  btn-orange">Buscar ahora</button>
							</div>
						</div>
					</div>
					<br>
					<div class="row" ng-repeat="(key, value) in vm.resultList">
						<div class="row" style="padding: 15px;">
							<div class="col-sm-6">
								<table>
									<tr>
										<td>
											<p>Vehiculo: {{value.booked.vehicle.licensePlate}}</p>
										</td>
									</tr>
									<tr>
										<td>
											<p>Modelo:{{value.booked.vehicle.model.brand.name}}
												{{value.booked.vehicle.model.name}}</p>
										</td>
									</tr>
									<tr>
										<td>
											<p>Retirado en: {{value.booked.originOffice.city}} el día {{value.rent.deliveryDate}}</p>
										</td>
									</tr>
									<tr>
										<td>
											<p>Entrega en: {{value.booked.endOffice.city}} el día {{value.rent.returnDate}}</p>
										</td>
									</tr>
									<tr>
										<td>
											<p>Cliente: {{value.booked.client.name}}
												{{value.booked.client.lastName}}</p>
										</td>
									</tr>
								</table>
							</div>

						</div>

						<hr class="line-dashed line-full" />
					</div>
				</section>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<section class="tile">

					<div class="tile-header dvd dvd-btm" id="searchResult">
						<div class="text-center">
							<h1 class="custom-font">
								<strong>Retiran {{hoy}} </strong>
							</h1>
						</div>
						<br>
						<button class="btn btn-blue" ng-click='pickedup(yesterdayPick)'>
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>Ver
							dia anterior
						</button>
						<button class="pull-right btn btn-blue"
							ng-click='pickedup(tomorrowPick)'>
							Ver dia despues<span class="glyphicon glyphicon-chevron-right"
								aria-hidden="true"></span>
						</button>
					</div>

					<br />

					<div class="row" ng-repeat="(key, value) in vm.pickedToday">
						<div class="row" style="padding: 15px;">
							<div class="col-sm-8">
								<table>
									<tr>
										<td>
											<p>Vehiculo: {{value.vehicle.licensePlate}}</p>
										</td>
									</tr>
									<tr>
										<td>
											<p>Modelo:{{value.vehicle.model.brand.name}}
												{{value.vehicle.model.name}}</p>
										</td>
									</tr>
									<tr ng-show="vm.roladmin">
										<td>
											<p>Retiran en: {{value.originOffice.city}}
										</td>
									</tr>
									<tr>
										<td>
											<p>Entrega en: {{value.endOffice.city}}</p>
										</td>
									</tr>
									
									<tr>
										<td>
											<p>Cliente: {{value.client.name}}
												{{value.client.lastName}}</p>
										</td>
									</tr>
								</table>
							</div>

							<div class="col-sm-1">
								<a ng-if="!value.rent" href="#/rent/confirm/{{value.id}}" class="btn  btn-orange">
								<strong>Gestionar
										entrega</strong></a>
										<a ng-if="value.rent"><strong>Ya fue entregado</strong></a>
							</div>
						</div>

						<hr class="line-dashed line-full" />
					</div>

				</section>

			</div>

			<div class="col-md-6">
				<section class="tile">

					<div class="tile-header dvd dvd-btm" id="searchResult">
						<div class="text-center">
							<h1 class="custom-font">
								<strong>Devuelven {{hoyRet}} </strong>
							</h1>
						</div>
						<br>
						<button class="btn btn-blue" ng-click='returned(yesterdayReturn)'>
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>Ver
							dia anterior
						</button>
						<button class="pull-right btn btn-blue"
							ng-click='returned(tomorrowReturn)'>
							Ver dia despues<span class="glyphicon glyphicon-chevron-right"
								aria-hidden="true"></span>
						</button>
					</div>

					<br />

					<div class="row" ng-repeat="(key, value) in vm.returnedToday">
						<div class="row" style="padding: 15px;">

							<div class="col-sm-8">
								<table>
									<tr>
										<td>
											<p>Vehiculo: {{value.vehicle.licensePlate}}</p>
										</td>
									</tr>
									<tr>
										<td>
											<p>Modelo:{{value.vehicle.model.brand.name}}
												{{value.vehicle.model.name}}</p>
										</td>
									</tr>
									<tr>
										<td>
											<p>Cliente: {{value.client.name}}
												{{value.client.lastName}}</p>
										</td>
									</tr>
									<tr ng-show="vm.roladmin">
										<td>
											<p>Devuelve en: {{value.endOffice.city}}
										</td>
									</tr>
								</table>
							</div>

							<div class="col-sm-1">
								<a ng-if="!value.returned" href="#/rent/return/{{value.id}}" class="btn  btn-orange"><strong>Gestionar
										devolucion</strong></a>
										<a ng-if="value.returned">Ya fue devuelto</a>
										<a ng-if="!value.rent" href="">
								<strong>Aun no ha sido retirado</strong></a>
							</div>
						</div>

						<hr class="line-dashed line-full" />



						<!-- </div> -->
				</section>
			</div>
		</div>


	</div>

</section>
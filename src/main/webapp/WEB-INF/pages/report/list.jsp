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
							<div class="col-sm-2">
								<table>
									<tr>
										<td>
											<p>Vehiculo: {{value.vehicle.licensePlate}}</p>
										</td>
									</tr>
									<tr>
										<td>
											<p>Cliente: {{value.client.name}}
												{{value.client.lastName}}</p>
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
							<div class="col-sm-5">
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

						<hr class="line-dashed line-full" />

						<div class="row" style="padding: 15px;">
							<div class="col-sm-3">
								<img src="static/images/logo-wide-transparent.png"
									style="height: 130px; max-width: 240px;">
							</div>
							<div class="col-sm-2">
								<table>
									<tr>
										<td>1</td>
									</tr>
									<tr>
										<td>2</td>
									</tr>
									<tr>
										<td>3</td>
									</tr>
									<tr>
										<td>4</td>
									</tr>
									<tr>
										<td>5</td>
									</tr>
									<tr>
										<td>6</td>
									</tr>
								</table>
							</div>
							<div class="col-sm-1">
								<a href class="btn  btn-orange"><strong>Reservar</strong></a>
							</div>
						</div>

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
							ng-click='returned(tomorrowReturnF)'>
							Ver dia despues<span class="glyphicon glyphicon-chevron-right"
								aria-hidden="true"></span>
						</button>
					</div>

					<br />

					<div class="row" ng-repeat="(key, value) in vm.returnedToday">
						<div class="row" style="padding: 15px;">

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
							<div class="col-sm-5">
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

						<hr class="line-dashed line-full" />

						<div class="row" style="padding: 15px;">
							<div class="col-sm-3">
								<img src="static/images/logo-wide-transparent.png"
									style="height: 130px; max-width: 240px;">
							</div>
							<div class="col-sm-2">
								<table>
									<tr>
										<td>1</td>
									</tr>
									<tr>
										<td>2</td>
									</tr>
									<tr>
										<td>3</td>
									</tr>
									<tr>
										<td>4</td>
									</tr>
									<tr>
										<td>5</td>
									</tr>
									<tr>
										<td>6</td>
									</tr>
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
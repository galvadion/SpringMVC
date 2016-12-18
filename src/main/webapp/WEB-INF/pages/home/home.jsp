<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard" style="margin-top: -20px;">

		<div class="row">
			<div class="col-md-12">
				<section class="tile text-center slyder">
					
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
					  <!-- Indicators -->
					  <ol class="carousel-indicators">
					    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					    <li data-target="#myCarousel" data-slide-to="1"></li>
					    <li data-target="#myCarousel" data-slide-to="2"></li>
					  </ol>
					
					  <!-- Wrapper for slides -->
					  <div class="carousel-inner" role="listbox">
					    <div class="item active">
					      <img src="static/images/slyder2.jpg" alt="">
					    </div>
					
					    <div class="item">
					      <img src="static/images/slyder1.jpg" alt="">
					    </div>
					
					    <div class="item">
					      <img src="static/images/slyder3.jpg" alt="">
					    </div>
					  </div>
					
					  <!-- Left and right controls -->
					  <a class="left carousel-control" data-target="#myCarousel" role="button" data-slide="prev"  ng-non-bindable>
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					    <span class="sr-only">Previous</span>
					  </a>
					  <a class="right carousel-control" data-target="#myCarousel" role="button" data-slide="next"  ng-non-bindable>
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					    <span class="sr-only">Next</span>
					  </a>
					</div>

				</section>
			</div>
		</div>

		<div class="row" ng-if="vm.roladmin">
			<div class="col-md-12">
				<section class="tile">
					<!-- tile header -->
					<div class="tile-header dvd dvd-btm">
						<h1 class="custom-font">
							<strong>Panel </strong> de gestión (accesos rápidos)
							<button type="button" class="btn" data-toggle="collapse" data-target="#gestion"><span class="glyphicon glyphicon-chevron-down"></span></button>
						</h1>
					</div>
					<!-- /tile header -->

					<!-- tile body -->
					<div class="tile-body table-custom collapse in" style="padding: 15px; margin-left: 15px;" id="gestion">
					
						<div class="row">
							<div class="col-sm-4">
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/brand"><h4><i class="fa fa-list"></i> Listado de marcas</h4></a>
								</div>
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/brand"><h4><i class="fa fa-arrows"> Crear marca</i></h4></a>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/model"><h4><i class="fa fa-list"></i> Listado de modelos</h4></a>
								</div>
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/model/create"><h4><i class="fa fa-bus"></i> Crear modelo</h4></a>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/vehicle"><h4><i class="fa fa-list"></i> Listado de vehículos</h4></a>
								</div>
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/vehicle/create"><h4><i class="fa fa-car"></i> Crear vehículo</h4></a>
								</div>
							</div>
						</div>
						
						<hr class="line-dashed line-full"/>
						
						<div class="row">
							<div class="col-sm-4">
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/branchoffice"><h4><i class="fa fa-list"></i> Listado de sucursales</h4></a>
								</div>
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/branchoffice/create"><h4><i class="fa fa-building-o"></i> Crear sucursal</h4></a>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/employee"><h4><i class="fa fa-list"></i> Listado de empleados</h4></a>
								</div>
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/employee/create"><h4><i class="fa fa-male"></i> Crear empleado</h4></a>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/client"><h4><i class="fa fa-list"></i> Listado de clientes</h4></a>
								</div>
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/client/create"><h4><i class="fa fa-user"></i> Crear cliente</h4></a>
								</div>
							</div>
						</div>
						
						<hr class="line-dashed line-full"/>
						
						<div class="row">
							<div class="col-sm-4">
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/tariff"><h4><i class="fa fa-list"></i> Listado de tarifas</h4></a>
								</div>
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/tariff"><h4><i class="fa fa-money"></i> Crear tarifa</h4></a>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/promotion"><h4><i class="fa fa-list"></i> Listado de promociones</h4></a>
								</div>
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/promotion/create"><h4><i class="fa fa-hashtag"></i> Crear promoción</h4></a>
								</div>
							</div>
							
							<div class="col-sm-4">
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/booked"><h4><i class="fa fa-list"></i> Listado de reservas</h4></a>
								</div>
								<div class="row">
									<a class="" style="cursor: pointer; text-decoration: none; color: #616F77;" href="#/rent"><h4><i class="fa fa-list"></i> Listado de alquileres</h4></a>
								</div>
							</div>
						</div>
						
					</div>
				</section>
			</div>
		</div>

		<div class="row" ng-if="!vm.roladmin">
			<div class="col-md-6">
				<section class="tile home-tile scroll-tile" ng-if="vm.rolemployee">
					<br/>
					<h3 class="text-center">Alquileres para hoy</h3>
					<br/>
					Aca va un listado con los alquileres de hoy
				</section>
				
				<section class="tile home-tile" ng-if="!vm.rolemployee">
					<br/>
					<h3 class="text-center">Encuentra tu vehículo</h3>
					<br/>
					<form name='form' class="" role="form" ng-submit="searchModels()">

						<div class="row" style="padding: 0 10px 0 10px;">

							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="beginDate" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label for="beginDate" class="control-label">Fecha de recogida</label>
									<input class="form-control" size="16" type="text" name="beginDate" placeholder="DD/MM/YYYY" ng-model="vm.search.beginDate" ng-change="checkEndDate()" readonly required>
									<span class="input-group-addon customAddonSize">
									<span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>

							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeOriginId.$dirty && form.officeOriginId.$error.required }">
								<label for="officeOriginId" class="control-label">Sucursal de recogida</label>
								<select ng-model="vm.search.officeOriginId" name="officeOriginId" id="officeOriginId" class="form-control" required>
									<option value="">Seleccione sucursal</option>
									<option ng-repeat="(key, value) in vm.allOffices" value="{{value.id}}">{{value.name}} - {{value.city}}</option>
								</select>
								<span ng-show="form.officeOriginId.$dirty && form.officeOriginId.$error.required" class="help-block">Sucursal es requerido</span>
							</div>
						</div>
						<div class="row" style="padding: 0 10px 0 10px;">

							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="endDate" data-date="" data-date-format="dd/mm/yyyy" data-link-field="dtp_input2" data-link-format="dd/mm/yyyy">
									<label for="endDate" class="control-label">Fecha de entrega</label>
									<input class="form-control" size="16" type="text" name="endDate" placeholder="DD/MM/YYYY" ng-model="vm.search.endDate" ng-change="checkEndDate()" readonly required>
									<span class="input-group-addon customAddonSize">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
								<span ng-show="endDateError" class="help-block" style="color: red">Fecha de entrega debe ser posterior a la de recogida</span>
							</div>
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeEndId.$dirty && form.officeEndId.$error.required }">
								<label for="officeEndId" class="control-label">Sucursal de entrega</label>
									<select ng-model="vm.search.officeEndId" name="officeEndId" id="officeEndId" class="form-control" required>
										<option value="">Seleccione sucursal</option>
										<option ng-repeat="(key, value) in vm.allOffices" value="{{value.id}}">{{value.name}} - {{value.city}}</option>
									</select>
								<span ng-show="form.officeEndId.$dirty && form.officeEndId.$error.required" class="help-block">Sucursal es requerido</span>
							</div>
						</div>

						<br /><br />

						<!-- Buttons -->
						<div class="form-group text-right" style="margin-right: 15px;">
							<button type="submit" id="submit" ng-disabled="form.$invalid || endDateError" class="btn  btn-orange">Buscar ahora</button>
						</div>
					</form>

				</section>
			</div>

			<div class="col-md-6" ng-if="!vm.roladmin">
				<section class="tile text-center">
					<ui-gmap-google-map center="map.center" zoom="map.zoom" draggable="true" options="options"> 
						<ui-gmap-marker ng-repeat="m in map.markers" coords="m.coords" icon="m.icon" idkey="m.id" title="m.title" click="onClick">
							<ui-gmap-window show="show">
							<div>{{m.title}}</div>
							</ui-gmap-window>
						</ui-gmap-marker>
					</ui-gmap-google-map>
				</section>
			</div>
		</div>

		<br />

		<!--div class="row">
			<div class="col-md-12">

				<section class="tile">

					<div class="tile-header dvd dvd-btm">
						<h1 class="custom-font">
							<strong>Listado </strong>de promociones
						</h1>
					</div>

					<div class="tile-body table-custom">

						<div class="table-responsive">

							<table class="table mb-0 table-custom" id="promoList">
								<tbody>
									<tr ng-repeat="(key, value) in vm.allPromos">
										<td>{{value.name}}</td>
										<td>{{value.picture}}</td>
										<td>{{value.fuel.fuelType}}</td>
										<td>{{value.year}}</td>
										<td>{{value.passangers}}</td>
										<td>{{value.luggage}}</td>
										<td>{{value.cylinders}}</td>
										<td>{{value.airConditioner}}</td>
									</tr>
								</tbody>
							</table>
						</div>

					</div>

				</section>
			</div>
		</div>
		
		<br/-->

		<div class="row" ng-if="chat && vm.rolemployee">
			<div class="col-md-12">

				<section class="tile">

					<!-- tile header -->
					<div class="tile-header dvd dvd-btm">
						<h1 class="custom-font">
							<strong>Empleados </strong>en línea
						</h1>
					</div>
					<!-- /tile header -->

					<!-- tile body -->
					<div class="tile-body table-custom">
						<div id="members"></div>
					</div>

				</section>
			</div>
		</div>

	</div>

	<a id="chatBtn" href="" ng-click="initChat()" ng-disabled="chat" ng-if="!vm.roladmin" class="btn  btn-primary" style="position: fixed; bottom: 0; right: 0; z-index: 100; margin-right: 10px;">
		<p ng-if="!chat">Iniciar chat</p>
		<p ng-if="chat">Chat iniciado..</p>
	</a>
</section>
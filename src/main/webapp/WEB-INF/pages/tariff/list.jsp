<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Tarifas </strong>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Inicio</a></li>
				<li class="breadcrumb-active">Tarifas</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">
				<section class="tile">
					<div class="container col-md-12">
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion"
											data-target="#collapse1">Categorias</a>
									</h4>
								</div>
								<div id="collapse1" class="panel-collapse collapse in">
									<div class="panel-body">
										<section class="tile">

											<!-- tile header -->
											<div class="tile-header dvd dvd-btm">
												<h2 class="custom-font">
													<strong>Agregar</strong>
												</h2>
											</div>
											<!-- /tile header -->

											<!-- tile body -->
											<div class="tile-body">

												<form form-on-change="checkFields()" name='form-cat'
													class="" role="form" ng-submit="saveCategory()">

													<div class="row">
														<div class="form-group col-sm-6" class="form-group"
															ng-class="{ 'has-error': form-cat.name.$dirty && form-cat.name.$error.required }">
															<label for="name" class="control-label">*
																Nomenclacion de categoria</label> <input type="text"
																ng-model="vm.category.name" name="catName" id="catName"
																class="form-control"
																placeholder="Nomenclacion de la categoria" required>
															<span
																ng-show="form-cat.name.$dirty && form-cat.name.$error.required"
																class="help-block">Es necesario nombrar la
																categoria</span>
														</div>
														<div class="form-group col-sm-6" class="form-group"
															ng-class="{ 'has-error': form-cat.basePrice.$dirty && form-cat.basePrice.$error.required }">
															<label for="basePrice" class="control-label">*
																Precio base por dia</label> <input type="number"
																ng-model="vm.category.basePrice" name="basePrice"
																id="basePrice" class="form-control"
																placeholder="Precio base por dia" required> <span
																ng-show="form-cat.basePrice.$dirty && form-cat.basePrice.$error.required"
																class="help-block">Es necesario ingresar el
																precio base</span>
														</div>
													</div>
													<!-- Buttons -->
													<div class="form-group text-right">
														<a class="btn btn-lightred"
															ng-click="cleanInput();cleanMessagges();">Cancelar</a>
														<button type="submit" id="submit"
															ng-disabled="form.$invalid" class="btn  btn-orange">Crear</button>
													</div>
												</form>
											</div>
										</section>
										<section class="tile">
											<!-- tile header -->
											<div class="tile-header dvd dvd-btm">
												<h2 class="custom-font">
													<strong>Listado</strong>
												</h2>
											</div>
											<!-- /tile header -->
											<!-- tile body -->
											<div class="tile-body table-custom p-0">
												<div class="table-responsive">
													<table datatable="ng" class="table mb-0 table-custom"
														id="CategoryList" dt-options="vm.dtOptions"
														dt-column-defs="vm.DTColumnDefs">
														<thead>
															<tr>
																<th>Nombre</th>
																<th>Precio por dia</th>
																<th style="width: 20px;"></th>
																<th style="width: 90px;">Acciones</th>
																<th style="width: 70px;"></th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="(key, value) in vm.categoryList">
																<td>{{value.name}}</td>
																<td>{{value.basePrice}}</td>
																<td class="text-center plus" style="cursor: pointer;">
																	<a title="Editar" ng-click="editCategory(value)"> <i
																		class="fa fa-pencil"></i><br> <small>Editar</small>
																</a>
																</td>
																<td class="text-center delete"><a doing-action=""
																	href data-toggle="" ng-click="deleteCategory(value.id)"
																	confirm-if="checked"
																	confirm="Esta seguro, eliminar categoria {{value.name}} ?"
																	title="Eliminar"> <i class="fa fa-times"></i><br />
																		<small>Eliminar</small>
																</a></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>

										</section>
									</div>
								</div>
							</div>
						
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										data-target="#collapse2">Tipos de combustible</a>
								</h4>
							</div>
							<div id="collapse2" class="panel-collapse collapse">
								<div class="panel-body">
										<section class="tile">

											<!-- tile header -->
											<div class="tile-header dvd dvd-btm">
												<h2 class="custom-font">
													<strong>Agregar</strong>
												</h2>
											</div>
											<!-- /tile header -->

											<!-- tile body -->
											<div class="tile-body">

												<form form-on-change="checkFields()" name='form-fuel'
													class="" role="form" ng-submit="saveFuelType()">

													<div class="row">
														<div class="form-group col-sm-6" class="form-group"
															ng-class="{ 'has-error': form-fuel.fuelType.$dirty && form-fuel.fuelType.$error.required }">
															<label for="name" class="control-label">*
																Nombre del tipo de combustible</label> <input type="text"
																ng-model="vm.fuelType.fuelType" name="fuelType" id="fuelType"
																class="form-control"
																placeholder="Nombre del tipo del combustible" required>
															<span
																ng-show="form-fuel.fuelType.$dirty && form-fuel.fuelType.$error.required"
																class="help-block">Es necesario nombrar el tipo de combustible</span>
														</div>
														<div class="form-group col-sm-6" class="form-group"
															ng-class="{ 'has-error': form-fuel.fuelPrice.$dirty && form-fuel.fuelPrice.$error.required }">
															<label for="fuelPrice" class="control-label">*
																Precio base por dia</label> <input type="number"
																ng-model="vm.fuelType.fuelPrice" name="fuelPrice"
																id="basePrice" class="form-control"
																placeholder="Precio base por dia" required> <span
																ng-show="form-fuel.fuelPrice.$dirty && form-fuel.fuelPrice.$error.required"
																class="help-block">Es necesario ingresar el
																precio base</span>
														</div>
													</div>
													<!-- Buttons -->
													<div class="form-group text-right">
														<a class="btn btn-lightred"
															ng-click="cleanInput();cleanMessagges();">Cancelar</a>
														<button type="submit" id="submit"
															ng-disabled="form.$invalid" class="btn  btn-orange">Crear</button>
													</div>
												</form>
											</div>
										</section>
										<section class="tile">
											<!-- tile header -->
											<div class="tile-header dvd dvd-btm">
												<h2 class="custom-font">
													<strong>Listado</strong>
												</h2>
											</div>
											<!-- /tile header -->
											<!-- tile body -->
											<div class="tile-body table-custom p-0">
												<div class="table-responsive">
													<table datatable="ng" class="table mb-0 table-custom"
														id="FuelList" dt-options="vm.dtOptions"
														dt-column-defs="vm.DTColumnDefs">
														<thead>
															<tr>
																<th>Nombre</th>
																<th>Precio por dia</th>
																<th style="width: 20px;"></th>
																<th style="width: 90px;">Acciones</th>
																<th style="width: 70px;"></th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="(key, value) in vm.fuelTypeList">
																<td>{{value.fuelType}}</td>
																<td>{{value.fuelPrice}}</td>
																<td class="text-center plus" style="cursor: pointer;">
																	<a title="Editar" ng-click="editFuelType(value)"> <i
																		class="fa fa-pencil"></i><br> <small>Editar</small>
																</a>
																</td>
																<td class="text-center delete"><a doing-action=""
																	href data-toggle="" ng-click="deleteFuelType(value.id)"
																	confirm-if="checked"
																	confirm="Esta seguro, eliminar categoria {{value.name}} ?"
																	title="Eliminar"> <i class="fa fa-times"></i><br />
																		<small>Eliminar</small>
																</a></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>

										</section>
									</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										data-target="#collapse3">Extras</a>
								</h4>
							</div>
							<div id="collapse3" class="panel-collapse collapse">
								<div class="panel-body">
										<section class="tile">

											<!-- tile header -->
											<div class="tile-header dvd dvd-btm">
												<h2 class="custom-font">
													<strong>Agregar</strong>
												</h2>
											</div>
											<!-- /tile header -->

											<!-- tile body -->
											<div class="tile-body">

												<form form-on-change="checkFields()" name='form-extra'
													class="" role="form" ng-submit="saveExtra()">

													<div class="row">
														<div class="form-group col-sm-6" class="form-group"
															ng-class="{ 'has-error': form-extra.extra.$dirty && form-extra.extra.$error.required }">
															<label for="name" class="control-label">*
																Nombre del extra</label> <input type="text"
																ng-model="vm.extra.name" name="name" id="name"
																class="form-control"
																placeholder="Nombre del extra" required>
															<span
																ng-show="form-extra.extra.$dirty && form-extra.extra.$error.required"
																class="help-block">Es necesario nombrar el Extra</span>
														</div>
														<div class="form-group col-sm-6" class="form-group"
															ng-class="{ 'has-error': form-extra.price.$dirty && form-extra.price.$error.required }">
															<label for="price" class="control-label">*
																Precio base por dia</label> <input type="number"
																ng-model="vm.extra.price" name="price"
																id="price" class="form-control"
																placeholder="Precio base por dia" required> <span
																ng-show="form-extra.price.$dirty && form-extra.price.$error.required"
																class="help-block">Es necesario ingresar el
																precio base</span>
														</div>
													</div>
													<!-- Buttons -->
													<div class="form-group text-right">
														<a class="btn btn-lightred"
															ng-click="cleanInput();cleanMessagges();">Cancelar</a>
														<button type="submit" id="submit"
															ng-disabled="form.$invalid" class="btn  btn-orange">Crear</button>
													</div>
												</form>
											</div>
										</section>
										<section class="tile">
											<!-- tile header -->
											<div class="tile-header dvd dvd-btm">
												<h2 class="custom-font">
													<strong>Listado</strong>
												</h2>
											</div>
											<!-- /tile header -->
											<!-- tile body -->
											<div class="tile-body table-custom p-0">
												<div class="table-responsive">
													<table datatable="ng" class="table mb-0 table-custom"
														id="ExtrasList" dt-options="vm.dtOptions"
														dt-column-defs="vm.DTColumnDefs">
														<thead>
															<tr>
																<th>Nombre</th>
																<th>Precio por dia</th>
																<th style="width: 20px;"></th>
																<th style="width: 90px;">Acciones</th>
																<th style="width: 70px;"></th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="(key, value) in vm.extrasList">
																<td>{{value.name}}</td>
																<td>{{value.price}}</td>
																<td class="text-center plus" style="cursor: pointer;">
																	<a title="Editar" ng-click="editExtra(value)"> <i
																		class="fa fa-pencil"></i><br> <small>Editar</small>
																</a>
																</td>
																<td class="text-center delete"><a doing-action=""
																	href data-toggle="" ng-click="deleteExtra(value.id)"
																	confirm-if="checked"
																	confirm="Esta seguro, eliminar categoria {{value.name}} ?"
																	title="Eliminar"> <i class="fa fa-times"></i><br />
																		<small>Eliminar</small>
																</a></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>

										</section>
									</div>
							</div>
						</div>
					</div>
					</div>
				</section>
			</div>
		</div>
	</div>


	<div class="row">
		<div class="col-md-12">


			<!-- /row -->
		</div>

		<div class="row">
			<div class="col-md-12"></div>
			<!-- /row -->
		</div>

	</div>

</section>
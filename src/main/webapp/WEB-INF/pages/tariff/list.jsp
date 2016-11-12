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
																<th style="width: 20px;">Precio por dia</th>
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
															ng-class="{ 'has-error': form-fuel.name.$dirty && form-fuel.name.$error.required }">
															<label for="name" class="control-label">*
																Nomenclacion de categoria</label> <input type="text"
																ng-model="vm.category.name" name="catName" id="fuelName"
																class="form-control"
																placeholder="Nomenclacion de la categoria" required>
															<span
																ng-show="form-fuel.name.$dirty && form-fuel.name.$error.required"
																class="help-block">Es necesario nombrar la
																categoria</span>
														</div>
														<div class="form-group col-sm-6" class="form-group"
															ng-class="{ 'has-error': form-fuel.basePrice.$dirty && form-fuel.basePrice.$error.required }">
															<label for="basePrice" class="control-label">*
																Precio base por dia</label> <input type="number"
																ng-model="vm.category.basePrice" name="basePrice"
																id="basePrice" class="form-control"
																placeholder="Precio base por dia" required> <span
																ng-show="form-fuel.basePrice.$dirty && form-fuel.basePrice.$error.required"
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
																<th style="width: 20px;">Precio por dia</th>
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
										data-target="#collapse3">Collapsible Group 3</a>
								</h4>
							</div>
							<div id="collapse3" class="panel-collapse collapse">
								<div class="panel-body">Lorem ipsum dolor sit amet,
									consectetur adipisicing elit, sed do eiusmod tempor incididunt
									ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
									nostrud exercitation ullamco laboris nisi ut aliquip ex ea
									commodo consequat.</div>
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
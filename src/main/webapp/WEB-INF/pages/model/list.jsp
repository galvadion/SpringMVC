<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Modelos </strong> Listado
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Inicio</a></li>
				<li class="breadcrumb-active">Modelos</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">

				<section class="tile" ng-show="vm.roladmin">

					<!-- tile header -->
					<div class="tile-header dvd dvd-btm">
						<h2 class="custom-font">
							<strong>Gestion:</strong>
						</h2>
						<a href="#/model/create"
							class="btn btn-orange btn-rounded mb-10 right"
							style="margin: 0 2px 0 2px;">Nuevo Modelo</a>
					</div>
					<!-- /tile header -->

					<!-- tile body -->
					<div class="tile-body table-custom p-0">
						<div class="table-responsive">

							<table datatable="ng" class="table mb-0 table-custom"
								id="ModelList" dt-options="vm.dtOptions"
								dt-column-defs="vm.DTColumnDefs">
								<thead>
									<tr>
										<th>Nombre</th>
										<th>Marca</th>
										<th>Categoria</th>
										<th>A�o</th>
										<th>Combustible</th>
										<th>Pasajeros</th>
										<th>Valijas</th>
										<th>Cilindrada</th>
										<th>Aire acondicionado</th>
										<th>Transmisi�n</th>
										<th>Seguro</th>
										<th>Tanque</th>
										<th style="width: 20px;"></th>
										<th style="width: 90px;">Acciones</th>
										<th style="width: 70px;"></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="(key, value) in vm.allModels">
										<td>{{value.name}}</td>
										<td>{{value.brand.name}}</td>
										<td>{{value.category.name}}</td>
										<td>{{value.year}}</td>
										<td>{{value.fuel.fuelType}}</td>
										<td>{{value.passangers}}</td>
										<td>{{value.luggage}}</td>
										<td>{{value.cylinders}}</td>
										<td>
											<p ng-if="value.airConditioner">Tiene</p>
											<p ng-if="!value.airConditioner">No tiene</p>
										</td>
										<td>
											<p ng-if="value.transmission == 'M'">Manual</p>
											<p ng-if="value.transmission == 'A'">Autom�tico</p>
										</td>
										<td>$ {{value.insurance}}</td>
										<td>{{value.fullTank}} lts.</td>
										<td class="text-center plus"></td>
										<td class="text-center plus"><a
											ng-href="#/model/edit/{{value.id}}" title="Edit"> <i
												class="fa fa-pencil"></i><br> <small>Editar</small>
										</a></td>
										<td class="text-center delete"><a doing-action="" href
											ng-click="openDialog(value)" title="Eliminar"> <i
												class="fa fa-times"></i><br /> <small>Eliminar</small>
										</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

				</section>
				<section class="tile" ng-show="!vm.roladmin">
					<div class="tile-header dvd dvd-btm">
						<div class="row" ng-repeat="(key, value) in vm.allModels">
							<div class="text-center">
								<h1 style="font-size: 25px"><b>{{value.brand.name}} {{value.name}} {{value.year}}</b></h1>
							</div>
							<br/>
							<div class="row" style="padding: 20px;">

								<div class="col-sm-4 text-center">
									<img ng-if="!value.images[0]" src="static/images/noimage.png" style="height: 150px; max-width: 240px;" class="text-center">
									<img ng-if="value.images[0]" src="images/{{value.images[0].fileLocation}}" style="height: 150px; max-width: 240px; margin: auto;" class="text-center">
								</div>
								<div class="col-sm-3">
									<table style="margin: auto; font-size: 13px">
										<tr>
											<td>
												<p>Categoria: {{value.category.name}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Combustible: {{value.fuel.fuelType}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Cilindradas: {{value.cylinders}}</p>
											</td>
										</tr>
										<tr>
											<td>
												<p>Pasajeros: {{value.passangers}}</p>
											</td>
										</tr>
									</table>
								</div>
								<div class="col-sm-3">
									<table style="margin: auto; font-size: 13px">
										<tr>
											<td>
												<p>Valijas: {{value.luggage}}</p>
											</td>
										</tr>
										<tr>
											<td>
												Aire acondicionado:
												<p ng-if="value.airConditioner">Tiene</p>
												<p ng-if="!value.airConditioner">No tiene</p>
												

											</td>
										</tr>
										<tr>
											<td>
												Transimisi�n:
												<p ng-if="value.transmission == 'M'">Manual</p>
												<p ng-if="value.transmission == 'A'">Autom�tica</p>
												

											</td>
										</tr>
									</table>
								</div>
								<div class="col-sm-2 text-center">
									<a href="#/model/properties/{{value.id}}" class="btn  btn-orange" style="margin-top: 20px; height: 30px; border-radius: 3px; border-color: #FFFFF"><b>Ver detalles</b></a>
								</div>
							</div>
							<div class="row modelDesc">
								<p style="margin-left: 10px; font-size: 15px"><b>Descripci�n:</b> {{value.description}}</p>
							</div>
							
							<hr class="line-dashed line-full"/>
							<br/>
						</div>
					</div>
				</section>
			</div>
			<!-- /row -->
		</div>

	</div>

</section>

<script type="text/ng-template" id="modalDialog">
	<div class="ngdialog-message modal-content">
	<div class="modal-header">
	<h3 class="modal-title custom-font">Eliminar</h3>
	</div>
	<div class="modal-body">
	Esta seguro, eliminar <strong>{{vm.auxModel.name}}</strong>?
	</div>                     
	<div class="modal-footer  ngdialog-buttons">
	<button type="button" class="ngdialog-button btn btn-lightred btn-ef btn-ef-4 btn-ef-4c" ng-click="closeThisDialog('button')"><i class="fa fa-arrow-left"></i>Cancelar</button>
	<button type="button" class="ngdialog-button btn btn-success btn-ef btn-ef-3 btn-ef-3c" ng-click="confirm();deleteModel(vm.auxModel.id)"><i class="fa fa-arrow-right"></i> Confirmar</button>
	</div>
	</div>
</script>
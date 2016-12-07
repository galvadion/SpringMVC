<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Ofertas </strong> Listado
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Inicio</a></li>
				<li class="breadcrumb-active">Ofertas</li>
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
						<a href="#/promotion/create"
							class="btn btn-orange btn-rounded mb-10 right"
							style="margin: 0 2px 0 2px;">Nueva Oferta</a>
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
										<th>Codigo</th>
										<th>Fecha inicio</th>
										<th>Fecha fin</th>
										<th>Porcentaje</th>
										<th style="width: 20px;"></th>
										<th style="width: 90px;">Acciones</th>
										<th style="width: 70px;"></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="(key, value) in vm.allPromotions">
										<td>{{value.promotionCode}}</td>
										<td>{{value.beginPromotionDate}}</td>
										<td>{{value.lastPromotionDate}}</td>
										<td>{{value.porcentage}}</td>
										
										<td class="text-center plus"></td>
										<td class="text-center plus"><a
											ng-href="#/pro/edit/{{value.id}}" title="Edit"> <i
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
								<h2>{{value.brand.name}} {{value.name}} {{value.year}}</h2>
							</div>
							<div class="row" style="padding: 15px;">
							<div class="col-sm-4">
								<div class="col-sm-4" ng-repeat="(keyI, valueI) in value.images">
									<img src="images/{{valueI.fileLocation}}"
										style="height: 150px; max-width: 240px;">
								</div>
								</div>
								<div class="col-sm-4">
									<table>
										<tr>
											<td>
												<p>Segmento: {{value.category.name}}</p>
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
								<div class="col-sm-4">
									<table>
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
												Transimision:
												<p ng-if="value.transmission == 'M'">Manual</p>
												<p ng-if="value.transmission == 'A'">Automática</p>
												

											</td>
										</tr>
									</table>
								</div>
							</div>
							<div class="row" style="padding: 15px;">
								{{value.description}}</div>
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
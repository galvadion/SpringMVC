<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Promociones </strong> Listado
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Inicio</a></li>
				<li class="breadcrumb-active">Promociones</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">

				<section class="tile">

					<!-- tile header -->
					<div class="tile-header dvd dvd-btm">
						<h2 class="custom-font">
							<strong>Gestion:</strong>
						</h2>
						<a href="#/promotion/create"
							class="btn btn-orange btn-rounded mb-10 right"
							style="margin: 0 2px 0 2px;">Nueva Promoción</a>
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
										<td>{{value.percentage}}</td>
										
										<td class="text-center plus"></td>
										<td class="text-center plus"><a
											ng-href="#/promotion/edit/{{value.id}}" title="Edit"> <i
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
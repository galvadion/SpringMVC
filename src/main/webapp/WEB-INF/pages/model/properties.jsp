<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Propiedades </strong> de Modelo
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Inicio</a></li>
				<li><a href="#/model">Modelos</a></li>
				<li class="breadcrumb-active">Propiedades</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">

				<!--section class="tile"-->

					<!-- tile body -->
					<div class="tile-body">

						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<div class="row"><center><h3 class="custom-font">{{vm.requestModel.brand.name}} {{vm.requestModel.name}}</h3></center></div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-8">
											<img class="img-responsive" ng-if="!vm.requestModel.images[0]" src="static/images/noimage.png" alt="car-picture" style="max-width: 70%; margin: auto;">
											<img class="img-responsive" ng-if="vm.requestModel.images[0]" src="images/{{vm.requestModel.images[0].fileLocation}}" alt="car-picture" style="max-width: 70%; margin: auto;">
											<br/>
										</div>
										<div class="col-md-4" style="">
											<div class="row">
												<label>Categoría: {{vm.requestModel.category.name}}</label>
											</div>
											<div class="row">
												<label>Año: {{vm.requestModel.year}}</label>
											</div>
											<div class="row">
												<label>Pasajeros: {{vm.requestModel.passangers}}</label>
											</div>
											<div class="row">
												<label>Maletas: {{vm.requestModel.luggage}}</label>
											</div>
											<div class="row">
												<label>Transmisión: {{vm.requestModel.transmission}}</label>
											</div>
											<div class="row">
												<label>Cilindradas: {{vm.requestModel.cylinders}}</label>
											</div>
											<div class="row">
												<label>Combustible: {{vm.requestModel.fuel.fuelType}}</label>
											</div>
											<div class="row">
												<label>Precio por día: $<strong>{{vm.requestModel.category.basePrice}}</strong></label>
											</div>
										</div>
									</div>
									<br/><br/>
									<div class="row">
										<div class="col-md-8">
											<img class="img-responsive" ng-if="vm.requestModel.images[1]" src="images/{{vm.requestModel.images[1].fileLocation}}" alt="car-picture" style="max-width: 70%; margin: auto;">
										</div>
									</div>
									<br/>
									<div class="row">
										<div class="col-md-8">
											<img class="img-responsive" ng-if="vm.requestModel.images[2]" src="images/{{vm.requestModel.images[2].fileLocation}}" alt="car-picture" style="max-width: 70%; margin: auto;">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
				<!--/section-->
			</div>
		</div>
	</div>
</section>
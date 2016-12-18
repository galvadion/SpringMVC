<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Detalles </strong> de Reserva
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Dashboard</a></li>
				<li><a href="#/booked">Reservas</a></li>
				<li class="breadcrumb-active">Detalles de reserva</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">

				<section class="tile">
					<!-- tile body -->
					<div class="tile-body">

						<div class="tile-header dvd dvd-btm">
	                        <h1 class="custom-font"><strong>Detalles generales</strong></h1>
	                        <button type="button" class="btn" data-toggle="collapse" data-target="#details1"><span class="glyphicon glyphicon-chevron-down"></span></button>
	                    </div>
						<div class="row collapse in" style="padding: 0 10px 0 10px;" id="details1">
							<br/>
							<div class="col-md-4" style="margin-left: 20px; font-size: 15px">
								<div class="row">
									<label><b>Número de transacción:</b> {{vm.booked.transactionNr}}</label>
								</div>
								<div class="row">
									<label><b>Fecha de transacción:</b> {{vm.booked.transactionDate}}</label>
								</div>
								<div class="row">
									<label><b>Monto inicial:</b> US$ {{vm.booked.initialAmount}}</label>
								</div>
								<div class="row">
									<label><b>Fecha de inicio:</b> {{vm.booked.beginbookedDate}}</label>
								</div>
								<div class="row">
									<label><b>Fecha de fin:</b> {{vm.booked.lastbookedDate}}</label>
								</div>
							</div>
							<div class="col-md-4" style="margin-left: 15px; font-size: 15px">
								<div class="row">
									<label><b>Código de promoción:</b> {{vm.booked.promotionCode_id}}</label>
								</div>
								<div class="row">
									<label>
										<b>Devuelto:</b>
										<i ng-if="vm.booked.returned" class="fa fa-check" style="color: green"> <p class="custom-font" style="display: inline;">Si</p></i>
                                        <i ng-if="!vm.booked.returned" class="fa fa-close" style="color: red"> <p class="custom-font" style="display: inline;">No</p></i>
									</label>
								</div>
								<div class="row">
									<label>
										<b>Cancelado:</b>
										<i ng-if="vm.booked.canceled" class="fa fa-check" style="color: green"> <p class="custom-font" style="display: inline;">Si</p></i>
                                        <i ng-if="!vm.booked.canceled" class="fa fa-close" style="color: red"> <p class="custom-font" style="display: inline;">No</p></i>
									</label>
								</div>
								<div class="row" ng-if="!vm.booked.canceled && !vm.booked.rent">
									<button class="btn btn-orange" ng-click="cancelBooking(vm.booked.id)">Cancelar reserva</button> <small class="custom-font" style="display: inline;"> (Se aplicaran cargos)</small>
								</div>
							</div>
						</div>
					</div>
				</section>
				
				<section class="tile">
					<!-- tile body -->
					<div class="tile-body">

						<div class="tile-header dvd dvd-btm">
	                        <h1 class="custom-font"><strong>Detalles del vehículo</strong></h1>
	                        <button type="button" class="btn" data-toggle="collapse" data-target="#details2"><span class="glyphicon glyphicon-chevron-down"></span></button>
	                    </div>
						<div class="row collapse" style="padding: 0 10px 0 10px;" id="details2">
							<br/>
							<div class="row" style="margin-left: 20px;">
								<div class="col-md-4" style="font-size: 15px">
									<div class="row">
										<label><b>Matrícula:</b> {{vm.booked.vehicle.licensePlate}}</label>
									</div>
									<div class="row">
										<label><b>Marca:</b> {{vm.booked.vehicle.model.brand.name}}</label>
									</div>
									<div class="row">
										<label><b>Modelo:</b> {{vm.booked.vehicle.model.name}}</label>
									</div>
								</div>
								<div class="col-md-4" style="font-size: 15px">
									<div class="row">
										<label><b>Seguro pago: </b>
											<p ng-if="vm.booked.withInsurance" class="custom-font" style="display: inline;">Si</p>
											<p ng-if="!vm.booked.withInsurance" class="custom-font" style="display: inline;">No</p>
										</label>
									</div>
									<div class="row">
										<label><b>Tanque lleno: </b>
											<p ng-if="vm.booked.withFullTank" class="custom-font" style="display: inline;">Si</p>
											<p ng-if="!vm.booked.withFullTank" class="custom-font" style="display: inline;">No</p>
										</label>
									</div>
									<div class="row">
										<label><b>Extras:</b> {{vm.booked.extrasList}}</label>
									</div>
								</div>
							</div>
							<div class="row" style="width: 90%; margin: 5px; margin-left: 20px;">
								<label><b>Descripción:</b> {{vm.booked.vehicle.model.description}}</label>
							</div>
						</div>
					</div>
				</section>
				
				<section class="tile">
					<!-- tile body -->
					<div class="tile-body">

						<div class="tile-header dvd dvd-btm">
	                        <h1 class="custom-font"><strong>Detalles de sucursales de retiro/entrega</strong></h1>
	                        <button type="button" class="btn" data-toggle="collapse" data-target="#details3"><span class="glyphicon glyphicon-chevron-down"></span></button>
	                    </div>
						<div class="row collapse" style="padding: 0 10px 0 10px;" id="details3">
							<br/>
							<div class="col-md-4" style="margin-left: 20px; font-size: 15px">
								<div class="row">
									<label><b>Oficina de origen:</b> {{vm.booked.originOffice.name}}</label>
								</div>
								<div class="row">
									<label><b>Dirección:</b> {{vm.booked.originOffice.address}}</label>
								</div>
								<div class="row">
									<label><b>Ciudad:</b> {{vm.booked.originOffice.city}}</label>
								</div>
								<div class="row">
									<label><b>Horario:</b> De {{vm.booked.originOffice.apertureHour}} hrs. a {{vm.booked.originOffice.closingHour}} hrs.</label>
								</div>
							</div>
							<div class="col-md-4" style="margin-left: 15px; font-size: 15px">
								<div class="row">
									<label><b>Oficina de destino:</b> {{vm.booked.endOffice.name}}</label>
								</div>
								<div class="row">
									<label><b>Dirección:</b> {{vm.booked.endOffice.address}}</label>
								</div>
								<div class="row">
									<label><b>Ciudad:</b> {{vm.booked.endOffice.city}}</label>
								</div>
								<div class="row">
									<label><b>Horario:</b> De {{vm.booked.endOffice.apertureHour}} hrs. a {{vm.booked.endOffice.closingHour}} hrs.</label>
								</div>
							</div>
						</div>
					</div>
				</section>
				
			</div>
			<!-- /row -->
		</div>

	</div>

</section>
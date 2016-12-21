<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Detalles </strong> de Alquiler
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Dashboard</a></li>
				<li><a href="#/booked">Alquiler</a></li>
				<li class="breadcrumb-active">Detalles de alquiler</li>
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
									<label><b>Número de transacción de reserva:</b> {{vm.book.transactionNr}}</label>
								</div>
								<div class="row">
									<label><b>Fecha de reserva:</b> {{vm.book.transactionDate}}</label>
								</div>
								<div class="row">
									<label><b>Monto inicial:</b> US$ {{vm.book.initialAmount}}</label>
								</div>
								<div class="row">
									<label><b>Fecha de inicio de la reserva:</b> {{vm.book.beginbookedDate}}</label>
								</div>
								<div class="row">
									<label><b>Fecha en que se retiro el vehiculo:</b> {{vm.rent.deliveryDate}}</label>
								</div>
							</div>
							<div class="col-md-4" style="margin-left: 15px; font-size: 15px">
								<div class="row">
									<label nf-show="vm.book.promotionCode_id"><b>Código de promoción:</b> {{vm.book.promotionCode_id}}</label>
								</div>
								<div class="row">
									<label><b>Fecha de fin de la reserva:</b> {{vm.book.lastbookedDate}}</label>
								</div>
								<div class="row">
									<label><b>Fecha en que se devuelve el vehiculo:</b> {{vm.rent.returnDate}}</label>
								</div>
								<div class="row">
									<label>
										<b>Devuelto:</b>
										<i ng-if="vm.book.returned" class="fa fa-check" style="color: green"> <p class="custom-font" style="display: inline;">Si</p></i>
                                        <i ng-if="!vm.book.returned" class="fa fa-close" style="color: red"> <p class="custom-font" style="display: inline;">No</p></i>
									</label>
								</div>
								
							</div>
						</div>
					</div>
				</section>
				
				<section class="tile">
					<!-- tile body -->
					<div class="tile-body">

						<div class="tile-header dvd dvd-btm">
	                        <h1 class="custom-font"><strong>Detalles extras</strong></h1>
	                        <button type="button" class="btn" data-toggle="collapse" data-target="#details2"><span class="glyphicon glyphicon-chevron-down"></span></button>
	                    </div>
						<div class="row collapse" style="padding: 0 10px 0 10px;" id="details2">
							<br/>
							<div class="row" style="margin-left: 20px;">
								<div class="col-md-4" style="font-size: 15px">
								<div class="row">
										<label><b>Conductores:</b></label>
									</div>
									<div ng-repeat="(key, value) in vm.rent.driversList">
									<div class="row">
										<label><b>Nombre:</b> {{value.driversName}}</label>
									</div>
									<div class="row">
										<label><b>Documento:</b> {{value.driversDocument}}</label>
									</div>
									<div class="row">
										<label><b>Fecha nacimiento:</b> {{value.birthDate}}</label>
									</div>
									<div class="row">
										<label><b>Vencimiento licencia:</b> {{value.licenseExpirationDate}}</label>
									</div>
									</div>
								</div>
								<div class="col-md-4" style="font-size: 15px">
								<div class="row">
									<label><b>Extras:</b></label>
								</div>
								<div ng-repeat="(key,value) in vm.rent.rentLine">
									<div class="row">
										<label><b>Detalle:</b>{{value.detail}}</label>
									</div>
									<div class="row">
										<label><b>Precio:</b>{{value.amount}}</label>
									</div>
								</div>
								</div>
								
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
									<label><b>Oficina de origen:</b> {{vm.book.originOffice.name}}</label>
								</div>
								<div class="row">
									<label><b>Dirección:</b> {{vm.book.originOffice.address}}</label>
								</div>
								<div class="row">
									<label><b>Ciudad:</b> {{vm.book.originOffice.city}}</label>
								</div>
								<div class="row">
									<label><b>Horario:</b> De {{vm.book.originOffice.apertureHour}} hrs. a {{vm.book.originOffice.closingHour}} hrs.</label>
								</div>
							</div>
							<div class="col-md-4" style="margin-left: 15px; font-size: 15px">
								<div class="row">
									<label><b>Oficina de destino:</b> {{vm.book.endOffice.name}}</label>
								</div>
								<div class="row">
									<label><b>Dirección:</b> {{vm.book.endOffice.address}}</label>
								</div>
								<div class="row">
									<label><b>Ciudad:</b> {{vm.book.endOffice.city}}</label>
								</div>
								<div class="row">
									<label><b>Horario:</b> De {{vm.book.endOffice.apertureHour}} hrs. a {{vm.book.endOffice.closingHour}} hrs.</label>
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
<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;">
				<strong>Reservas </strong> Detalles
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

						<div class="row vehicleDesc">
							<h2>Detalles del veh�culo</h2>
							
							<br/>
							<div class="col-md-4" style="margin-left: 20px; font-size: 15px">
								<div class="row">
									<label><b>Matr�cula:</b> {{vm.booked.vehicle.licensePlate}}</label>
								</div>
								<div class="row">
									<label><b>Sucursal actual:</b> {{vm.booked.branchOffice.name}}</label>
								</div>
								<div class="row">
									<label><b>Marca:</b> {{vm.booked.model.brand.name}}</label>
								</div>
								<div class="row">
									<label><b>Modelo:</b> {{vm.booked.model.name}}</label>
								</div>
								<div class="row">
									<label><b>Vencimiento de patente:</b> {{vm.booked.licensePlateExpirationDate}}</label>
								</div>
								<div class="row">
									<label><b>N�mero de motor:</b> {{vm.booked.motorNr}}</label>
								</div>
							</div>
							<div class="col-md-4" style="margin-left: 15px; font-size: 15px">
								<div class="row">
									<label><b>N�mero de chasis:</b> {{vm.booked.chasisNr}}</label>
								</div>
								<div class="row">
									<label><b>Kil�metros ya recorridos:</b> {{vm.booked.kilometers}}</label>
								</div>
								<div class="row">
									<label><b>Color:</b> {{vm.booked.color}}</label>
								</div>
								<div class="row">
									<label><b>Descripci�n:</b> {{vm.booked.model.description}}</label>
								</div>
								<div class="row">
									<label><b>Observaciones:</b> {{vm.booked.observations}}</label>
								</div>
							</div>
						</div>
						
						<br/>
						
						<div class="row vehicleDesc">
							<h2>Detalles generales</h2>
							
							<br/>
							<div class="col-md-4" style="margin-left: 20px; font-size: 15px">
								<div class="row">
									<label><b>Matr�cula:</b> {{vm.booked.vehicle.licensePlate}}</label>
								</div>
								<div class="row">
									<label><b>Sucursal actual:</b> {{vm.booked.branchOffice.name}}</label>
								</div>
								<div class="row">
									<label><b>Marca:</b> {{vm.booked.model.brand.name}}</label>
								</div>
								<div class="row">
									<label><b>Modelo:</b> {{vm.booked.model.name}}</label>
								</div>
								<div class="row">
									<label><b>Vencimiento de patente:</b> {{vm.booked.licensePlateExpirationDate}}</label>
								</div>
								<div class="row">
									<label><b>N�mero de motor:</b> {{vm.booked.motorNr}}</label>
								</div>
							</div>
							<div class="col-md-4" style="margin-left: 15px; font-size: 15px">
								<div class="row">
									<label><b>N�mero de chasis:</b> {{vm.booked.chasisNr}}</label>
								</div>
								<div class="row">
									<label><b>Kil�metros ya recorridos:</b> {{vm.booked.kilometers}}</label>
								</div>
								<div class="row">
									<label><b>Color:</b> {{vm.booked.color}}</label>
								</div>
								<div class="row">
									<label><b>Descripci�n:</b> {{vm.booked.model.description}}</label>
								</div>
								<div class="row">
									<label><b>Observaciones:</b> {{vm.booked.observations}}</label>
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
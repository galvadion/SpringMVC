<ng-include src="'blocks/header'"></ng-include>

<section id="content">
	<div class="container-fluid">
		<div ng-switch="vm.estado">
			<div ng-switch-when="booking" class="row-fluid">
				<!-- Panel de datos de vehiculo -->
				<div class="col-md-8 col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row"><center><h3>{{model.brand.name}} {{model.name}}</h3></center></div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-8">
									<!--<img class="img-responsive" src="{{vm.model.image[0]}}" alt="car-picture"> -->
									 <img class="img-responsive" src="https://www.bmw.co.cr/content/dam/bmw/common/all-models/i-series/i8/2014/model-card/BMW-i8_ModelCard.png" alt="car-picture">  
								</div>
								<div class="col-xs-4">
									<div class="row">
										<label>Category: {{model.category.name}}</label>
									</div>
									<div class="row">
										<label>Year: {{model.year}}</label>
									</div>
									<div class="row">
										<label>Passengers: {{model.passangers}}</label>
									</div>
									<div class="row">
										<label>Luggage: {{model.luggage}}</label>
									</div>
									<div class="row">
										<label>Transmission: {{model.transmission}}</label>
									</div>
									<div class="row">
										<label>Cylinders: {{model.cylinders}}</label>
									</div>
									<div class="row">
										<label>Fuel: {{model.fuel.fuelType}}</label>
									</div>
									<div class="row">
										<label>Price per day: $<strong>{{model.category.basePrice}}</strong></label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Panel de extras -->
				<div class="col-md-4 col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<center><h3>Extras</h3></center>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class = "col-xs-offset-1 col-xs-9">
									<label>GPS ($ {{gpsPrice}} per day)</label>
								</div>
								<div class="col-xs-2">
									<input type="checkbox" ng-model="vm.gpsCheck" ng-change="addItem(-1, 'GPS', vm.gpsPrice, vm.gpsCheck)" data-toggle="toggle">
								</div>
							</div>
							<div class="row">
								<div class = "col-xs-offset-1 col-xs-9">
									<label>Insurance ($ {{insurancePrice}} per day)</label>
								</div>
								<div class = "col-xs-2">
									<input type="checkbox" ng-model="vm.insuranceCheck" ng-change="addItem(-2, 'Insurance', {{insurancePrice}}, vm.insuranceCheck)" data-toggle="toggle">
								</div>
							</div>
							<div class="row">
								<div class = "col-xs-offset-1 col-xs-9">
									<label>Full tank ($ {{fulltankPrice}})</label>
								</div>
								<div class = "col-xs-2">
									<input type="checkbox" ng-model="vm.fulltankCheck" ng-change="addItem(-3, 'Full Tank', {{fulltankPrice}}, vm.fulltankCheck)" data-toggle="toggle">
								</div>
							</div>
	<!-- 						<div class="row"> -->
	<!-- 							<div class = "col-xs-offset-1 col-xs-9"> -->
	<!-- 								<label>Baby Car Safety Seats(0 to 9 month)</label> -->
	<!-- 							</div> -->
	<!-- 							<div class = "col-xs-2"> -->
	<!-- 								<input type="number" ng-model="vm.babySeat1" min="0" style="width:100%" value="0"> -->
	<!-- 							</div> -->
	<!-- 						</div> -->
	<!-- 						<div class="row"> -->
	<!-- 							<div class = "col-xs-offset-1 col-xs-9"> -->
	<!-- 								<label>Baby Car Safety Seats(9 month to 4 years)</label> -->
	<!-- 							</div> -->
	<!-- 							<div class = "col-xs-2"> -->
	<!-- 								<input type="number" ng-model="vm.babySeat2" min="0" style="width:100%" value="0"> -->
	<!-- 							</div> -->
	<!-- 						</div> -->
	<!-- 						<div class="row"> -->
	<!-- 							<div class = "col-xs-offset-1 col-xs-9"> -->
	<!-- 								<label>Baby Car Safety Seats(4 to 6 years)</label> -->
	<!-- 							</div> -->
	<!-- 							<div class = "col-xs-2"> -->
	<!-- 								<input type="number" ng-model="vm.babySeat3" min="0" style="width:100%" value="0"> -->
	<!-- 							</div> -->
	<!-- 						</div> -->
						</div>
					</div>
					
					<!-- Panel de costo de reserva y pago -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<center><h3>Booking price</h3></center>
						</div>
						<div class="panel-body">
							<div ng-repeat="item in cart.items">
								<div class="row">
									<div class="col-xs-offset-1 col-xs-5">{{item.name}}</div>
									<div class="col-xs-2">x {{item.quantity}}</div>
									<div class="col-xs-3">$ {{item.price * item.quantity}}</div>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<div class="row">
								<div class="col-xs-offset-1 col-xs-5">Total</div>
								<div class="col-xs-2">x {{cart.getTotalCount()}}</div>
								<div class="col-xs-3">$ {{cart.getTotalPrice()}}</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<center><h3>Pay with:</h3></center>
						</div>
						<div class="panel-body">
							<div class="col-xs-offset-5 col-xs-6">
								<button id="btnPaypalId"
				                    class="btn btn-block btn-link"
				                    ng-click="cart.checkout('PayPal')"
				                    ng-disabled="cart.getTotalCount() < 1" >
				                    <img src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" alt="checkout PayPal"/>
				                </button>
							</div>
						</div>
						<div class="panel-footer">
							<div class="btn btn-danger" ng-click="volver()">Volver</div>
						</div>
					</div>
				</div>
				
				
				
				</div>
			<div ng-switch-when="details" class="col-md-6 col-sm-12">
				<h1>Thank you!!!</h1>
				<h3>Please, review your booking before confirm</h3>
				
				<div ng-repeat="item in vm.items">
					<div class="row">
						<div class="col-sm-3">Name: </div>
						<div class="col-sm-3">{{item.name}}</div>
					</div>
					<div class="row">
						<div class="col-sm-3">Amount: </div>
						<div class="col-sm-3">{{item.amount}}</div>
					</div>
					<div class="row">
						<div class="col-sm-3">Quantity: </div>
						<div class="col-sm-3">{{item.quantity}}</div>
					</div>
					<br/>
				</div>
				<div>
					<div class="row">
						<div class="col-sm-3">Total: </div>
						<div class="col-sm-3">{{vm.itemTotal}}</div>
					</div>
					<br/>
				</div>
				<div>
					<div class="row">
						<div class="col-sm-2">
							<div class="btn btn-info" ng-click="ConfirmBooking()">Confirm</div>
						</div>
						<div class="col-sm-2">
							<div class="btn btn-danger" ng-click="CancelBooking()">Cancel</div>						
						</div>
					</div>
				</div>
			</div>
			<div ng-switch-default>
			</div>
		</div>
	</div>
	<input id="paymentDetails" type="button" hidden="true" ng-click="getDetails()"/>
</section>
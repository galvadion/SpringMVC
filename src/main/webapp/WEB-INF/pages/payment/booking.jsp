<ng-include src="'blocks/header'"></ng-include>

<section id="content" class="fullSection">
	<div class="container-fluid fullSection">
		<div ng-switch="vm.estado" class=fullSection>
		
			<div ng-switch-when="booking" class="row-fluid fullSection" id="paymentBookingSection">
				<!-- Panel de datos de vehiculo -->
				<div class="col-md-8 col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row"><center><h3>{{model.brand.name}} {{model.name}}</h3></center></div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-8">
									<img class="img-responsive" ng-if="!model.images[0]" src="static/images/noimage.png" alt="car-picture">
									<img class="img-responsive" ng-if="model.images[0]" src="images/{{model.images[0].fileLocation}}" alt="car-picture">
<!-- 									 <img class="img-responsive" src="https://www.bmw.co.cr/content/dam/bmw/common/all-models/i-series/i8/2014/model-card/BMW-i8_ModelCard.png" alt="car-picture">   -->
								</div>
								<div class="col-xs-4">
									<div class="row">
										<label>Categoría: {{model.category.name}}</label>
									</div>
									<div class="row">
										<label>Año: {{model.year}}</label>
									</div>
									<div class="row">
										<label>Pasajeros: {{model.passangers}}</label>
									</div>
									<div class="row">
										<label>Maletas: {{model.luggage}}</label>
									</div>
									<div class="row">
										<label>Transmisión: {{model.transmission}}</label>
									</div>
									<div class="row">
										<label>Cilindradas: {{model.cylinders}}</label>
									</div>
									<div class="row">
										<label>Combustible: {{model.fuel.fuelType}}</label>
									</div>
									<div class="row">
										<label>Precio por día: $<strong>{{model.category.basePrice}}</strong></label>
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
							<div ng-repeat="extra in extras">
								<div class="row">
									<div class = "col-xs-offset-1 col-xs-9">
										<label>{{extra.name}} ($ {{extra.price}} por dia)</label>
									</div>
									<div class="col-xs-2">
										<input type="checkbox" checklist-model="extra.checked" checklist-value="extra" ng-change="addItem((-1) * {{extra.id}}, '{{extra.name}}', {{extra.price}}, checked)" data-toggle="toggle">
									</div>
								</div>
							</div>
							<div class="row">
								<div class = "col-xs-offset-1 col-xs-9">
									<label>Seguro ($ {{insurancePrice}} por day)</label>
								</div>
								<div class = "col-xs-2">
									<input type="checkbox" ng-model="vm.insuranceCheck" ng-change="addItem('Insurance', 'Insurance', {{insurancePrice}}, vm.insuranceCheck)" data-toggle="toggle">
								</div>
							</div>
							<div class="row">
								<div class = "col-xs-offset-1 col-xs-9">
									<label>Devolución con tanque lleno ($ {{fulltankPrice}})</label>
								</div>
								<div class = "col-xs-2">
									<input type="checkbox" ng-model="vm.fulltankCheck" ng-change="addItem('FullTank', 'Full Tank', {{fulltankPrice}}, vm.fulltankCheck)" data-toggle="toggle">
								</div>
							</div>
						</div>
					</div>
					
					<!-- Panel de costo de reserva y pago -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<center><h3>Detalles de reserva</h3></center>
						</div>
						<div class="panel-body">
							<div ng-repeat="item in cart.items">
								<div class="row">
									<div class="col-xs-offset-1 col-xs-5">{{item.name}}</div>
									<div class="col-xs-2">x {{item.quantity}}</div>
									<div class="col-xs-3">$ {{item.price * item.quantity}}</div>
								</div>
							</div>
							<br><br>
							<div ng-if="vm.rolclient && !vm.isPromotion"  class="form-inline">
								<label for="name" class="col-xs-offset-1 col-xs-4">Codigo promocional</label>
                            	<input type="text" name="promoCode" id="promoCode" class="col-xs-3" ng-model="vm.promo.promotionCode" placeholder="">
								<div class="col-xs-3" style="margin-right: 15px;">
									<button ng-click="validatePromotion()" class="btn  btn-orange">Validar</button>
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
						<div class="panel-body">
							<p class="text-info" ng-show="vm.rolclient">
								<button id="btnPaypalId"
				                    class="btn btn-block btn-primary"
				                    ng-click="cart.checkout('PayPal')"
				                    ng-disabled="cart.getTotalCount() < 1" >
				                    <span class="glyphicon glyphicon-ok"></span> Reserva con Paypal 
				                </button>
							</p>
							<p class="text-info" ng-show="!vm.rolclient">
								<button id="btnLoginToPay" class="btn btn-block btn-success" ng-click="loginModal()"><span class="glyphicon glyphicon-user"></span> Inicie sesión para reservar</button>
							</p>
							<p class="text-info">
								<button class="btn btn-block btn-danger" ng-click="volver()"><i class="glyphicon glyphicon-chevron-left" /> Volver</button>
							</p>
						</div>
						<div class="panel-footer" ng-show="vm.rolclient">
							<button id="btnPaypalId"
				                    class="btn btn-block btn-link"
				                    ng-click="cart.checkout('PayPal')"
				                    ng-disabled="cart.getTotalCount() < 1" >
				                    <img src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" alt="checkout PayPal" />
				            </button>
						</div>
					</div>
				</div>
				
				
			</div>
			
			<div ng-switch-when="details" class="row-fluid  fullSection" id="paymentDetailsSection">
				<div class="col-md-offset-1 col-md-6 col-sm-12">
					<h1>Todo en orden</h1>
					<h3>Por favor, revisa los detalles de la reserva antes de confirmar</h3>
					
					<div ng-repeat="item in vm.items">
						<div class="row">
							<div class="col-sm-3">Nombre: </div>
							<div class="col-sm-3">{{item.name}}</div>
						</div>
						<div class="row">
							<div class="col-sm-3">Monto: </div>
							<div class="col-sm-3">{{item.amount}}</div>
						</div>
						<div class="row">
							<div class="col-sm-3">Cantidad: </div>
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
							<div class="btn btn-info" ng-click="ConfirmBooking()">Confirmar</div>
						</div>
						<div class="col-sm-2">
							<div class="btn btn-danger" ng-click="CancelBooking()">Cancelar</div>						
						</div>
					</div>
				</div>
				</div>
			</div>
			<div ng-switch-when="confirm" class="row-fluid  fullSection" id="paymentConfirmSection">
				<div class="col-md-offset-1 col-md-5 col-sm-12" id="confirmMessage">
					<div class="col-xs-offset-1 col-xs-10">
						<h1>Reserva confirmada!!</h1>
						<h3>Gracias por escogernos</h3>
						<br/>
						<p>Un e-mail ha sido enviado con los detalles de su alquiler</p>
						<p>Lo esperamos en la sucursal escogida</p>
					</div>
				</div>
			</div>
			<div ng-switch-default>
			</div>
		</div>
	</div>
	<input id="paymentDetails" type="button" hidden="true" ng-click="getDetails()"/>
	
	<!-- Modal -->
	<div class="modal fade" id="loginModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header bookingModalHeader" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h1 class="logo"><img src="static/images/logo.png" alt="Rent-UY" ></h1>
		        	<h3 class="text-light text-primary ">Ingresar</h3>
				</div>
				<div class="modal-body page-login" style="padding: 40px 50px;">
				
					<div ng-show="vm.error" class="alert alert-danger  alert-lightred alert-dismissable">{{vm.error}}</div>
					
					<form role="form" class="form-validation">
						
						<div class="form-group" ng-class="{ 'has-error': (form.email.$dirty && form.email.$error.required ) || form.email.$error.email}">
			                <input type="email" class="form-control" name="email" id="email" class="form-control" ng-model="vm.email" placeholder="Email" required>
			                <span ng-show="form.email.$dirty && form.email.$error.required" class="help-block">Email es requirido</span>
			                <span ng-show="form.email.$dirty && form.email.$error.email" class="help-block">No es un Email valido</span>
			            </div>
			
			            <div class="form-group" ng-class="{ 'has-error': form.password.$dirty && form.password.$error.required }">
			                <input type="password" name="password" id="password" class="form-control underline-input" ng-model="vm.password" placeholder="Contraseña" required />
			                <span ng-show="form.password.$dirty && form.password.$error.required" class="help-block">Contraseña es requerida</span>
			            </div>
			
			            <div class="text-left">
			                <button type="submit" ng-click="login()" ng-disabled="form.$invalid" class="btn btn-blue b-0 br-2 mr-5">Ingresar</button>
			            </div>
			            <div class="form-group text-left">
			                <label class="checkbox checkbox-custom-alt checkbox-custom-sm inline-block"><input type="checkbox"><i></i> Recuerdame</label>
			                <a href="#/forgot" class="pull-right mt-10">Recuperar contraseña</a>
			            </div>
						
					</form>
				</div>
				<div class="modal-footer bookingModalFooter">
					<button type="submit" class="btn btn-danger pull-left"
						data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span> Cancel
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End Modal -->
	
</section>
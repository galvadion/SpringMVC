<ng-include src="'blocks/header'"></ng-include>

<section id="content">
	<div class="container-fluid">
		<div class="row-fluid">
			<div ng-if="vm.booleano" class="col-md-4 col-sm-12">
				<table class="table table-bordered">
					
					<!-- header -->
	                <tr class="well">
	                    <td><b>Item</b></td>
	                    <td class="tdCenter"><b>Quantity</b></td>
	                    <td class="tdRight"><b>Price</b></td>
	                    <td />
	                </tr>
	                
	                <!-- cart items -->
	                <tr ng-repeat="item in cart.items">
	                	<td>{{item.name}}</td>
	                	<td class="tdCenter">
	                		<div class="input-append">
	                			<input
	                				class="text-center span3" type="tel"
	                				ng-model="item.quantity"
	                			/>
	                			<button
	                				class="btn btn-success" type="button"
	                				ng-disabled="item.quantity >= 10"
	                				ng-click="cart.addItem(item.sku, item.name, item.price, +1)"></button>
	                			<button
	                				class="btn btn-inverse" type="button"
	                				ng-disabled="item.quantity < 0"
	                				ng-click="cart.addItem(item.sku, item.name, item.price, -1)"></button>
	                		</div>
	                	</td>
	                	<td class="tdRight">{{item.price * item.quantity | currency}}</td>
<!-- 	                	<td class="tdCenter" title="remove from cart"> -->
<!-- 	                		<a href="" ng-click="cart.addItem(item.sku, item.name, item.price, -10000000)" > -->
<!-- 	                			<i class="icon-remove"></i> -->
<!-- 	                		</a> -->
<!-- 	                	</td> -->
	                </tr>
	                
	                <!-- footer -->
	                <tr class="well">
	                	<td><b>Total</b></td>
	                	<td class="tdCenter"><b>{{cart.getTotalCount()}}</b></td>
	                	<td class="tdRight"><b>{{cart.getTotalPrice()}}</b></td>
	                	<td />
	                </tr>
				</table>
				
				<br /><br />
				
				<p class="text-info">
	                <button id="btnPaypalId"
	                    class="btn btn-block btn-link"
	                    ng-click="cart.checkout('PayPal')"
	                    ng-disabled="cart.getTotalCount() < 1" >
	                    <img src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" alt="checkout PayPal"/>
	                </button>   
	            </p>
			</div>
			<div ng-if="!vm.booleano" class="col-md-4 col-sm-12">
				<h1>Thank you!!!</h1>
				<h3>Please, review your booking before confirm</h3>
				
				<div ng-repeat="item in vm.items">
					<div class="row">
						<div class="col-sm-2">Name: </div>
						<div class="col-sm-2">{{item.name}}</div>
					</div>
					<div class="row">
						<div class="col-sm-2">Amount: </div>
						<div class="col-sm-2">{{item.amount}}</div>
					</div>
					<div class="row">
						<div class="col-sm-2">Quantity: </div>
						<div class="col-sm-2">{{item.quantity}}</div>
					</div>
					<br/>
					
				</div>
				<div>
					<div class="row">
						<div class="col-sm-2">Total: </div>
						<div class="col-sm-2">{{vm.itemTotal}}</div>
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
		</div>
		<input id="paymentDetails" type="button" hidden="true" ng-click="getDetails()"/>
	</div>
	
	
</section>
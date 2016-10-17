<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Inicio </strong></h1> banner?
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                        <h1 class="custom-font"><strong>Titulo </strong>de una seccion</h1>
                    </div>
                    <!-- /tile header -->

                    <!-- tile body -->
                    <div class="tile-body table-custom">
            			<p>PUT ALL THE CRAP HERE</p>
            			<form action="https://www.sandbox.paypal.com/webapps/adaptivepayment/flow/pay" target="PPDGFrame" class="standard">
							<label for="buy">Buy Now:</label>
							<input type="image" id="submitBtn" value="Pay with PayPal" src="https://www.paypalobjects.com/en_US/i/btn/btn_paynowCC_LG.gif">
							<input id="type" type="hidden" name="expType" value="light">
							<input id="paykey" type="hidden" name="paykey" value="insert_pay_key">
						</form>
						
						<form action="https://www.sandbox.paypal.com/webapps/adaptivepayment/flow/pay" target="PPDGFrame" class="standard">
							<label for="buy">Buy Now:</label>
							<input type="image" id="submitBtn" value="Pay with PayPal" src="https://www.paypalobjects.com/en_US/i/btn/btn_paynowCC_LG.gif">
							<input id="type" type="hidden" name="expType" value="mini">
							<input id="paykey" type="hidden" name="paykey" value="insert_pay_key">
						</form>
            		</div>
            	
            	</section>
            </div>
		<!-- /row -->
		
		<script type="text/javascript" charset="utf-8">
			var embeddedPPFlow = new PAYPAL.apps.DGFlow({trigger: 'submitBtn'});
		</script>
		
		<script type="text/javascript" charset="utf-8">
			var dgFlowMini = new PAYPAL.apps.DGFlowMini({trigger: 'submitBtn'});
		</script>
		
        </div>

    </div>

</section>
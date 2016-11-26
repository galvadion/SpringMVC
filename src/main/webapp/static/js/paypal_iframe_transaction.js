$(document).ready(function(){
	var url = window.location.href;
	if(url.includes("paypal-transaction-flow")){
		
		paypal.checkout.setup('info-rentuy@gmail.com', {
	    	environment: 'sandbox',
	    	container: 'btnPaypalId'
	    });
	    
	    paypal.checkout.initXO();
	}
	else{
		parent.getDetails(url);
	}
});

function paypalBeginTransaction(token){
	paypal.checkout.startFlow(token);
}
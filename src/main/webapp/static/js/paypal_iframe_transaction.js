$(document).ready(function(){
	paypal.checkout.setup('info-rentuy@gmail.com', {
    	environment: 'sandbox',
    	container: 'btnPaypalId'
    })
    
    paypal.checkout.initXO();
});

function paypalBeginTransaction(token){
	paypal.checkout.startFlow(token);
}
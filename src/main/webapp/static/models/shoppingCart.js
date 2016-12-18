//----------------------------------------------------------------
// shopping cart
//
function shoppingCart(cartName) {
    this.cartName = cartName;
    this.clearCart = false;
    this.checkoutParameters = {};
    this.items = [];

}

//adds an item to the cart
shoppingCart.prototype.addItem = function (sku, name, price, quantity) {
    quantity = this.toNumber(quantity);
    if (quantity != 0) {

        // update quantity for existing item
        var found = false;
        for (var i = 0; i < this.items.length && !found; i++) {
            var item = this.items[i];
            if (item.sku == sku) {
                found = true;
                item.quantity = this.toNumber(item.quantity + quantity);
                if (item.quantity <= 0) {
                    this.items.splice(i, 1);
                }
            }
        }

        // new item, add now
        if (!found) {
            var item = new cartItem(sku, name, price, quantity);
            this.items.push(item);
        }
    }
}

//get the total price for all items currently in the cart
shoppingCart.prototype.getTotalPrice = function (sku) {
    var total = 0;
    for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        if (sku == null || item.sku == sku) {
            total += this.toNumber(item.quantity * item.price);
        }
    }
    return total;
}

// get the total price for all items currently in the cart
shoppingCart.prototype.getTotalCount = function (sku) {
    var count = 0;
    for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        if (sku == null || item.sku == sku) {
            count += this.toNumber(item.quantity);
        }
    }
    return count;
}

//clear the cart
shoppingCart.prototype.clearItems = function () {
    this.items = [];
    //this.saveItems();
}

// define checkout parameters
shoppingCart.prototype.addCheckoutParameters = function (serviceName, merchantID, options) {

    // check parameters
    if (serviceName != "PayPal") {
        throw "serviceName must be 'PayPal'.";
    }
    if (merchantID == null) {
        throw "A merchantID is required in order to checkout.";
    }

    // save parameters
    this.checkoutParameters[serviceName] = new checkoutParameters(serviceName, merchantID, options);
}

//check out
shoppingCart.prototype.checkout = function (serviceName, clearCart) {

    // select serviceName if we have to
    if (serviceName == null) {
        var p = this.checkoutParameters[Object.keys(this.checkoutParameters)[0]];
        serviceName = p.serviceName;
    }

    // sanity
    if (serviceName == null) {
        throw "Use the 'addCheckoutParameters' method to define at least one checkout service.";
    }

    // go to work
    var parms = this.checkoutParameters[serviceName];
    if (parms == null) {
        throw "Cannot get checkout parameters for '" + serviceName + "'.";
    }
    switch (parms.serviceName) {
        case "PayPal":
            this.checkoutPayPal(parms, clearCart);
            break;
        case "Google":
            this.checkoutGoogle(parms, clearCart);
            break;
        case "Stripe":
            this.checkoutStripe(parms, clearCart);
            break;
        default:
            throw "Unknown checkout service: " + parms.serviceName;
    }
}

// check out using PayPal
// for details see:
// www.paypal.com/cgi-bin/webscr?cmd=p/pdn/howto_checkout-outside
shoppingCart.prototype.checkoutPayPal = function (parms, clearCart) {

    // global data
    var data = {
        cmd: "_cart",
        business: parms.merchantID,
        upload: "1",
        rm: "2",
        charset: "utf-8"
    };
    
    var datos = [];
    
    for(var i = 0; i < this.items.length; i++){
    	var item = this.items[i];
    	var TransactionItem = {'name' : item.name, 'amount' : item.price.toFixed(2), 'quantity' : item.quantity};
    	datos.push(TransactionItem);
    }

    var jsonData = JSON.stringify(datos);
    var paypaliframe = document.createElement('iframe');
    paypaliframe.src = "payment/paypal-transaction-flow";
    paypaliframe.id = "paypal-iframe";
    paypaliframe.width = "0px";
    paypaliframe.height = "0px";
    document.body.appendChild(paypaliframe);

	$.support.cors = true;
	$.ajax({
		url:"/SpringMVC/payment/start-paypal",
		type: "POST",
		async: true,
		crossDomain: true,
		data: jsonData,
		contentType: "json",
		dataType: "json",
		headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		
		success:function(response){
			if(response.status == 500){
				alert(response.message);
			}
			else{
				paypaliframe.contentWindow.paypalBeginTransaction(response.token);
				
			}
		},
		error: function(response){
			$("#paypal-iframe").remove();
			$("#errorModal").modal();
		}
	})
    
    this.clearCart = clearCart == null || clearCart;
}

//utility methods
shoppingCart.prototype.addFormFields = function (form, data) {
    if (data != null) {
        $.each(data, function (name, value) {
            if (value != null) {
                var input = $("<input></input>").attr("type", "hidden").attr("name", name).val(value);
                form.append(input);
            }
        });
    }
}
shoppingCart.prototype.toNumber = function (value) {
    value = value * 1;
    return isNaN(value) ? 0 : value;
}

function getDetails(url){
	
	if(url.includes("accept-payment")){
		var urlParts = url.split("?");
		var parameters = urlParts[1].split("&");
		var token, payerId;
		parameters.forEach(function (p){
			if(p.includes("token")){
				token = p.split("=")[1];
			}
			else{
				if(p.includes("PayerID")){
					payerId = p.split("=")[1];
				}
			}
		});
		var inputToken = document.createElement("input");
		inputToken.hidden = "true";
		inputToken.value = token;
		inputToken.id = "token";
		
		var inputPayerId = document.createElement("input");
		inputPayerId.hidden = "true";
		inputPayerId.value = payerId;
		inputPayerId.id = "payerId";
		
		document.body.appendChild(inputToken);
		document.body.appendChild(inputPayerId);
		$("#paymentDetails").click();
	}
	$("#paypal-iframe").remove();
};

//----------------------------------------------------------------
//checkout parameters (one per supported payment service)
//
function checkoutParameters(serviceName, merchantID, options) {
	 this.serviceName = serviceName;
	 this.merchantID = merchantID;
	 this.options = options;
}

//----------------------------------------------------------------
//items in the cart
//
function cartItem(sku, name, price, quantity) {
	 this.sku = sku;
	 this.name = name;
	 this.price = price * 1;
	 this.quantity = quantity * 1;
}

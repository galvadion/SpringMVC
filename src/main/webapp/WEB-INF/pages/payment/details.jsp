<ng-include src="'blocks/header'"></ng-include>

<section id="Content">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-6 col-sm-12">
				<h1>Thank you ${transaction.getPayerFirsName()} for choosing us!</h1>
				<h3>Please, check the details before confirm your reservation</h3>
				<c:forEach items="${transaction.getItemsList()}" var="item">
					${item.getName() x {item.getQuantity()} = ${item.getAmount()}
					<br/>
				<c/:forEach>
				<br/>
				<b>Total: {transaction.getOrderTotal()}</b>
			</div>
		</div>
	</div>

</section>
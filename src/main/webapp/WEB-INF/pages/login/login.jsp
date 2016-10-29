<ng-include src="'blocks/header'"></ng-include>

<section id="content">

    <div class="page page-dashboard">
    
    
		<div class="row page-login">
		   
		   <div class="container w-420 p-15 text-center" style="background-color: white; width: 500px !Important;">
		   		<h1 class="logo"><img src="static/images/logo.png" alt="Rent-UY" ></h1>
		
		        <h3 class="text-light text-primary ">Ingresar</h3>
		
		        <div ng-show="vm.error" class="alert alert-danger  alert-lightred alert-dismissable">{{vm.error}}</div>
		
		        <form name="form" ng-submit="vm.login()" role="form" class="form-validation mt-20">
		
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
		                <button type="submit" ng-disabled="form.$invalid" class="btn btn-blue b-0 br-2 mr-5">Ingresar</button>
		            </div>
		            <div class="form-group text-left">
		                <label class="checkbox checkbox-custom-alt checkbox-custom-sm inline-block"><input type="checkbox"><i></i> Recuerdame</label>
		                <a href="#/forgot" class="pull-right mt-10">Recuperar contraseña</a>
		            </div>
		
		        </form>
				
				<div class="lt wrap-reset mt-10">
			        <p class="m-0">
			            <a href="#/register" class="text-uppercase">Registrarse</a>
			        </p>
			    </div>
			    
		    </div>
		
		
		</div>
		
	</div>
</section>

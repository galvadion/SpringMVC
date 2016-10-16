<ng-include src="'blocks/header'"></ng-include>

<section id="content">
    <div class="page page-dashboard">
        <div class="row page-login">

			<div class="container w-420 p-15 text-center" style="background-color: white; width: 450px !Important;">
				<h1 class="logo"><img src="static/images/logo.png" alt="Logo" ></h1>
			    <h3 class="text-light text-primary ">Recuperar contraseña</h3>
			
			    <form name="form" role="form" ng-submit="vm.forgot()" class="form-validation mt-20">
			
			          <p class="help-block text-left">
			          	Ingresar direccion de correo para recuperar su contraseña.
			          </p>
			
			          <div class="form-group"  >
			              <input type="email" name="username" id="username" class="form-control underline-input" ng-model="vm.username" placeholder="Email" required>
			          </div>
			
			
			
			          <div class="form-group">
			              <p class="m-0">
			                  <button type="submit" ng-disabled="form.$invalid" class="btn btn-blue b-0 br-2 mr-5">Recuperar</button>
			              </p>
			          </div>
			    </form>
			      
			    <div class="lt wrap-reset mt-10">
			        <a href="#/login" class="b-0 text-uppercase">Atras</a>
			    </div>
			
			 </div>

		</div>
	</div>
</section>
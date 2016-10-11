<div class="page page-core page-login">

   <h1 class="logo"><img src="/assets/images/logo.png" alt="Logo" ></h1>    
   
   <div class="container w-420 p-15 text-center">

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

    </div>

    <div class="lt wrap-reset mt-10">
        <a href="#/login" class="b-0 text-uppercase">Atras</a>
    </div>

</div>


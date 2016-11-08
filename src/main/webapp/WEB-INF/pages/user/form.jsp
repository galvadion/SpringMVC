<ng-include src="'blocks/header'"></ng-include>

<section id="content">

	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 ng-if="vm.location[1] == 'client'" class="custom-font" style="margin-top: -10px !important;"><strong>Ajustes de Cliente</strong></h1>
			<h1 ng-if="vm.location[1] == 'employee'" class="custom-font" style="margin-top: -10px !important;"><strong>Ajustes de Empleado</strong></h1>
			
			<ol ng-if="vm.location[1] == 'client'" class="breadcrumb">
				<li><a href="#/home">Inicio</a></li>
				<li><a href="#/client">Clientes</a></li>
				<li ng-if="vm.location[2] == 'create'" class="breadcrumb-active">Nuevo Cliente</li>
				<li ng-if="vm.location[2] == 'edit'" class="breadcrumb-active">Editar Cliente</li>
			</ol>
			
			<ol ng-if="vm.location[1] == 'employee'" class="breadcrumb">
				<li><a href="#/home">Inicio</a></li>
				<li><a href="#/employee">Empleados</a></li>
				<li ng-if="vm.location[2] == 'create'" class="breadcrumb-active">Nuevo Empleado</li>
				<li ng-if="vm.location[2] == 'edit'" class="breadcrumb-active">Editar Empleado</li>
			</ol>
		</div>

		<!-- row -->
		<div class="row">
			<!-- col -->
			<div class="col-md-12  profile-settings">
				<!-- tile -->
				<section class="tile tile-simple">

					<!-- tile body -->
					<div class="tile-body" >

						<form  name='form' ng-submit="saveUser()" rol="form" class="profile-settings">

							<div class="row">
								<div class="form-group col-sm-6" ng-class="{ 'has-error': form.name.$dirty&& 	form.name.$error.required }">
									<label for="name">* Nombre</label>
									<input type="text" class="form-control" id="name" name="name" ng-model="vm.user.name" ng-readonly="vm.edit" required>
									<span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Nombre es requerido</span>
								</div>
								<div class="form-group col-sm-6"  ng-class="{ 'has-error': form.lastname.$dirty && 	form.lastname.$error.required }">
									<label for="last-name">* Apellido</label>
									<input type="text" class="form-control" id="lastname" name="lastname" ng-model="vm.user.lastName" ng-readonly="vm.edit" required>
									<span ng-show="form.lastname.$dirty && form.lastname.$error.required" class="help-block">Apellido es requerido</span>
								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-6" ng-class="{ 'has-error': (form.email.$dirty && 	form.email.$error.required) || form.email.$error.email }">
									<label for="email">* E-mail</label>
									<input type="email" class="form-control" id="email" name="email" ng-model="vm.user.email" ng-readonly="vm.edit" required />
									<span ng-show="form.email.$dirty && form.email.$error.required" class="help-block">Email es requerido</span>
									<span class="help-block" ng-show="form.email.$error.email">
										No es un email valido!</span>
									</div>


									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.address.$dirty && form.address.$error.required }" >
										<label for="address1">* Dirección</label>
										<input type="text" class="form-control" id="address" name="address" ng-model="vm.user.address" ng-readonly="vm.edit" required>
										<span ng-show="form.address.$dirty && form.address.$error.required" class="help-block">Dirección es requerido</span>
									</div>

								</div>

								<div class="row">
									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.phone.$dirty && form.phone.$error.required }">
						                <label for="phone">* Telefono</label>
						                <input type="text" pattern="\d*" name="phone" class="form-control" placeholder="Telefono" ng-model="vm.user.phone" ng-readonly="vm.edit" required />
						                <span ng-show="form.phone.$dirty && form.phone.$error.required" class="help-block">Telefono es requerido</span>
						            </div>
						            
									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.password.$dirty && form.password.$error.required}">
										<label for="password">* Contraseña</label>
										<input type="password"  name="password" id="password" class="form-control" id="password" ng-model="vm.user.password" required>
										<span ng-show="form.password.$dirty && form.password.$error.required" class="help-block">Contraseña es requerida</span>
									</div>

								</div>
								
								<div class="row" ng-if="vm.location[1] == 'client'">

										<div class="form-group col-sm-6" ng-class="{ 'has-error': form.birthDate.$dirty && form.birthDate.$error.required }">
											<label for="form.birthDate.$dirty">* Fecha de Nacimiento</label>
							                <input type="text" name="birthDate" id="Text1" class="form-control" placeholder="Nacimiento DD-MM-YYYY" ng-model="vm.user.birthDate" ng-readonly="vm.edit" required />
							                <span ng-show="form.birthDate.$dirty && form.birthDate.$error.required" class="help-block">Fecha de nacimiento es requerido</span>
							            </div>
							            
							            
							            <div class="form-group col-sm-6" ng-class="{ 'has-error': form.licenseExpirationDate.$dirty && form.licenseExpirationDate.$error.required }">
							                <label for="form.licenseExpirationDate.$dirty">* Vencimiento Licencia</label>
							                <input type="text" name="licenseExpirationDate" id="Text1" class="form-control" placeholder="Vencimiento licencia DD-MM-YYYY" ng-model="vm.user.licenseExpirationDate" ng-readonly="vm.edit" required />
							                <span ng-show="form.licenseExpirationDate.$dirty && form.licenseExpirationDate.$error.required" class="help-block">Vencimiento de licencia es requerido</span>
							            </div>
										
								</div>
								
								<div class="form-group text-right">
									<a ng-if="vm.location[1] == 'client'" href="#/client" class="btn btn-lightred">Cancelar</a>
									<a ng-if="vm.location[1] == 'employee'" href="#/employee" class="btn btn-lightred">Cancelar</a>
									<button type="submit" id="submit" ng-disabled="form.$invalid" class="btn  btn-orange">Guardar</button>
								</div>
							</form>
						</div>
						<!-- /tile body -->
					</section>
					<!-- /tile -->

				</div>
				<!-- /tile body -->

			</div>
			<!-- /row -->
		</div>
	</section>

<!--/ CONTENT -->
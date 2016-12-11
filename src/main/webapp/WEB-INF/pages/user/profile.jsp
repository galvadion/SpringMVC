<ng-include src="'blocks/header'"></ng-include>

<section id="content">

	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Perfil </strong></h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Inicio</a></li>
				<li class="breadcrumb-active">Perfil</li>
			</ol>
		</div>

		<!-- row -->
		<div class="row">
			<!-- col -->
			<div class="col-md-8  profile-settings">
				<!-- tile -->
				<section class="tile tile-simple">

					<div class="tile-header dvd dvd-btm">
						<h1 class="custom-font legend"><strong>Ajustes</strong> de perfil</h1>
					</div>
					<!-- tile body -->
					<div class="tile-body" >

						<form  name='form' ng-submit="saveUser()" rol="form" class="profile-settings">

							<div class="row">
								<div class="form-group col-sm-6">
									<label for="name">* Nombre</label>
									<input type="text" class="form-control" id="name" name="name" ng-model="vm.user.name" disabled />
								</div>
								<div class="form-group col-sm-6">
									<label for="last-name">* Apellido</label>
									<input type="text" class="form-control" id="lastname" name="lastname" ng-model="vm.user.lastName" disabled />
								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-6">
									<label for="email">* E-mail</label>
									<input type="email" class="form-control" id="email" name="email" ng-model="vm.user.email" disabled />
								</div>

								<div class="form-group col-sm-6" >
									<label for="address1">* Dirección</label>
									<input type="text" class="form-control" id="address" name="address" ng-model="vm.user.address" required />
								</div>
							</div>

								<div class="row">

									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.password.$dirty &&  vm.user.password.length <= 1 }">
										<label for="password">* Contraseña</label>
										<input type="password"  name="password" id="password" class="form-control" id="password" ng-model="vm.user.password" ng-keyup="checkPaswords()" required>
										<span ng-show="form.password.$dirty && vm.user.password.length <= 1 " class="help-block">Contraseña es requerida</span>
									</div>

									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.newpassword.$dirty && vm.user.password != vm.newpassword }">
										<label for="password">* Confirmar Contraseña</label>
										<input type="password"  name="newpassword" id="newpassword" class="form-control" ng-model="vm.newpassword" ng-keyup="checkPaswords()" ng-disabled="!vm.newPasswordFlag">										
										<span ng-show="form.newpassword.$dirty && vm.user.password != vm.newpassword" class="help-block">Contraseñas no concuerdan</span>
									</div>

								</div>
								
								<div class="row">
									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.phone.$dirty && form.phone.$error.required }">
						                <label for="phone">* Télefono</label>
						                <input type="text" pattern="\d*" name="phone" class="form-control" placeholder="Télefono" ng-model="vm.user.phone" required />
						                <span ng-show="form.phone.$dirty && form.phone.$error.required" class="help-block">Télefono es requerido</span>
						            </div>
						            
						            <div class="form-group col-sm-6" ng-show="vm.rolclient">
										<label for="address1">* Documento</label>
										<input type="text" class="form-control" id="document" name="document" ng-model="vm.user.document" disabled />
									</div>
						        </div>
								
								<div class="row" ng-show="vm.rolclient">

										<div class="form-group col-sm-6">
											<label for="form.birthDate.$dirty">* Fecha de Nacimiento</label>
							                <input type="text" name="birthDate" id="Text1" class="form-control" placeholder="Nacimiento DD/MM/YYYY" ng-model="vm.user.birthDate" disabled />
							            </div>
							            
							            
							            <div class="form-group col-sm-6" ng-class="{ 'has-error': form.licenseExpirationDate.$dirty && form.licenseExpirationDate.$error.required }">
							                <label for="form.licenseExpirationDate.$dirty">* Vencimiento Licencia</label>
							                <input type="text" name="licenseExpirationDate" id="Text1" class="form-control" placeholder="Vencimiento licencia DD/MM/YYYY" ng-model="vm.user.licenseExpirationDate" required />
							                <span ng-show="form.licenseExpirationDate.$dirty && form.licenseExpirationDate.$error.required" class="help-block">Vencimiento de licencia es requerido</span>
							            </div>
							            
							            <div class="text-left form-group col-sm-6">
							                <label class="checkbox checkbox-custom-alt checkbox-custom-sm inline-block">
							                    <input type="checkbox" ng-model="vm.user.allowNotification"><i></i> Deseo recibir emails con promociones.</a>
							                </label>
							            </div>
										
								</div>
								
								<div class="form-group text-right">
									<a href="#/home" class="btn btn-lightred">Cancelar</a>
									<button type="submit" id="submit" ng-disabled="form.$invalid || (vm.newPasswordFlag && (vm.newpassword != vm.user.password))" class="btn  btn-orange">Guardar</button>
								</div>
							</form>
						</div>
						<!-- /tile body -->
					</section>
					<!-- /tile -->

				</div>
				<!-- /tile body -->

				<!-- col -->
				<div class="col-md-4 p-fixed-right">

					<!-- tile -->
					<section class="tile tile-simple hidden-xs hidden-sm ">

						<!-- tile widget -->
						<div class="tile-widget p-30 text-center">
							<div class="thumb thumb-xl">
								<img class="img-circle" ng-src="static/images/avatar.png" alt="avatar">
							</div>
							<br/>
							<h4 class="mb-0"><strong>{{vm.user.email}}</strong></h4>
						</div>
						<!-- /tile widget -->

					</section>
					<!-- /tile -->

				</div>
			</div>
			<!-- /row -->
		</div>
	</section>

<!--/ CONTENT -->
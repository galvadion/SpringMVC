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
								<div class="form-group col-sm-6" ng-class="{ 'has-error': form.name.$dirty&& 	form.name.$error.required }">
									<label for="name">* Nombre</label>
									<input type="text" class="form-control" id="name" name="name" ng-model="vm.user.name" ng-readonly="vm.edit" required>
									<span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Nombre es requerido</span>
								</div>
								<div class="form-group col-sm-6"  ng-class="{ 'has-error': form.lastname.$dirty && 	form.lastname.$error.required }">
									<label for="last-name">* Apellido</label>
									<input type="text" class="form-control" id="lastname" name="lastname" ng-model="vm.user.lastname" ng-readonly="vm.edit" required>
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


									<div class="form-group col-sm-6" >
										<label for="address1">* Direccion</label>
										<input type="text" class="form-control" id="address" name="address" ng-model="vm.user.address" ng-readonly="vm.edit" required>
									</div>

								</div>

								<div class="row">

									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.password.$dirty &&  vm.user.password.length <= 1 }">
										<label for="password">* Contrase�a</label>
										<input type="password"  name="password" id="password" class="form-control" id="password" ng-model="vm.user.password" ng-disabled="vm.user.id > 0" ng-keyup="checkPaswords()" ng-readonly="vm.edit" required>
										<span ng-show="form.password.$dirty && vm.user.password.length <= 1 " class="help-block">Contrase�a es requerida</span>
									</div>

									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.newpassword.$dirty && vm.user.password != vm.newpassword }">
										<label for="password">* Confirmar Contrase�a</label>
										<input type="password"  name="newpassword" id="newpassword" class="form-control" ng-model="vm.newpassword" ng-disabled="vm.user.id > 0" ng-keyup="checkPaswords()" ng-readonly="vm.edit" required>										
										<span ng-show="form.newpassword.$dirty && vm.user.password != vm.newpassword" class="help-block">Contrase�as no concuerdan</span>
									</div>

								</div>
								
								<div class="row">
									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.phone.$dirty && form.phone.$error.required }">
						                <label for="phone">* Telefono</label>
						                <input type="text" pattern="\d*" name="phone" class="form-control" placeholder="Telefono" ng-model="vm.user.phone" ng-readonly="vm.edit" required />
						                <span ng-show="form.phone.$dirty && form.phone.$error.required" class="help-block">Telefono es requerido</span>
						            </div>
						        </div>
								
								<div class="row" ng-show="vm.rolclient">

										<div class="form-group col-sm-6" ng-class="{ 'has-error': form.birthDate.$dirty && form.birthDate.$error.required }">
											<label for="form.birthDate.$dirty">* Fecha de Nacimiento</label>
							                <input type="text" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" name="birthDate" id="Text1" class="form-control" placeholder="Nacimiento YYYY-MM-DD" ng-model="vm.user.birthDate" ng-readonly="vm.edit" required />
							                <span ng-show="form.birthDate.$dirty && form.birthDate.$error.required" class="help-block">Fecha de nacimiento es requerido</span>
							            </div>
							            
							            
							            <div class="form-group col-sm-6" ng-class="{ 'has-error': form.licenseExpirationDate.$dirty && form.licenseExpirationDate.$error.required }">
							                <label for="form.licenseExpirationDate.$dirty">* Vencimiento Licencia</label>
							                <input type="text" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" name="licenseExpirationDate" id="Text1" class="form-control" placeholder="Vencimiento licencia YYYY-MM-DD" ng-model="vm.user.licenseExpirationDate" ng-readonly="vm.edit" required />
							                <span ng-show="form.licenseExpirationDate.$dirty && form.licenseExpirationDate.$error.required" class="help-block">Vencimiento de licencia es requerido</span>
							            </div>
										
								</div>
								
								<div class="form-group text-right">
									<a href="" ng-click="resetUser()" class="btn btn-lightred">Cancelar</a>
									<button type="submit" id="submit" ng-disabled="form.$invalid || vm.saveUserFlag ==true" class="btn  btn-orange">Guardar</button>
									<img ng-if="vm.adv.dataLoading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
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
								<img class="img-circle" ng-src="static/images/avatar.png" err-src="http://google.com/favicon.ico" alt="avatar">
							</div>
							<h4 class="mb-0"><strong>{{vm.user.email}}</strong></h4>
							<span class="text-muted">{{vm.user.rol}}</span></br>
							<span class="text-muted">Ultima Conexion: {{vm.user.lastlogin}}</span>
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
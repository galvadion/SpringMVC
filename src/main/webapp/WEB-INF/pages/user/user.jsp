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
										<label for="address1">Direccion</label>
										<input type="text" class="form-control" id="address" name="address" ng-model="vm.user.address" ng-readonly="vm.edit" required>
									</div>

								</div>

								<div class="row">

									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.password.$dirty &&  vm.user.password.length <= 1 }">
										<label for="password">* Contraseña</label>
										<input type="password"  name="password" id="password" class="form-control" id="password" ng-model="vm.user.password" ng-disabled="vm.user.id > 0" ng-keyup="checkPaswords()" ng-readonly="vm.edit" required>
										<span ng-show="form.password.$dirty && vm.user.password.length <= 1 " class="help-block">Contraseña es requerida</span>
									</div>

									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.newpassword.$dirty && vm.user.password != vm.newpassword }">
										<label for="password">* Confirmar Contraseña</label>
										<input type="password"  name="newpassword" id="newpassword" class="form-control" ng-model="vm.newpassword" ng-disabled="vm.user.id > 0" ng-keyup="checkPaswords()" ng-readonly="vm.edit" required>										
										<span ng-show="form.newpassword.$dirty && vm.user.password != vm.newpassword" class="help-block">Contraseñas no concuerdan</span>
									</div>

								</div>
								
								<div class="row">
									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.phone.$dirty && form.phone.$error.required }">
						                <label for="phone">* Telefono</label>
						                <input type="text" pattern="\d*" name="phone" class="form-control" placeholder="Telefono" ng-model="vm.user.phone" ng-readonly="vm.edit" required />
						                <span ng-show="form.phone.$dirty && form.phone.$error.required" class="help-block">Telefono es requerido</span>
						            </div>
						            
						            <div ng-show="vm.roladmin"  class="form-group col-sm-6" ng-class="{ 'has-error': form.rol.$dirty && form.rol.$error.required }" >
										<label for="access_level">Rol</label>
										<select ng-readonly="vm.edit" ng-model="vm.rol" ng-change="checkClient()" name="rol" id="rol" class="col-sm-2 control-label col-lg-2 form-control m-bot15" ng-disabled="vm.edit" required>
											<option value=""> Seleccionar Rol</option> 
											<option value="employee">Empleado</option>
											<option value="client">Cliente</option>
										</select>
										<span ng-show="form.rol.$dirty && form.rol.$error.required" class="help-block">Rol es requerido</span>
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

					<!-- tile -->
					<section ng-show="vm.roladmin" class="tile">

						<!-- tile header -->
						<div class="tile-header dvd dvd-btm">
							<h1 class="custom-font"><strong>Users </strong> </h1>
							<div class="col-sm-9 right text-right p-0">
								<button ng-click="newUser()" class="btn btn-orange btn-rounded left mr-5">New User</button>
							</div>
						</div>
						<!-- /tile header -->

						<!-- tile body -->
						<div  class="tile-body">
							<div class="list">
								<ul class="list-group">
									<li  ng-repeat="user in vm.users" class="list-group-item" ng-class="{{user.status}} == 0 ? 'bg-active-user' : ''">
										<span class="pull-right">
											<a class="btn btn-rounded-20 btn-default btn-xs mr-5 bt-times" href="" ng-click="deleteUser(vm.users, $index)" confirm-if="checked" confirm="Are you sure, delete this User: {{user.name}} ?"><i class="fa fa-times"></i></a>
											<a href="" class="btn btn-rounded-20 btn-default btn-xs mr-5 bt-pencil" ng-click="editUser(user)"><i class="fa fa-pencil"></i></a>
											<a href="" class="btn btn-rounded-20 btn-default btn-xs mr-5 bt-lock" ng-click="changeUserStatus($event,user)"><i ng-class="{{user.status}} == 0 ? 'fa-lock' : 'fa-unlock'" class="fa " aria-hidden="true"></i></a>
										</span>
										{{user.name}}
									</li>
								</ul>
							</div>
						</div>

					</section>
					<!-- /tile -->

				</div>
			</div>
			<!-- /row -->
		</div>
	</section>

	<script type="text/ng-template" id="modalDialogCampaign">
	<div class="ngdialog-message modal-content">
	<div class="modal-header">
	<h3 class="modal-title custom-font">Change Password</h3>
	</div>
	<div class="modal-body">
	Do you want to change the <strong>Password</strong>?
	</div>                     
	<div class="modal-footer  ngdialog-buttons">
	<button type="button" class="ngdialog-button btn btn-lightred btn-ef btn-ef-4 btn-ef-4c" ng-click="closeThisDialog('button')"><i class="fa fa-arrow-left"></i>Cancel</button>
	<button type="button" class="ngdialog-button btn btn-success btn-ef btn-ef-3 btn-ef-3c" ng-click="confirm();enablePasswordEdit()"><i class="fa fa-arrow-right"></i> Confirm</button>
	</div>
	</div>
	</script>
<!--/ CONTENT -->
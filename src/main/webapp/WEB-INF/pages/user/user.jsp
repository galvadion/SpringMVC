<ng-include src="'/blocks/header.html'"></ng-include>
<section id="content">

	<div class="page page-dashboard">

		<div class="pageheader">
			<h2><i class="fa fa-user"></i> Profile</h2>
			<ol class="breadcrumb">
				<li><a href="#/home">Dashboard</a></li>
				<li class="active">Profile</li>
				
			</ol>
		</div>

		<!-- row -->
		<div class="row">
			<!-- col -->
			<div class="col-md-8  profile-settings">
				<!-- tile -->
				<section class="tile tile-simple">

					<div class="tile-header dvd dvd-btm">
						<h1 class="custom-font legend"><strong>Profile</strong> Settings</h1>
					</div>
					<!-- tile body -->
					<div class="tile-body" >

						<form  name='form' ng-submit="saveUser()" rol="form" class="profile-settings">

							<div class="row">
								<div class="form-group col-sm-6" ng-class="{ 'has-error': form.name.$dirty&& 	form.name.$error.required }">
									<label for="name">* First Name</label>
									<input type="text" class="form-control" id="name" name="name" ng-model="vm.user.name" ng-readonly="vm.edit" required>
									<span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Name is required</span>
								</div>
								<div class="form-group col-sm-6"  ng-class="{ 'has-error': form.lastname.$dirty && 	form.lastname.$error.required }">
									<label for="last-name">* Last Name</label>
									<input type="text" class="form-control" id="lastname" name="lastname" ng-model="vm.user.lastname" ng-readonly="vm.edit" required>
									<span ng-show="form.lastname.$dirty && form.lastname.$error.required" class="help-block">Last Name is required</span>
								</div>
							</div>

							<div class="row">
								<div class="form-group col-sm-6" ng-class="{ 'has-error': (form.email.$dirty && 	form.email.$error.required) || form.email.$error.email }">
									<label for="email">* E-mail</label>
									<input type="email" class="form-control" id="email" name="email" ng-model="vm.user.email" ng-readonly="vm.edit" required />
									<span ng-show="form.email.$dirty && form.email.$error.required" class="help-block">Email is required</span>
									<span class="help-block" ng-show="form.email.$error.email">
										Not valid email!</span>
									</div>


									<div class="form-group col-sm-6" >
										<label for="address1">Address</label>
										<input type="text" class="form-control" id="address" name="address" ng-model="vm.user.address" ng-readonly="vm.edit">
									</div>

								</div>

								<div class="row">

									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.password.$dirty &&  vm.user.password.length <= 1 }">
										<label for="password">* Password</label>
										<input type="password"  name="password" id="password" class="form-control" id="password" ng-model="vm.user.password" ng-disabled="vm.user.id > 0" ng-keyup="checkPaswords()" >
										<i class="fa fa-pencil edit-password" ng-click="openConfirmEditPassword()" ng-show="vm.user.id > 0"></i>
										<span ng-show="form.password.$dirty && vm.user.password.length <= 1 " class="help-block">Password is Required and Needs to be larger than 5 Characters</span>
									</div>

									<div class="form-group col-sm-6" ng-class="{ 'has-error': form.newpassword.$dirty && vm.user.password != vm.user.newpassword }">
										<label for="password">* Confirm Password</label>
										<input type="password"  name="newpassword" id="newpassword" class="form-control" ng-model="vm.user.newpassword" ng-disabled="vm.user.id > 0" ng-keyup="checkPaswords()">										
										<span ng-show="form.newpassword.$dirty && vm.user.password != vm.user.newpassword" class="help-block">Confirm Password needs to match password field</span>
									</div>

								</div>



								<div class="row">								

									<div ng-show="vm.roladmin"  class="form-group col-sm-6" ng-class="{ 'has-error': form.rol.$dirty && form.rol.$error.required }" >
										<label for="access_level">Rol</label>
										<select ng-readonly="vm.edit" ng-model="vm.rol.id" name="rol" id="rol" class="col-sm-2 control-label col-lg-2 form-control m-bot15" required>
											<option value=""> Please select the Rol</option> 
											<option value="1">Admin</option>
											<option value="2">Advertiser</option>
											<option value="3">Analist</option>
										</select>
										<span ng-show="form.rol.$dirty && form.rol.$error.required" class="help-block">Rol is required</span>
									</div>

									<div  ng-class="{ 'hide': vm.user.id  == null }" class="form-group col-sm-6">
										<label for="avatar" class="col-md-12 pl-0">Avatar</label>

										<label class="customFileInput clearfix">
											<div class="btn btn-success fileinput-button">
												<i class="glyphicon glyphicon-plus"></i>
												<span>Add files...</span>
												<input ng-readonly="vm.edit" class="upload-appIcon filestyle" type="file" nv-file-select="" uploader="file" />
											</div>
										</label>									

										<span class="help-block col-sm-9 " style="padding-left:0px">
											<strong>Allowed files: gif, png, jpg. Max file size 1Mb</strong>
											<small>{{vm.user.avatarURL}}</small>
										</span>
									</div>
								</div>

								
								<div class="form-group text-right">
									<a href="" ng-click="resetUser()" class="btn btn-lightred">Cancel</a>
									<button type="submit" id="submit" ng-disabled="form.$invalid || vm.saveUserFlag ==true" class="btn  btn-orange">Save changes</button>
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
								<img class="img-circle" ng-src="{{vm.user.avatarURL}}" err-src="http://google.com/favicon.ico" alt="avatar">
							</div>
							<h4 class="mb-0"><strong>{{vm.user.email}}</strong></h4>
							<span class="text-muted">{{vm.rol.name}}</span></br>
							<span class="text-muted">Last Login: {{vm.user.lastlogin}}</span>
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
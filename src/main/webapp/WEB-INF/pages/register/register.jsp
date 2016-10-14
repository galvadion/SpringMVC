<ng-include src="'blocks/header'"></ng-include>


<section id="content">


    <div class="page page-dashboard">



        <div class="row page-login">
            <div class="container w-420 p-15 text-center" style="background-color: white; width: 500px !Important;">

		        <h3 class="text-light text-primary ">Registrarse</h3>
		
		        <div ng-show="vm.error" class="alert alert-danger">{{vm.error}}</div>
		
		        <form name="form" ng-submit="vm.register()" role="form" class="form-validation mt-20">
		
		            <div class="form-group" ng-class="{ 'has-error': form.name.$dirty && form.name.$error.required }">
		
		                <input type="text" name="name" id="name" placeholder="Nombre"  class="form-control underline-input" ng-model="vm.user.name" required />
		
		                <span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Nombre es requerido</span>
		
		            </div>
		
		            <div class="form-group" ng-class="{ 'has-error': form.lastName.$dirty && form.lastName.$error.required }">
		
		                <input type="text" name="lastName" id="Text1" class="form-control underline-input" placeholder="Apellido" ng-model="vm.user.lastName" required />
		
		                <span ng-show="form.lastName.$dirty && form.lastName.$error.required" class="help-block">Apellido es requerido</span>
		
		            </div>
		
		            <div class="form-group" ng-class="{ 'has-error': form.email.$dirty &&  form.email.$error.required }">
		
		                <input type="email" class="form-control underline-input" id="email" name="email" ng-model="vm.user.email" ng-readonly="vm.edit" required  placeholder="Email" />
		
		                <span ng-show="form.addresss.$dirty && form.addresss.$error.required" class="help-block">Email es requerido</span>
		                 <span class="error" ng-show="form.email.$error.email">
		                No es un email valido!</span>
		
		            </div>
		            
		            
		            <div class="form-group" ng-class="{ 'has-error': form.phone.$dirty && form.phone.$error.required }">
		
		                <input type="text" pattern="\d*" name="phone" id="Text1" class="form-control underline-input" placeholder="Telefono" ng-model="vm.user.phone" required />
		
		                <span ng-show="form.phone.$dirty && form.phone.$error.required" class="help-block">Telefono es requerido</span>
		            </div>
		
		
					<div class="form-group" ng-class="{ 'has-error': form.address.$dirty && form.address.$error.required }">
		
		                <input type="text" name="address" id="Text1" class="form-control underline-input" placeholder="Direccion" ng-model="vm.user.address" required />
		
		                <span ng-show="form.address.$dirty && form.address.$error.required" class="help-block">Direccion es requerido</span>
		
		            </div>
		            
		            
		            <div class="form-group" ng-class="{ 'has-error': form.birthDate.$dirty && form.birthDate.$error.required }">
		
		                <input type="text" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" name="birthDate" id="Text1" class="form-control underline-input" placeholder="Nacimiento YYYY-MM-DD" ng-model="vm.user.birthDate" required />
		
		                <span ng-show="form.birthDate.$dirty && form.birthDate.$error.required" class="help-block">Fecha de nacimiento es requerido</span>
		
		            </div>
		            
		            
		            <div class="form-group" ng-class="{ 'has-error': form.licenseExpirationDate.$dirty && form.licenseExpirationDate.$error.required }">
		
		                <input type="text" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" name="licenseExpirationDate" id="Text1" class="form-control underline-input" placeholder="Vencimiento licencia YYYY-MM-DD" ng-model="vm.user.licenseExpirationDate" required />
		
		                <span ng-show="form.licenseExpirationDate.$dirty && form.licenseExpirationDate.$error.required" class="help-block">Vencimiento de licencia es requerido</span>
		
		            </div>
		            
		
		            <div class="form-group" ng-class="{ 'has-error': form.password.$dirty && form.password.$error.required }">
		
		                <input type="password" name="password" id="password" placeholder="Password" class="form-control underline-input" ng-model="vm.user.password" required />
		
		                <span ng-show="form.password.$dirty && form.password.$error.required" class="help-block">Contrase�a es requerida</span>
		
		            </div>
		            
		
		            <div class="text-left">
		
		                <label class="checkbox checkbox-custom-alt checkbox-custom-sm inline-block">
		
		                    <input type="checkbox" ng-model="vm.terms" required><i></i> Estoy de acuerdo con los <a href="/SpringMVC/#/terms" target="_blank">Terminos De Servicio</a> 
		
		                </label>
		
		            </div>
					<br/><br/>
		            <div class="form-group">
		
		                    <button type="submit" ng-disabled="form.$invalid || vm.dataLoading" class="btn btn-blue b-0 br-2 mr-5">Registrarse</button>
		
		                    <img ng-if="vm.dataLoading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />  
		            </div>
		        </form>
		        
		        <div class="lt wrap-reset mt-10">
		        <a href="#/login" class="b-0 text-uppercase">Atras</a>
		    </div>
		    </div>
		    
		<!-- /row -->
        </div>

    </div>

</section>
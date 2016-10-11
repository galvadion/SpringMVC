<div class="page page-core page-login">

   <h1 class="logo"><img src="static/images/logo.jpeg" alt="Logo" ></h1>    
   
   <div class="container w-420 p-15 text-center">

        <h3 class="text-light text-primary ">Ingresar</h3>

        <div ng-show="vm.error" class="alert alert-danger  alert-lightred alert-dismissable">{{vm.error}}</div>

        <form name="form" ng-submit="vm.login()" role="form" class="form-validation mt-20">

            <div class="form-group" ng-class="{ 'has-error': (form.emailAddress.$dirty && form.emailAddress.$error.required ) || form.emailAddress.$error.email}">
                <input type="email" class="form-control" name="emailAddress" id="emailAddress" class="form-control" ng-model="vm.emailAddress" placeholder="Email" required>
                <span ng-show="form.emailAddress.$dirty && form.emailAddress.$error.required" class="help-block">Email es requirido</span>
                <span ng-show="form.emailAddress.$dirty && form.emailAddress.$error.email" class="help-block">No es un Email valido</span>
            </div>

            <div class="form-group" ng-class="{ 'has-error': form.password.$dirty && form.password.$error.required }">
                <input type="password" name="password" id="password" class="form-control underline-input" ng-model="vm.password" placeholder="Contraseña" required />
                <span ng-show="form.password.$dirty && form.password.$error.required" class="help-block">Contraseña es requerida</span>
            </div>

            <div class="text-left">
                <button type="submit" ng-disabled="form.$invalid || vm.dataLoading" class="btn btn-blue b-0 br-2 mr-5">Ingresar</button>
                <img ng-if="vm.dataLoading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />

            </div>
            <div class="form-group text-left">
                <label class="checkbox checkbox-custom-alt checkbox-custom-sm inline-block"><input type="checkbox"><i></i> Recuerdame</label>
                <a href="#/forgot" class="pull-right mt-10">Recuperar contraseña</a>
            </div>

        </form>

    </div>

    <div class="lt wrap-reset mt-10">

        <p class="m-0">

            <a href="#/register" class="text-uppercase">Registrarse</a>
        </p>

    </div>


</div>




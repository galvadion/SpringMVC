<div class="page page-core page-login">

    <h1 class="logo"><img src="/assets/images/logo.png" alt="Logo" ></h1>    

    <div class="container w-420 p-15 text-center">


        <h3 class="text-light text-primary ">Registrarse</h3>

        <div ng-show="vm.error" class="alert alert-danger">{{vm.error}}</div>

        <form name="form" ng-submit="vm.register()" role="form"class="form-validation mt-20">

            <div class="form-group" ng-class="{ 'has-error': form.name.$dirty && form.name.$error.required }">

                <input type="text" name="name" id="name" placeholder="Primer Nombre"  class="form-control underline-input" ng-model="vm.user.name" required />

                <span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Primer Nombre es requerido</span>

            </div>

            <div class="form-group" ng-class="{ 'has-error': form.lastName.$dirty && form.lastName.$error.required }">

                <input type="text" name="lastName" id="Text1" class="form-control underline-input" placeholder="Segundo Nombre" ng-model="vm.user.lastName" required />

                <span ng-show="form.lastName.$dirty && form.lastName.$error.required" class="help-block">Segundo Nombre es requerido</span>

            </div>

            <div class="form-group" ng-class="{ 'has-error': form.email.$dirty &&  form.email.$error.required }">

                <input type="email" class="form-control underline-input" id="email" name="email" ng-model="vm.user.email" ng-readonly="vm.edit" required  placeholder="Email" />

                <span ng-show="form.address.$dirty && form.address.$error.required" class="help-block">Email es requerido</span>
                 <span class="error" ng-show="form.email.$error.email">
                No es un email valido!</span>

            </div>


            

            <div class="form-group" ng-class="{ 'has-error': form.password.$dirty && form.password.$error.required }">

                <input type="password" name="password" id="password" placeholder="Password" class="form-control underline-input" ng-model="vm.user.password" required />

                <span ng-show="form.password.$dirty && form.password.$error.required" class="help-block">Contraseña es requerida</span>

            </div>

            <div  ng-class="{ 'has-error': form.rol.$dirty && form.rol.$error.required }">

                <select name="rol" id="rol" ng-model="vm.user.rol" class="form-control mb-10" required>
                    <option value=""> Por favor selecciona el Rol</option> 
                    <!--<option value="1">Admin</option>-->
                    <option value="2">Rol1</option>
                    <option value="3">Rol2</option>
                </select>


                <span ng-show="form.rol.$dirty && form.rol.$error.required" class="help-block">Rol es requerido</span>

            </div>

            <div class="text-left">

                <label class="checkbox checkbox-custom-alt checkbox-custom-sm inline-block">

                    <input type="checkbox" ng-model="vm.terms" required><i></i> Estoy de acuerdo con los <a href="/#/terms" target="_blank">Terminos De Servicio</a> 

                </label>

            </div>

            <div class="form-group">

                    <button type="submit" ng-disabled="form.$invalid || vm.dataLoading" class="btn btn-blue b-0 br-2 mr-5">Registrarse</button>

                    <img ng-if="vm.dataLoading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />  
            </div>
        </form>
    </div>
    <div class="lt wrap-reset mt-10">
        <a href="#/login" class="b-0 text-uppercase">Atras</a>
    </div>
</div>

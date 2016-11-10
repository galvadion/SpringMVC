<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Búsqueda</strong></h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Búsqueda</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	<section class="tile" style="height: 400px !important;">
					<br/>
					<br/>
					<form name='form' class="" role="form" ng-submit="searchModels()">

						<div class="row" style="padding: 0 10px 0 10px;">

							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="beginDate" data-date="" data-date-format="dd-mm-yyyy" data-link-field="dtp_input2" data-link-format="dd-mm-yyyy">
									<label for="beginDate" class="control-label">Fecha de recogida</label>
									<input class="form-control" size="16" type="text" name="beginDate" placeholder="DD-MM-YYYY" ng-model="vm.search.beginDate" ng-change="checkEndDate()" readonly required>
									<span class="input-group-addon customAddonSize">
									<span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>

							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeOriginId.$dirty && form.officeOriginId.$error.required }">
								<label for="officeOriginId" class="control-label">Sucursal de recogida</label>
								<select ng-model="vm.search.officeOriginId" name="officeOriginId" id="officeOriginId" class="form-control" required>
									<option value="">Seleccione sucursal</option>
									<option ng-repeat="(key, value) in vm.allOffices" ng-value="{{value.id}}">{{value.id}} {{value.name}} - {{value.city}}</option>
								</select>
								<span ng-show="form.officeOriginId.$dirty && form.officeOriginId.$error.required" class="help-block">Sucursal es requerido</span>
							</div>
						</div>
{{vm.search.officeOriginId}}
						<div class="row" style="padding: 0 10px 0 10px;">

							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="endDate" data-date="" data-date-format="dd-mm-yyyy" data-link-field="dtp_input2" data-link-format="dd-mm-yyyy">
									<label for="endDate" class="control-label">Fecha de entrega</label>
									<input class="form-control" size="16" type="text" name="endDate" placeholder="DD-MM-YYYY" ng-model="vm.search.endDate" ng-change="checkEndDate()" readonly required>
									<span class="input-group-addon customAddonSize">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
								<span ng-show="endDateError" class="help-block" style="color: red">Fecha de entrega debe ser posterior a la de recogida</span>
							</div>

							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeEndId.$dirty && form.officeEndId.$error.required }">
								<label for="officeEndId" class="control-label">Sucursal de entrega</label>
									<select ng-model="vm.search.officeEndId" name="officeEndId" id="officeEndId" class="form-control" required>
										<option value="">Seleccione sucursal</option>
										<option ng-repeat="(key, value) in vm.allOffices" value="{{value.id}}">{{value.name}} - {{value.city}}</option>
									</select>
								<span ng-show="form.officeEndId.$dirty && form.officeEndId.$error.required" class="help-block">Sucursal es requerido</span>
							</div>
						</div>

						<br /> <br />

						<!-- Buttons -->
						<div class="form-group text-right" style="margin-right: 15px;">
							<button type="submit" id="submit" ng-disabled="form.$invalid || endDateError" class="btn  btn-orange">Buscar ahora</button>
						</div>
					</form>

				</section>
            	
            </div>
		<!-- /row -->
        </div>

    </div>

</section>
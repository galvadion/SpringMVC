<ng-include src="'blocks/header'"></ng-include>

<section id="content">


	<div class="page page-dashboard">

		<div class="pageheader">
			<h1 class="custom-font" style="margin-top: -10px !important;"
				ng-if="vm.location[2] == 'create'">
				<strong>Ofertas </strong> Crear
			</h1>
			<h1 class="custom-font" style="margin-top: -10px !important;"
				ng-if="vm.location[2] == 'edit'">
				<strong>Ofertas </strong> Editar
			</h1>
			<ol class="breadcrumb">
				<li><a href="#/home">Dashboard</a></li>
				<li><a href="#/vehicle">Ofertas</a></li>
				<li ng-if="vm.location[2] == 'create'" class="breadcrumb-active">Nueva
					Oferta</li>
				<li ng-if="vm.location[2] == 'edit'" class="breadcrumb-active">Editar
					Oferta</li>
			</ol>
		</div>

		<div class="row">
			<div class="col-md-12">

				<section class="tile">

					<!-- tile body -->
					<div class="tile-body">

						<form form-on-change="checkFields()" name='form' class=""
							role="form" ng-submit="savePromotion()">
							<div class="row">
								<div class="form-group customDatePickers col-sm-6">
									<div class="input-group date" id="beginDate" data-date=""
										data-date-format="dd/mm/yyyy" data-link-field="dtp_input2"
										data-link-format="dd/mm/yyyy">
										<label for="beginDate" class="control-label">Fecha de
											inicio</label> <input class="form-control" size="16" type="text"
											name="beginDate" placeholder="DD/MM/YYYY"
											ng-model="vm.promotion.beginPromotionDate" readonly required> <span
											class="input-group-addon customAddonSize"> <span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
								<div class="form-group customDatePickers col-sm-6">
									<div class="input-group date" id="endDate" data-date=""
										data-date-format="dd/mm/yyyy" data-link-field="dtp_input2"
										data-link-format="dd/mm/yyyy">
										<label for="beginDate" class="control-label">Fecha de
											finalizacion</label> <input class="form-control" size="16"
											type="text" name="beginDate" placeholder="DD/MM/YYYY"
											ng-model="vm.promotion.lastPromotionDate" readonly required> <span
											class="input-group-addon customAddonSize"> <span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.promotionCode.$dirty && form.promotionCode.$error.required }">
									<label for="promotionCode" class="control-label">*
										Codigo de promocion</label> <input type="text"
										ng-model="vm.promotion.promotionCode" name="promotionCode"
										id="promotionCode" class="form-control"
										placeholder="Codigo de promocion" required> <span
										ng-show="form.promotionCode.$dirty && form.promotionCode.$error.required"
										class="help-block">Es necesario ingresar un codigo de
										promocion</span>
									<div class="btn btn-blue" ng-click="generateText()">Generar</div>
								</div>
								<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.percentage.$dirty && form.percentage.$error.required }">
									<label for="percentage" class="control-label">*
										Porcentaje</label> <input type="number" ng-model="vm.promotion.percentage"
										name="percentage" id="percentage" class="form-control"
										placeholder="percentage" required> <span
										ng-show="form.percentage.$dirty && form.percentage.$error.required"
										class="help-block">Es necesario ingresar un valor para
										el percentage de descuento</span>
								</div>
							</div>
							<div class="row">
							<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.model.$dirty && form.model.$error.required }">
									<label for="model" class="control-label">Modelos con descuentos</label> <isteven-multi-select    
    input-model="vm.allModels"    
    output-model="vm.promotion.models"
    button-label="name year"
    item-label="brand.name name year"
    tick-property="unavailable"
>
</isteven-multi-select> <span
										ng-show="form.model.$dirty && form.model.$error.required"
										class="help-block">Modelo es requerida</span>
								</div>
							<div class="form-group col-sm-6" class="form-group"
									ng-class="{ 'has-error': form.branchOffice.$dirty && form.branchOffice.$error.required }">
									<label for="model" class="control-label">Sucursales con descuentos</label><isteven-multi-select    
    input-model="vm.allOffices"    
    output-model="vm.promotion.offices"
    button-label="name"
    item-label="name"
    tick-property="closed"
>
</isteven-multi-select> <span
										ng-show="form.branchOffice.$dirty && form.branchOffice.$error.required"
										class="help-block">Sucursal es requerido</span>
								</div>
							


							<div class="row">
								<div class="form-group col-sm-12" class="form-group">
									<label for="descriptionText" class="control-label">Descripcion</label>
									<textarea type="text" ng-model="vm.descriptionText"
										name="descriptionText" id="descriptionText" class="form-control"/>
								</div>
							</div>

							<!-- Buttons -->

							<div class="form-group text-right">
								<a href="#/vehicle" class="btn btn-lightred">Cancelar</a>
								<button type="submit" id="submit" ng-disabled="form.$invalid"
									class="btn  btn-orange">Crear</button>
							</div>

						</form>

					</div>

				</section>
			</div>
			<!-- /row -->
		</div>

	</div>

</section>
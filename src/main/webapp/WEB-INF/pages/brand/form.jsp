<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Marca </strong> Crear</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Dashboard</a></li>
                <li><a href="#/brand/list">Marcas</a></li>
                <li class="breadcrumb-active">Nueva Marca</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile body -->
                    <div class="tile-body">
            			
            			<form  form-on-change="checkFields()" name='form' class="" role="form" ng-submit="saveBrand()">

                        <div class="form-group" ng-class="{ 'has-error': form.name.$dirty && form.name.$error.required }">
                            <label for="name" class="control-label">* Nombre</label>
                            <input type="text" name="name" id="name" class="form-control" ng-model="vm.brand.name" placeholder="Nombre de la Marca" required>
                            <span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Nombre es requerido</span>
                            <span ng-show="vm.duplicated" class="help-block">Ya existe una marca con ese nombre</span>
                        </div>

                        <!-- Buttons -->

                        <div class="form-group text-right">
                            <a href="#/brand/list" class="btn btn-lightred">Cancelar</a>
                            <button type="submit" id="submit" ng-disabled="form.$invalid" class="btn  btn-orange">Crear</button>
                        </div>

                    </form>
            			
            		</div>
            	
            	</section>
            </div>
		<!-- /row -->
        </div>

    </div>

</section>
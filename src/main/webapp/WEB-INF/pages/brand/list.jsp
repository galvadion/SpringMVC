<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Marcas </strong></h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Marcas</li>
            </ol>
        </div>

		<div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                    	<h2 class="custom-font"><strong>Crear</strong></h2>
                    </div>
                    <!-- /tile header -->
                    
            		<!-- tile body -->
                    <div class="tile-body">
            			
            			<form  form-on-change="checkFields()" name='form' class="" role="form" ng-submit="saveBrand()">

                        <div class="form-group" ng-class="{ 'has-error': form.name.$dirty && form.name.$error.required }">
                            <label for="name" class="control-label">* Nombre</label>
                            <input type="text" name="name" id="name" class="form-control" ng-model="vm.brand.name" placeholder="Nombre de la Marca" ng-change="cleanMessagges();" required>
                            <span ng-show="form.name.$dirty && form.name.$error.required" class="help-block">Nombre es requerido</span>
                            <span ng-show="vm.error" class="help-block" style="color: red;">Ya existe una marca con ese nombre</span>
                            <span ng-show="vm.success" class="help-block" style="color: green;">Marca creada</span>
                        </div>

                        <!-- Buttons -->

                        <div class="form-group text-right">
                            <a class="btn btn-lightred" ng-click="cleanInput();cleanMessagges();">Cancelar</a>
                            <button type="submit" id="submit" ng-disabled="form.$invalid" class="btn  btn-orange">Crear</button>
                        </div>

                    </form>
            			
            		</div>
            	
            	</section>
            </div>
		<!-- /row -->
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                    	<h2 class="custom-font"><strong>Listado</strong></h2>
                    </div>
                    <!-- /tile header -->

                    <!-- tile body -->
                    <div class="tile-body table-custom p-0">
            			<div class="table-responsive">
            				
            				<table datatable="ng" class="table mb-0 table-custom" id="VehicleList" dt-options="vm.dtOptions" dt-column-defs="vm.DTColumnDefs">
                                <thead>
                                   <tr>
                                   		<th>Nombre</th>
                                        <th style="width:20px;"></th>
                                        <th style="width:90px;">Acciones</th>
                                        <th style="width:70px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="(key, value) in vm.allBrands">
                                    	<td>
                                            {{value.name}}
                                        </td>
                                        <td>
                                        </td>
                                        <td class="text-center plus" style="cursor: pointer;">
                                             <a title="Editar" ng-click="editBrand(value)">
                                             	<i class="fa fa-pencil"></i><br><small>Editar</small>
                                             </a>
                                        </td>
                                        <td class="text-center delete">
                                            <a doing-action="" href data-toggle="" ng-click="deleteBrand(value.id)" confirm-if="checked" confirm="Esta seguro, eliminar marca {{value.name}} ?" title="Eliminar">
                                                <i class="fa fa-times"></i><br />
                                                <small>Eliminar</small>
                                            </a>
                                        </td>
                                   	</tr>
                                </tbody>
                            </table>
                         </div>
            		</div>
            	
            	</section>
            </div>
		<!-- /row -->
        </div>

    </div>

</section>
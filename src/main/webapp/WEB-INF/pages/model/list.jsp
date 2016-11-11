<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Modelos </strong> Listado</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Modelos</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                    	<h2 class="custom-font"><strong>Gestion:</strong></h2>
                        <a href="#/model/create" class="btn btn-orange btn-rounded mb-10 right" style="margin: 0 2px 0 2px;">Nuevo Modelo</a>
                    </div>
                    <!-- /tile header -->

                    <!-- tile body -->
                    <div class="tile-body table-custom p-0">
            			<div class="table-responsive">
            				
            				<table datatable="ng" class="table mb-0 table-custom" id="ModelList" dt-options="vm.dtOptions" dt-column-defs="vm.DTColumnDefs">
                                <thead>
                                   <tr>
                                   		<th>Nombre</th>
                                        <th>Marca</th>
                                        <th>Categoria</th>
                                        <th>Año</th>
                                   		<th>Combustible</th>
                                   		<th>Pasajeros</th>
                                   		<th>Valijas</th>
                                   		<th>Cilindrada</th>
                                   		<th>Aire acondicionado</th>
                                   		<th>Transmisión</th>
                                   		<th>Seguro</th>
                                   		<th>Tanque</th>
                                        <th style="width:20px;"></th>
                                        <th style="width:90px;">Acciones</th>
                                        <th style="width:70px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="(key, value) in vm.allModels">
                                    	<td>
                                            {{value.name}}
                                        </td>
                                        <td>
                                            {{value.brand.name}}
                                        </td>
                                        <td>
                                            <p ng-if="value.category.id == 1">S</p>
                                            <p ng-if="value.category.id == 2">A</p>
                                            <p ng-if="value.category.id == 3">B</p>
                                            <p ng-if="value.category.id == 4">C</p>
                                            <p ng-if="value.category.id == 5">D</p>
                                        </td>
                                        <td>
                                            {{value.year}}
                                        </td>
                                        <td>
                                            {{value.fuel.fuelType}}
                                        </td>
                                        <td>
                                            {{value.passangers}}
                                        </td>
                                        <td>
                                            {{value.luggage}}
                                        </td>
                                        <td>
                                            {{value.cylinders}}
                                        </td>
                                        <td>
                                            <p ng-if="value.airConditioner">Tiene</p>
                                            <p ng-if="!value.airConditioner">No tiene</p>
                                        </td>
                                        <td>
                                            <p ng-if="value.transmission == 'M'">Manual</p>
                                            <p ng-if="value.transmission == 'A'">Automático</p>
                                        </td>
                                        <td>
                                            $ {{value.insurance}}
                                        </td>
                                        <td>
                                            {{value.fullTank}} lts.
                                        </td>
                                        <td class="text-center plus">
                                            
                                        </td>
                                        <td class="text-center plus">
                                             <a ng-href="#/model/edit/{{value.id}}" title="Edit">
                                             	<i class="fa fa-pencil"></i><br><small>Editar</small>
                                             </a>
                                        </td>
                                        <td class="text-center delete">
                                            <a doing-action="" href id="model-{{$index}}" href data-toggle="" ng-click="deleteModel(value,$index)" confirm-if="checked" confirm="Esta seguro, eliminar modelo nombre {{value.name}} ?" title="Eliminar">
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
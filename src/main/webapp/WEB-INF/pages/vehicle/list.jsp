<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Vehiculos </strong> Listado</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Vehiculos</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile" ng-show="vm.roladmin">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                    	<h2 class="custom-font"><strong>Gestion:</strong></h2>
                        <a href="#/vehicle/create" class="btn btn-orange btn-rounded mb-10 right" style="margin: 0 2px 0 2px;">Nuevo Vehiculo</a>
                    </div>
                    <!-- /tile header -->

                    <!-- tile body -->
                    <div class="tile-body table-custom p-0">
            			<div class="table-responsive">
            				
            				<table datatable="ng" class="table mb-0 table-custom" id="VehicleList" dt-options="vm.dtOptions" dt-column-defs="vm.DTColumnDefs">
                                <thead>
                                   <tr>
                                   		<th>Matricula</th>
                                        <th>Marca</th>
                                        <th>Modelo</th>
                                        <th>Estado</th>
                                        <th>Vencimiento Patente</th>
                                        <th style="width:20px;"></th>
                                        <th style="width:90px;">Acciones</th>
                                        <th style="width:70px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!--tr ng-repeat="(key, value) in allVehicles"-->
                                    <tr>
                                    	<td>
                                            SBF 562                                             
                                        </td>
                                        <td>
                                            Fiat                                             
                                        </td>
                                        <td>
                                            Uno                                             
                                        </td>
                                        <td>
                                            Disponible
                                        </td>
                                        <td>
                                            23/10/2017
                                        </td>
                                        <td class="text-center plus">
                                             <a ng-href="#/vehicle/{{value.id}}" title="Alquilar Vehiculo">
                                             	<i class="fa fa-automobile"></i><br><small>Alquilar</small>
                                             </a>
                                        </td>
                                        <td class="text-center plus">
                                             <a ng-href="#/vehicle/{{value.id}}" title="Editar">
                                             	<i class="fa fa-pencil"></i><br><small>Editar</small>
                                             </a>
                                        </td>
                                        <td class="text-center delete">
                                            <a doing-action="" href id="vehic-{{$index}}" href data-toggle="" ng-click="deleteVehicle(value,$index)" confirm-if="checked" confirm="Esta seguro, eliminar vehiculo id {{value.id}} ?" title="Eliminar">
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
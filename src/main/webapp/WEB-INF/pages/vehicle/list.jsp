<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>VehÝculos </strong> Listado</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">VehÝculos</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                    	<h2 class="custom-font"><strong>Gestion:</strong></h2>
                        <a ng-show="vm.roladmin" href="#/vehicle/create" class="btn btn-orange btn-rounded mb-10 right" style="margin: 0 2px 0 2px;">Nuevo VehÝculo</a>
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
                                        <th>Sucursal</th>
                                        <th>Vencimiento Patente</th>
                                        <th style="width:20px;"></th>
                                        <th style="width:90px;">Acciones</th>
                                        <th style="width:70px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="(key, value) in vm.allVehicles">
                                    	<td>
                                            <a ng-href="#/vehicle/details/{{value.id}}" title="Detalles">{{value.licensePlate}}
                                            </a>                                           
                                        </td>
                                        <td>
                                            {{value.model.brand.name}}                                             
                                        </td>
                                        <td>
                                            {{value.model.name}}                                             
                                        </td>
                                        <td>{{value.branchOffice.name}}
                                        </td>
                                        
                                        <td>
                                            {{value.licensePlateExpirationDate}}
                                        </td>
                                        <td class="text-center plus">
                                        	<a ng-href="#/vehicle/details/{{value.id}}" title="Detalles" ng-show="vm.roladmin">
                                             	<i class="fa fa-eye"></i><br><small>Detalles</small>
                                             </a>
                                        </td>
                                        <td class="text-center plus">
                                             <a ng-href="#/vehicle/edit/{{value.id}}" title="Editar" ng-show="vm.roladmin">
                                             	<i class="fa fa-pencil"></i><br><small>Editar</small>
                                             </a>
                                             <a ng-href="#/vehicle/details/{{value.id}}" title="Detalles" ng-show="vm.rolemployee">
                                             	<i class="fa fa-eye"></i><br><small>Detalles</small>
                                             </a>
                                        </td>
                                        <td class="text-center delete">
                                            <a doing-action="" href ng-click="deleteVehicle(value,$index)" confirm-if="checked" confirm="Esta seguro, eliminar vehÝculo id {{value.id}} ?" title="Eliminar" ng-show="vm.roladmin">
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
<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div ng-if="vm.location == 'employee'" class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Empleados </strong> Listado</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Empleados</li>
            </ol>
        </div>
        
        <div ng-if="vm.location == 'client'" class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Clientes </strong> Listado</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Clientes</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile" ng-show="vm.roladmin">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                    	<h2 class="custom-font"><strong>Gestion:</strong></h2>
                        <a ng-if="vm.location == 'client'" href="#/client/create" class="btn btn-orange btn-rounded mb-10 right" style="margin: 0 2px 0 2px;">Nuevo Cliente</a>
                        <a ng-if="vm.location == 'employee'" href="#/employee/create" class="btn btn-orange btn-rounded mb-10 right" style="margin: 0 2px 0 2px;">Nuevo Empleado</a>
                    </div>
                    <!-- /tile header -->

                    <!-- tile body -->
                    <div class="tile-body table-custom p-0">
            			<div class="table-responsive">
            				
            				<table datatable="ng" class="table mb-0 table-custom" id="UserList" dt-options="vm.dtOptions" dt-column-defs="vm.DTColumnDefs">
                                <thead>
                                   <tr>
                                   		<th>Nombre</th>
                                        <th>Apellido</th>
                                        <th>E-mail</th>
                                        <th>Dirección</th>
                                        <th>Teléfono</th>
                                        <th><span ng-if="vm.location == 'client'">Nacimiento</span></th>
                                        <th><span ng-if="vm.location == 'client'">Expiración de licencia</span></th>
                                        <th>Estado</th>
                                        <th style="width:20px;"></th>
                                        <th style="width:90px;">Acciones</th>
                                        <th style="width:70px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="(key, value) in vm.allUsers">
                                    	<td>
                                            {{value.name}}
                                        </td>
                                        <td>
                                            {{value.lastName}}
                                        </td>
                                        <td>
                                            {{value.email}}
                                        </td>
                                        <td>
                                            {{value.address}}
                                        </td>
                                        <td>
                                            {{value.phone}}
                                        </td>
                                        <td>
                                        	<span ng-if="vm.location == 'client'">
                                            	{{value.birthDate}}
                                            </span>
                                        </td>
                                        <td>
                                        	<span ng-if="vm.location == 'client'">
	                                            {{value.licenseExpirationDate}}
	                                        </span>
                                        </td>
                                        <td>
                                            {{value.active}}
                                        </td>
                                        <td class="text-center plus">
                                             <a ng-href="" title="Cambiar estado" ng-click="changeStatus(value.id, value.active)">
                                             	<i class="fa fa-pause"></i><br><small>Cambiar estado</small>
                                             </a>
                                        </td>
                                        <td class="text-center plus">
                                             <a ng-if="vm.location == 'client'" ng-href="#/client/edit/{{value.id}}" title="Editar">
                                             	<i class="fa fa-pencil"></i><br><small>Editar</small>
                                             </a>
                                             <a ng-if="vm.location == 'employee'" ng-href="#/employee/edit/{{value.id}}" title="Editar">
                                             	<i class="fa fa-pencil"></i><br><small>Editar</small>
                                             </a>
                                        </td>
                                        <td class="text-center delete">
                                            <a doing-action="" href data-toggle="" ng-click="deleteUser(value.id)" confirm-if="checked" confirm="Esta seguro, eliminar a {{value.name}} {{value.lastName}}?" title="Eliminar">
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
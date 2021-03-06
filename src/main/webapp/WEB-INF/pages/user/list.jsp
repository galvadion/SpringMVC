<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div ng-if="vm.location[1] == 'employee'" class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Empleados </strong> Listado</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Empleados</li>
            </ol>
        </div>
        
        <div ng-if="vm.location[1] == 'client'" class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Clientes </strong> Listado</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Clientes</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	<section class="tile" ng-show="vm.roladmin || vm.rolemployee">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                    	<h2 class="custom-font"><strong>Gestion:</strong></h2>
                        <a ng-if="vm.location[1] == 'client'" href="#/client/create" class="btn btn-orange btn-rounded mb-10 right" style="margin: 0 2px 0 2px;">Nuevo Cliente</a>
                        <a ng-if="vm.location[1] == 'employee'" href="#/employee/create" class="btn btn-orange btn-rounded mb-10 right" style="margin: 0 2px 0 2px;">Nuevo Empleado</a>
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
                                        <th>Direcci�n</th>
                                        <th>Tel�fono</th>
                                        <th><span ng-if="vm.location[1] == 'client'">Nacimiento</span><span ng-if="vm.location[1] == 'employee'">Sucursal</span></th>
                                        <th><span ng-if="vm.location[1] == 'client'">Expiraci�n de licencia</span></th>
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
                                        	<span ng-if="vm.location[1] == 'client'">
                                            	{{value.birthDate}}
                                            </span>
                                            <span ng-if="vm.location[1] == 'employee'">{{value.branchOffice.city}}</span>
                                        </td>
                                        <td>
                                        	<span ng-if="vm.location[1] == 'client'">
	                                            {{value.licenseExpirationDate}}
	                                        </span>
                                        </td>
                                        <td></td>
                                        <td class="text-center plus" style="cursor: pointer !important;">
                                             <a ng-if="vm.location[1] == 'client'" ng-href="#/client/edit/{{value.id}}" title="Editar">
                                             	<i class="fa fa-pencil"></i><br><small>Editar</small>
                                             </a>
                                             <a ng-if="vm.location[1] == 'employee'" ng-href="#/employee/edit/{{value.id}}" title="Editar">
                                             	<i class="fa fa-pencil"></i><br><small>Editar</small>
                                             </a>
                                        </td>
                                        <td class="text-center delete" style="cursor: pointer !important;">
                                            <a href ng-click="openDialog(value)" title="Eliminar">
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

<script type="text/ng-template" id="modalDialog">
	<div class="ngdialog-message modal-content">
	<div class="modal-header">
	<h3 class="modal-title custom-font">Eliminar</h3>
	</div>
	<div class="modal-body">
	Esta seguro, eliminar a <strong>{{vm.auxUser.name}} {{vm.auxUser.lastName}}</strong>?
	</div>                     
	<div class="modal-footer  ngdialog-buttons">
	<button type="button" class="ngdialog-button btn btn-lightred btn-ef btn-ef-4 btn-ef-4c" ng-click="closeThisDialog('button')"><i class="fa fa-arrow-left"></i>Cancelar</button>
	<button type="button" class="ngdialog-button btn btn-success btn-ef btn-ef-3 btn-ef-3c" ng-click="confirm();deleteUser(vm.auxUser.id)"><i class="fa fa-arrow-right"></i> Confirmar</button>
	</div>
	</div>
</script>
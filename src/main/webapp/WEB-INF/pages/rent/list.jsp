<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Alquileres </strong> Listado</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li class="breadcrumb-active">Alquileres</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile" ng-show="vm.roladmin || vm.rolemployee">
            		
            		<!-- tile body -->
                    <div class="tile-body table-custom p-0">
            			<div class="table-responsive">
            				
            				<table datatable="ng" class="table mb-0 table-custom" id="VehicleList" dt-options="vm.dtOptions" dt-column-defs="vm.DTColumnDefs">
                                <thead>
                                   <tr>
                                   		<th>Número de transacción</th>
                                   		<th>Email de cliente</th>
                                        <th>Vehículo</th>
                                        <th>Fecha de inicio</th>
                                        <th>Fecha de fin</th>
                                        <th>Oficina de origen</th>
                                        <th>Oficina de destino</th>
                                        <th>Devuelto</th>
                                        <th style="width:20px;"></th>
                                        <th style="width:90px;">Acciones</th>
                                        <th style="width:70px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="(key, value) in vm.allRents">
                                    	<td>
                                            {{value.book.transactionNr}}
                                        </td>
                                        <td>
                                            {{value.book.client.email}}
                                        </td>
                                        <td>
                                            {{value.book.vehicle.licensePlate}}
                                        </td>
                                        <td>
                                            {{value.rent.deliveryDate}}
                                        </td>
                                        <td ng-if="value.book.returned">
                                            {{value.rent.returnDate}}
                                        </td>
                                        <td ng-if="!value.book.returned">
                                            {{value.book.lastbookedDate}}
                                        </td>
                                        <td>
                                            {{value.book.originOffice.city}}
                                        </td>
                                        <td>
                                            {{value.book.endOffice.city}}
                                        </td>
                                        <td class="text-center plus">
                                            <i ng-if="value.book.returned" class="fa fa-check" style="color: green"> <p class="custom-font">Si</p></i>
                                            <i ng-if="!value.book.returned" class="fa fa-close" style="color: red"> <p class="custom-font">No</p></i>
                                        </td>
                                       
                                        <td class="text-center plus"></td>
                                        <td class="text-center plus">
                                             <a ng-href="#/rent/details/{{value.rent.id}}" title="Ver detalles">
                                             	<i class="fa fa-eye"></i><br><small>Ver detalles</small>
                                             </a>
                                        </td>
                                        <td class="text-center delete"></td>
                                   	</tr>
                                </tbody>
                            </table>
                         </div>
            		</div>
            	</section>
            	
            	<section class="tile" ng-show="vm.rolclient">
            		
            		<!-- tile body -->
                    <div class="tile-body table-custom p-0">
            			<div class="table-responsive">
            				
            				<table datatable="ng" class="table mb-0 table-custom" id="VehicleList" dt-options="vm.dtOptions" dt-column-defs="vm.DTColumnDefsClient">
                                <thead>
                                   <tr>
                                   		<th>Número de transacción</th>
                                        <th>Vehículo</th>
                                        <th>Fecha de inicio</th>
                                        <th>Fecha de fin</th>
                                        <th>Oficina de origen</th>
                                        <th>Oficina de destino</th>
                                        <th>Devuelto</th>
                                        <th style="width:20px;"></th>
                                        <th style="width:90px;">Acciones</th>
                                        <th style="width:70px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="(key, value) in vm.allRents">
                                    	<td>
                                            {{value.book.transactionNr}}
                                        </td>
                                        <td>
                                            {{value.book.vehicle.licensePlate}}
                                        </td>
                                        <td>
                                            {{value.rent.deliveryDate}}
                                        </td>
                                        <td ng-if="value.book.returned">
                                            {{value.rent.returnDate}}
                                        </td>
                                        <td ng-if="!value.book.returned">
                                            {{value.book.lastbookedDate}}
                                        </td>
                                        <td>
                                            {{value.book.originOffice.city}}
                                        </td>
                                        <td>
                                            {{value.book.endOffice.city}}
                                        </td>
                                        <td class="text-center plus">
                                            <i ng-if="value.book.returned" class="fa fa-check" style="color: green"> <p class="custom-font">Si</p></i>
                                            <i ng-if="!value.book.returned" class="fa fa-close" style="color: red"> <p class="custom-font">No</p></i>
                                        </td>
                                       
                                        <td class="text-center plus"></td>
                                        <td class="text-center plus">
                                             <a ng-href="#/rent/details/{{value.id}}" title="Ver detalles">
                                             	<i class="fa fa-eye"></i><br><small>Ver detalles</small>
                                             </a>
                                        </td>
                                        <td class="text-center delete"></td>
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
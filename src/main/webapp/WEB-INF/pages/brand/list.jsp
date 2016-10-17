<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Marcas </strong> Listado</h1>
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
                    	<h2 class="custom-font"><strong>Gestion Admin:</strong></h2>
                        <a href="#/brand/create" class="btn btn-orange btn-rounded mb-10 right" style="margin: 0 2px 0 2px;">Nueva Marca</a>
                    </div>
                    <!-- /tile header -->

                    <!-- tile body -->
                    <div class="tile-body table-custom p-0">
            			<div class="table-responsive">
            				
            				<table datatable="ng" class="table mb-0 table-custom" id="VehicleList" dt-options="vm.dtOptions" dt-column-defs="vm.DTColumnDefs">
                                <thead>
                                   <tr>
                                   		<th>Id</th>
                                        <th>Nombre</th>
                                        <th style="width:20px;"></th>
                                        <th style="width:90px;">Acciones</th>
                                        <th style="width:70px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!--tr ng-repeat="(key, value) in allBrands"-->
                                    <tr>
                                    	<td>
                                            1
                                        </td>
                                        <td>
                                            Fiat
                                        </td>
                                        <td>
                                        </td>
                                        <td class="text-center plus">
                                             <a ng-href="#/brand/{{value.id}}" title="Editar">
                                             	<i class="fa fa-pencil"></i><br><small>Editar</small>
                                             </a>
                                        </td>
                                        <td class="text-center delete">
                                            <a doing-action="" href id="brand-{{$index}}" href data-toggle="" ng-click="deleteBrand(value,$index)" confirm-if="checked" confirm="Esta seguro, eliminar marca {{value.name}} ?" title="Eliminar">
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
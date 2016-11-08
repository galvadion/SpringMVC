<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="row">
			<div class="col-md-12">
				<section class="tile text-center slyder">
					
<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="http://www.autostlalpan.com/wp-content/uploads/2016/05/09042016sliderventa-autos-tlalpan-seminuevos-carros-usados-160409014-142-1280x720.jpg" alt="Chania">
    </div>

    <div class="item">
      <img src="https://i.ytimg.com/vi/QaocTfH4hYM/maxresdefault.jpg" alt="Chania">
    </div>

    <div class="item">
      <img src="https://image.jimcdn.com/app/cms/image/transf/none/path/sb171f10b2887615a/image/i1e0ff4564417b3c4/version/1419699490/image.jpg" alt="Flower">
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>



				</section>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<section class="tile" style="height: 400px !important;">
					<br/>
					<h3 class="text-center">Encuentra tu veh�culo</h3>
					<br/><br/>
					<form name='form' class="" role="form" ng-submit="searchModels()">
						
						<div class="row" style="padding: 0 10px 0 10px;">
						
							<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="beginDate" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
									<label for="beginDate" class="control-label">Fecha de recogida</label>
									<input class="form-control" size="16" type="text" name="beginDate" placeholder="YYYY-MM-DD" ng-model="vm.search.beginDate" readonly required ng-change="checkEndDate()">
									<span class="input-group-addon customAddonSize"><span class="glyphicon glyphicon-calendar"></span></span>
		                        </div>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeOriginId.$dirty && form.officeOriginId.$error.required }">
	                            <label for="officeOriginId" class="control-label">Sucursal de recogida</label>
	                            <select ng-model="vm.search.officeOriginId" name="officeOriginId" id="officeOriginId" class="form-control" required>
									<option value=""> Seleccione sucursal</option> 
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
								<span ng-show="form.officeOriginId.$dirty && form.officeOriginId.$error.required" class="help-block">Sucursal es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <div class="row" style="padding: 0 10px 0 10px;">
	                    
	                    	<div class="form-group customDatePickers col-sm-6">
								<div class="input-group date" id="endDate" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
									<label for="endDate" class="control-label">Fecha de entrega</label>
									<input class="form-control" size="16" type="text" name="endDate" placeholder="YYYY-MM-DD" ng-model="vm.search.endDate" readonly required ng-change="checkEndDate()">
									<span class="input-group-addon customAddonSize"><span class="glyphicon glyphicon-calendar"></span></span>
		                        </div>
		                        <span ng-show="endDateError" class="help-block" style="color: red">Fecha de entrega debe ser posterior a la de recogida</span>
	                        </div>
	
							<div class="form-group col-sm-6" class="form-group" ng-class="{ 'has-error': form.officeEndId.$dirty && form.officeEndId.$error.required }">
	                            <label for="officeEndId" class="control-label">Sucursal de entrega</label>
	                            <select ng-model="vm.search.officeEndId" name="officeEndId" id="officeEndId" class="form-control" required>
									<option value=""> Seleccione sucursal</option> 
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
								<span ng-show="form.officeEndId.$dirty && form.officeEndId.$error.required" class="help-block">Sucursal es requerido</span>
	                        </div>
	                    </div>
	                    
	                    <br/><br/>
	                    
	                    <!-- Buttons -->
                        <div class="form-group text-right" style="margin-right: 15px;">
                            <button type="submit" id="submit" ng-disabled="form.$invalid" class="btn  btn-orange">Buscar ahora</button>
                        </div>
		        	</form>
					
				</section>
			</div>
			
			<!--div class="col-md-1"></div-->
			
			<div class="col-md-6">
				<section class="tile text-center">
					<ui-gmap-google-map center="map.center" zoom="map.zoom" draggable="true" options="options">
                    	<ui-gmap-markers models="markers" coords="'self'" icon="'icon'">
					</ui-gmap-google-map>
				</section>
			</div>
		</div>

		<br/>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile header -->
                    <div class="tile-header dvd dvd-btm">
                        <h1 class="custom-font"><strong>Listado </strong>de promociones</h1>
                    </div>
                    <!-- /tile header -->

                    <!-- tile body -->
                    <div class="tile-body table-custom">
                    	
						<div class="table-responsive">
            				
            				<table class="table mb-0 table-custom" id="promoList">
                                <tbody>
                                    <tr ng-repeat="(key, value) in vm.allPromos">
                                    	<td>
                                            {{value.name}}
                                        </td>
                                        <td>
                                            {{value.picture}}
                                        </td>
                                        <td>
                                            {{value.fuel.fuelType}}
                                        </td>
                                        <td>
                                            {{value.year}}
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
                                            {{value.airConditioner}}
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
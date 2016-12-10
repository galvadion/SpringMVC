    <!-- ===============================================
        ================= HEADER Content ===================
        ================================================ -->
        <section id="header" style="background-color: #EDEDED;" class="row">

            <header class="clearfix">

                	<nav class="navbar navbar-default nav-custom">
	                	<div class="container-fluid">
	                		<div class="navbar-header">
						      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						        <span class="icon-bar"></span>
						        <span class="icon-bar"></span>
						        <span class="icon-bar"></span>
								<span class="icon-bar"></span>
							  </button>
								
							  <!-- Branding -->
				              <a class="brand navbar-brand" ng-href="#/home" style="margin-left:5px !important;margin-right:25px !important;">
				              	<img src="static/images/logo-wide-transparent.png">
				              </a>
				              <!-- Branding end -->

						    </div>
						    <div class="collapse navbar-collapse" id="myNavbar">
							    <ul class="nav navbar-nav nav-navigation" id="">
							      <li ng-class=getClass('home') class=""><a href="#/home"><span class="nav-custom-font">Inicio</span></a></li>
							      <li ng-show="vm.roladmin" ng-class=getClass('brand') class=""><a href="#/brand"><span class="nav-custom-font">Marcas</span></a></li>
							      <li ng-show="vm.roladmin" ng-class=getClass('model') class=""><a href="#/model"><span class="nav-custom-font">Modelos</span></a></li>
							      <li ng-show="!vm.roladmin" ng-class=getClass('search') class=""><a href="#/search"><span class="nav-custom-font">Buscar</span></a></li>
							      <li ng-class=getClass('vehicle') class=""><a href="#/vehicle"><span class="nav-custom-font">Vehículos</span></a></li>
							      <li ng-class=getClass('branchoffice') class=""><a href="#/branchoffice"><span class="nav-custom-font">Sucursales</span></a></li>
							      <li ng-show="vm.roladmin" ng-class=getClass('employee') class=""><a href="#/employee"><span class="nav-custom-font">Empleados</span></a></li>
							      <li ng-show="vm.roladmin || vm.rolemployee" ng-class=getClass('client') class=""><a href="#/client"><span class="nav-custom-font">Clientes</span></a></li>
							      <li ng-show="vm.roladmin" ng-class=getClass('tariff') class=""><a href="#/tariff"><span class="nav-custom-font">Tarifas</span></a></li>
							      <li ng-show="vm.roladmin" ng-class=getClass('promotion') class=""><a href="#/promotion"><span class="nav-custom-font">Promociones</span></a></li>
							      <li ng-show="vm.roladmin" ng-class=getClass('booked') class=""><a href="#/booked"><span class="nav-custom-font">Reservas</span></a></li>
							      <li ng-show="vm.roladmin" ng-class=getClass('rent') class=""><a href="#/rent"><span class="nav-custom-font">Alquileres</span></a></li>
							      <li ng-show="!vm.roladmin || !vm.rolemployee" ng-class=getClass('about') class=""><a href="#/about"><span class="nav-custom-font">Nosotros</span></a></li>
							    </ul>
							    <ul class="nav navbar-nav navbar-right nav-navigation" ng-if="globals.currentUser">
							        <li ng-class=getClass('profile') class="dropdown">
							        	<a class="dropdown-toggle" data-toggle="dropdown" href="">
							        		<span class="nav-custom-font">{{globals.currentUser.name}} </span>
	                                		<img src="static/images/avatar.png" alt="" class="img-circle" style="height: 22px; width: 22px;">
							        		<span class="caret"></span>
							        	</a>
							        	<ul class="dropdown-menu">
								            <li><a href="#/profile">Perfil</a></li>
								            <li><a ng-show="vm.rolclient" href="#/booked">Mis reservas</a></li>
								            <li><a ng-show="vm.rolclient" href="#/rent">Historial Alquileres</a></li>
								            <li><a ng-click="logOut()" href="#/">Salir</a></li>
								        </ul>
									</li>
							    </ul>
							    <ul class="nav navbar-nav navbar-right nav-navigation" ng-if="!globals.currentUser">
							        <li ng-class=getClass('register')>
							        	<a href="#/register" class="text-default">
			                                <span class="nav-custom-font">Registrarse</span>
			                            </a>
									</li>
							        <li ng-class=getClass('login')>
							        	<a href="#/login" class="text-default">
			                                <span class="nav-custom-font">Ingresar</span>
			                            </a>
									</li>
							    </ul>
						    </div>
						</div>
					</nav>
                
            </header>

        </section>
        <!--/ HEADER Content  -->


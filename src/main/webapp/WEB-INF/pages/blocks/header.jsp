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
							      <li ng-class=getClass('vehicle') class=""><a href="#/vehicle/list"><span class="nav-custom-font">Coches</span></a></li>
							      <li ng-class=getClass('offices') class=""><a href="#/offices"><span class="nav-custom-font">Sucursales</span></a></li>
							      <li ng-class=getClass('about') class=""><a href="#/about"><span class="nav-custom-font">Nosotros</span></a></li>
							    </ul>
							    <ul class="nav navbar-nav navbar-right nav-navigation" ng-if="1==1">
							        <li ng-class=getClass('profile') class="dropdown">
							        	<a class="dropdown-toggle" data-toggle="dropdown" href="">
							        		<span class="nav-custom-font">Pepe{{globals.currentUser.username}} </span>
	                                		<img src="static/images/avatar.png" alt="" class="img-circle" style="height: 22px; width: 22px;">
							        		<span class="caret"></span>
							        	</a>
							        	<ul class="dropdown-menu">
								            <li><a href="#/profile">Perfil</a></li>
								            <li><a ng-click="logOut()" href="#/">Salir</a></li>
								          </ul>
									</li>
							    </ul>
							    <ul class="nav navbar-nav navbar-right nav-navigation" ng-if="1==2">
							        <li>
							        	<a href="#/register" class="text-default">
			                                <span class="nav-custom-font">Registrarse</span>
			                            </a>
									</li>
							        <li>
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


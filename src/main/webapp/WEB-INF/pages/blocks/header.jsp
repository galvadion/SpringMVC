    <!-- ===============================================
        ================= HEADER Content ===================
        ================================================ -->
        <section id="header" style="background-color: white" class="row">

            <header class="clearfix">

                
                <!-- Right-side navigation -->
                <!--div class="col-xs-6 col-sm-6 col-md-3 right">
                    <ul class="nav-right pull-right list-inline">

                        <li class="dropdown nav-profile">
                        	<div ng-if="true">

	                            <a href class="dropdown-toggle text-default" data-toggle="dropdown">
	                            	<span>PEPE{{globals.currentUser.username}}<i class="fa fa-angle-down"></i></span>
	                                <img src="{{globals.currentUser.avatarURL}}" alt="" class="img-circle size-30x30"--
	                                <img src="static/images/avatar.png" alt="" class="img-circle size-30x30">>
	                            </a>
	
	                            <ul class="dropdown-menu animated littleFadeInRight" role="menu">
	
	                                <li>
	                                    <a role="button" href="#/profile/{{globals.currentUser.id}}" tabindex="0">
	
	                                        <i class="fa fa-user"></i>Perfil
	                                    </a>
	                                </li>
	                                <li class="divider"></li>
	
	                                <li>
	                                   <a href="#/logout" role="button" tabindex="0">
	                                        <i class="fa fa-sign-out"></i>Salir
	                                    </a>
	                                </li>
	                            </ul>
                            </div>
                            
                            <div ng-if="false">
                            	<a href class="text-default">
	                                <img src="{{globals.currentUser.avatarURL}}" alt="" class="img-circle size-30x30">
	                                <span class="nav-custom-font">Registrarse</span>
	                            </a>
                            </div>
                        </li>
                    </ul>
                </div-->

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
				              	<img src="static/images/logo-nav.png">
				              </a>
				              <!-- Branding end -->

						    </div>
						    <div class="collapse navbar-collapse" id="myNavbar">
							    <ul class="nav navbar-nav nav-navigation" id="">
							      <li ng-class=getClass('#/home') class="active"><a href="#/home"><span class="nav-custom-font">Inicio</span></a></li>
							      <li ng-class=getClass('/') class=""><a href="#/home"><span class="nav-custom-font">Opcion 2</span></a></li>
							      <li ng-class=getClass('/') class=""><a href="#/home"><span class="nav-custom-font">Opcion 3</span></a></li>
							      <li ng-class=getClass('/') class=""><a href="#/home"><span class="nav-custom-font">Opcion 4</span></a></li>
							    </ul>
							    <ul class="nav navbar-nav navbar-right nav-navigation">
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


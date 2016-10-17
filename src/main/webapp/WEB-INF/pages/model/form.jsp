<ng-include src="'blocks/header'"></ng-include>

<section id="content">


    <div class="page page-dashboard">

        <div class="pageheader">
        	<h1 class="custom-font" style="margin-top: -10px !important;"><strong>Modelos </strong> Crear</h1>
            <ol class="breadcrumb">
                <li><a href="#/home">Inicio</a></li>
                <li><a href="#/model/list">Modelos</a></li>
                <li class="breadcrumb-active">Nuevo Modelo</li>
            </ol>
        </div>

        <div class="row">
        	<div class="col-md-12">
            	
            	<section class="tile">
            		
            		<!-- tile body -->
                    <div class="tile-body">
            			
            			<form  form-on-change="checkFields()" name='form' class="" role="form" ng-submit="saveAdv()">

                        <div class="form-group" ng-class="{ 'has-error': form.advertiserName.$dirty && form.advertiserName.$error.required }">
                            <label for="advertiserName" class="control-label">* Name</label>
                            <input type="text" name="advertiserName" id="advertiserName" class="form-control" id="advertiserName" ng-model="vm.adv.advertiserName" placeholder="Name" required>
                            <span ng-show="form.advertiserName.$dirty && form.advertiserName.$error.required" class="help-block">Advertiser Name is required</span>
                        </div>

                        <div class="form-group" ng-class="{ 'has-error': form.contactName.$dirty && form.contactName.$error.required }">
                            <label for="contactName" class="control-label">* Contact</label>
                            <input type="text" class="form-control" name="contactName" id="contactName" class="form-control" id="contactName" ng-model="vm.adv.contactName" placeholder="Contact" required>
                            <span ng-show="form.contactName.$dirty && form.contactName.$error.required" class="help-block">Contact Name is required</span>
                        </div>


                        <div class="form-group" ng-class="{ 'has-error': (form.emailAddress.$dirty && form.emailAddress.$error.required ) || form.emailAddress.$error.email}">

                            <label for="emailAddress" class="control-label">* Email</label>
                            <input type="email" class="form-control" name="emailAddress" id="emailAddress" class="form-control" ng-model="vm.adv.emailAddress" placeholder="Enter Email" required>
                            <span ng-show="form.emailAddress.$dirty && form.emailAddress.$error.required" class="help-block">Email is required</span>
                            <span ng-show="form.emailAddress.$dirty && form.emailAddress.$error.email" class="help-block">Not valid Email</span>
                        </div>


                        <div class="row">
                            <div class="form-group col-md-6" ng-class="{ 'has-error': form.address1.$dirty && form.address1.$error.required }">
                                <label for="address1" class="control-label">* Address 1</label>
                                <input type="text" name="address1" id="address1" class="form-control" id="address1" ng-model="vm.adv.address1" placeholder="Address 1"  required>
                                <span ng-show="form.address1.$dirty && form.address1.$error.required" class="help-block">Advertiser Address is required</span>
                            </div>
                            
                            <div class="form-group col-md-6">
                                <label for="address2" class="control-label">Address 2</label>
                                <input type="text" class="form-control" name="address2" id="address2" class="form-control" id="address2" placeholder="Address 2" ng-model="vm.adv.address2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-3" ng-class="{ 'has-error': form.country.$dirty && form.country.$error.required }">
                                <label for="country" class="control-label">* Country</label>
                                <select name="country" id="country" ng-model="vm.adv.country" class="form-control" ng-change="countryFilter()" required>
                                    <option ng-repeat="(key,value) in vm.allCountry" value="{{value.id}}">{{value.name}}</option>
                                </select>
                                <!--<input type="text" name="country" id="country" class="form-control" id="country" ng-model="vm.adv.country" required>-->
                                <span ng-show="form.country.$dirty && form.country.$error.required" class="help-block">Advertiser Country is required</span>
                            </div>


                            <div class="form-group col-md-3" ng-class="{ 'has-error': form.state.$dirty && form.state.$error.required }">
                                <label for="state" class="control-label">* State</label>
                                <select name="state" id="state" ng-model="vm.adv.state" class="form-control">
                                    <option ng-repeat="(key,value) in vm.filteredState" value="{{value.id}}">{{value.name}}</option>
                                </select>
                                <!--<input type="text" name="state" id="state" class="form-control" id="state" ng-model="vm.adv.state" required>
                                <span ng-show="form.state.$dirty && form.state.$error.required" class="help-block">Advertiser State is required</span>-->
                            </div>

                            <div class="form-group col-md-3" ng-class="{ 'has-error': form.city.$dirty && form.city.$error.required }">
                                <label for="city" class="control-label">* City</label>
                                <input type="text" name="city" id="city" class="form-control" id="city" ng-model="vm.adv.city" placeholder="City"  required>
                                <span ng-show="form.city.$dirty && form.city.$error.required" class="help-block">Advertiser City is required</span>
                            </div>

                            <div class="form-group col-md-3" ng-class="{ 'has-error': form.zipcode.$dirty && form.zipcode.$error.required }">
                                <label for="zipcode" class="control-label">* Zip Code</label>
                                <input type="number" name="zipcode" id="zipcode" class="form-control" id="zipcode" ng-model="vm.adv.zipcode" placeholder="Zip Code" required>
                                <span ng-show="form.zipcode.$dirty && form.zipcode.$error.required" class="help-block">Advertiser Zip Code is required</span>
                            </div>
                        </div>


                        <div class="form-group" ng-class="{ 'has-error': form.phone.$dirty && form.phone.$error.required }">
                            <label for="phone" class="control-label">* Phone Number</label>
                            <input type="number" name="phone" id="phone" class="form-control" id="phone" ng-model="vm.adv.phone" placeholder="Phone Number" required>
                            <span ng-show="form.phone.$dirty && form.phone.$error.required" class="help-block">Advertiser Phone is required</span>
                        </div>

                      <!-- Commercial Contact -->
                        <div class="form-group col-md-12 legend"> <h4><strong>Commercial</strong> Contact</h4><p></p> </div>

                        <div class="row">
                            <div class="form-group col-md-6" ng-class="{ 'has-error': form.commercialName.$dirty && form.commercialName.$error.required }">
                                <label for="commercialName" class="control-label">* Name</label>
                                <input type="text" name="commercialName" id="commercialName" class="form-control" id="commercialName" ng-model="vm.adv.commercial.name" placeholder="Name" required>
                                <span ng-show="form.commercialName.$dirty && form.commercialName.$error.required" class="help-block">Name is required</span>
                            </div>

                            <div class="form-group col-md-6"  ng-class="{ 'has-error': form.commercialLastname.$dirty && form.commercialLastname.$error.required }">
                                <label for="commercialLastname" class="control-label">* Last Name</label>
                                <input type="text" name="commercialLastname" id="commercialLastname" class="form-control" id="commercialLastname" ng-model="vm.adv.commercial.lastName" placeholder="Last Name" required>
                                <span ng-show="form.commercialLastname.$dirty && form.commercialLastname.$error.required" class="help-block">Last name is required</span>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-6" ng-class="{ 'has-error': (form.commercialEmail.$dirty && form.commercialEmail.$error.required ) || form.commercialEmail.$error.email}">

                                <label for="commercialEmail" class="control-label">* Email</label>
                                <input type="email" class="form-control" name="commercialEmail" id="commercialEmail" class="form-control" ng-model="vm.adv.commercial.email" placeholder="Enter Email" required>
                                <span ng-show="form.commercialEmail.$dirty && form.commercialEmail.$error.required" class="help-block">Email is required</span>
                                <span ng-show="form.commercialEmail.$dirty && form.commercialEmail.$error.email" class="help-block">Not valid Email</span>
                            </div>

                            <div class="form-group col-md-6" ng-class="{ 'has-error': form.commercialPhone.$dirty && form.commercialPhone.$error.required }">
                                <label for="commercialPhone" class="control-label">* Phone Number</label>
                                <input type="number" name="commercialPhone" id="commercialPhone" class="form-control" id="commercialPhone" ng-model="vm.adv.commercial.phone" placeholder="Phone Number" required>
                                <span ng-show="form.commercialPhone.$dirty && form.commercialPhone.$error.required" class="help-block">Phone is required</span>
                            </div>
                        </div>
                      <!-- /Commercial Contact -->

                      <!-- Finance Contact -->
                        <div class="form-group col-md-12 legend"> <h4><strong>Finance</strong> Contact</h4><p></p> </div>

                        <div class="row">
                            <div class="form-group col-md-6" ng-class="{ 'has-error': form.financeName.$dirty && form.financeName.$error.required }">
                                <label for="financeName" class="control-label">* Name</label>
                                <input type="text" name="financeName" id="financeName" class="form-control" id="financeName" ng-model="vm.adv.finance.name" placeholder="Name" required>
                                <span ng-show="form.financeName.$dirty && form.financeName.$error.required" class="help-block">Name is required</span>
                            </div>

                            <div class="form-group col-md-6"  ng-class="{ 'has-error': form.financeLastname.$dirty && form.financeLastname.$error.required }">
                                <label for="financeLastname" class="control-label">* Last Name</label>
                                <input type="text" name="financeLastname" id="financeLastname" class="form-control" id="financeLastname" ng-model="vm.adv.finance.lastName" placeholder="Last Name" required>
                                <span ng-show="form.financeLastname.$dirty && form.financeLastname.$error.required" class="help-block">Last name is required</span>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-6" ng-class="{ 'has-error': (form.financeEmail.$dirty && form.financeEmail.$error.required ) || form.financeEmail.$error.email}">

                                <label for="financeEmail" class="control-label">* Email</label>
                                <input type="email" class="form-control" name="financeEmail" id="financeEmail" class="form-control" ng-model="vm.adv.finance.email" placeholder="Enter Email" required>
                                <span ng-show="form.financeEmail.$dirty && form.financeEmail.$error.required" class="help-block">Email is required</span>
                                <span ng-show="form.financeEmail.$dirty && form.financeEmail.$error.email" class="help-block">Not valid Email</span>
                            </div>

                            <div class="form-group col-md-6" ng-class="{ 'has-error': form.financePhone.$dirty && form.financePhone.$error.required }">
                                <label for="financePhone" class="control-label">* Phone Number</label>
                                <input type="number" name="financePhone" id="financePhone" class="form-control" id="financePhone" ng-model="vm.adv.finance.phone" placeholder="Phone Number" required>
                                <span ng-show="form.financePhone.$dirty && form.financePhone.$error.required" class="help-block">Phone is required</span>
                            </div>
                        </div>
                      <!-- /Finance Contact -->

                        <!-- Buttons -->

                        <div class="form-group text-right">
                            <a href="#/advertiser" class="btn btn-lightred">Cancel</a>
                            <button type="submit" id="submit" ng-disabled="form.$invalid || vm.saveAdvFlag == true" class="btn  btn-orange">Save changes</button>
                            <img ng-if="vm.adv.dataLoading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
                        </div>

                    </form>
            			
            		</div>
            	
            	</section>
            </div>
		<!-- /row -->
        </div>

    </div>

</section>
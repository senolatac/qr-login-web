    <div class="container">    
        <div id="signupbox" style="max-width:430px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2  login-custom-box">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">Sign Up</div>
                            <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="/">Sign In</a></div>
                        </div>  
                       
                        <div style="padding:40px;" class="panel-body" ng-controller="UserController as ctrl">
                            <form id="signupform" class="form-horizontal" name="myForm" role="form" ng-submit="myForm.$valid && ctrl.register()">
                                
								<div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                				<div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                                  
                                <div class="form-group" ng-class="{'has-error has-feedback': myForm.email.$dirty && myForm.email.$invalid, 'has-success has-feedback': myForm.email.$dirty && myForm.email.$valid  }">
                                    <div class="col-md-12">
                                        <label for="email" class="control-label">Email:</label>
                                        <input type="email" class="form-control" name="email" ng-model="ctrl.user.email" placeholder="Email Address" required maxlength="100">
                                        <span ng-show="myForm.email.$dirty && myForm.email.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
            							<span ng-show="myForm.email.$dirty && myForm.email.$invalid" class="glyphicon glyphicon-remove  form-control-feedback"></span>
	                                  <div class="has-error" ng-show="myForm.$dirty && myForm.email.$touched">
	                                      <span ng-show="myForm.email.$error.required">This field is required.</span>
	                                      <span ng-show="myForm.email.$invalid">Email format is invalid. </span>
	                                  </div>            							
                                    </div>

                                </div>
                                    
                                <div class="form-group"  ng-class="{'has-error has-feedback': myForm.ssoId.$dirty && myForm.ssoId.$invalid, 'has-success has-feedback': myForm.ssoId.$dirty && myForm.ssoId.$valid  }">
                                    <div class="col-md-12">
                                        <label for="ssoId" class="control-label">Username:</label>
                                        <input type="text" class="form-control has-feedback" name="ssoId" ng-model="ctrl.user.ssoId" placeholder="Username" required  ng-minlength="3">
                                         <span ng-show="myForm.ssoId.$dirty && myForm.ssoId.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
            							<span ng-show="myForm.ssoId.$dirty && myForm.ssoId.$invalid" class="glyphicon glyphicon-remove  form-control-feedback"></span>
	                                  <div class="has-error" ng-show="myForm.$dirty && myForm.ssoId.$touched">
	                                      <span ng-show="myForm.ssoId.$error.required">This field is required.</span>
	                                      <span ng-show="myForm.ssoId.$error.minlength">Username should be at least min 3 letters.</span>
	                                  </div>                                
                                    </div>
                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span> 
                                </div>
                                <div class="form-group"  ng-class="{'has-error has-feedback': myForm.name.$dirty && myForm.name.$invalid, 'has-success has-feedback': myForm.name.$dirty && myForm.name.$valid  }">
                                    <div class="col-md-12">
                                        <label for="name" class="control-label">Full Name:</label>
                                        <input type="text" class="form-control" name="name" ng-model="ctrl.user.name" placeholder="Name" required  ng-minlength="3">
                                         <span ng-show="myForm.name.$dirty && myForm.name.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
            							<span ng-show="myForm.name.$dirty && myForm.name.$invalid" class="glyphicon glyphicon-remove  form-control-feedback"></span>
	                                  <div class="has-error" ng-show="myForm.$dirty && myForm.name.$touched">
	                                      <span ng-show="myForm.name.$error.required">This field is required.</span>
	                                      <span ng-show="myForm.name.$error.minlength">Name should be at least min 3 letters.</span>
	                                  </div>                                          
                                    </div>
                                </div>
                                <div class="form-group"  ng-class="{'has-error has-feedback': myForm.password.$dirty && myForm.password.$invalid, 'has-success has-feedback': myForm.password.$dirty && myForm.password.$valid  }">
                                    <div class="col-md-12">
                                        <label for="password" class="control-label">Password:</label>
                                        <input type="password" class="form-control" name="password" ng-model="ctrl.user.password" placeholder="Password" required  ng-minlength="3">
                                         <span ng-show="myForm.password.$dirty && myForm.password.$valid" class="glyphicon glyphicon-ok form-control-feedback"></span>
            							<span ng-show="myForm.password.$dirty && myForm.password.$invalid" class="glyphicon glyphicon-remove  form-control-feedback"></span>
	                                  <div class="has-error" ng-show="myForm.$dirty && myForm.password.$touched">
	                                      <span ng-show="myForm.password.$error.required">This field is required.</span>
	                                      <span ng-show="myForm.password.$error.minlength">Password should be at least min 3 letters.</span>
	                                  </div>                                         
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-12">
                                        <button id="btn-signup" type="submit" class="btn btn-info  btn-block" ng-disabled="ctrl.submitted || myForm.$invalid">
                                        <i ng-show="!ctrl.submitted" class="fa fa-hand-o-right" aria-hidden="true"></i> 
                                        <span ng-show="ctrl.submitted"><i class="glyphicon glyphicon-refresh spinning" aria-hidden="true"></i></span> 
                                        &nbsp Sign Up
                                        </button>
                                    </div>
                                </div>
                                
                                
                                
                                
                            </form>
                         </div>
                    </div>
		          <footer style="text-align:center;">
		                <div class="row">
		                    <div class="col-md-12">
		                        <div class="center-block" style="margin-top:10px;">&copy;All rights are reserved by Senol Atac.</div>
		                    </div>
		                </div>
		        </footer> 
         </div> 
    </div>

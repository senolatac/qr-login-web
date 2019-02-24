 <%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 <style>
 
 body {
 background-color: #efefef;
}
 </style>
    <div class="container">    
        <div id="loginbox" style="max-width: 446px;" class="login-custom-box">                    
            <div class="panel" >

                    <div style="padding-top:30px" class="panel-body"  ng-controller="UserController as ctrl">

								<div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                				<div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                            
                        <form id="loginform" class="form-horizontal login-form" role="form" name="myForm" ng-submit="myForm.$valid && ctrl.login()">
							<div>
								<img src="/resources/images/logo.png" alt="" class="img-responsive" style="margin: auto;"/>
								<h4 class="panel-title-custom" style="text-align:center; color: #b4b4b4; line-height: 20px; display: block;"></h4>
							</div>                        
							<div>
								
							</div>	                        
                                    
                            <div style="margin-bottom: 25px" class="input-group" ng-class="{'has-error has-feedback': myForm.email.$dirty && myForm.email.$invalid, 'has-success has-feedback': myForm.email.$dirty && myForm.email.$valid  }">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" class="form-control input-lg" style="border-radius: 0 5px 5px 0px;" name="email" ng-model="ctrl.user.email" placeholder="Username or email" required> 
                                    </div>
	                                  <div class="has-error" ng-show="myForm.$dirty && myForm.email.$touched">
	                                      <span ng-show="myForm.email.$error.required">This field is required.</span>
	                                  </div>                                       
                                
                            <div style="margin-bottom: 25px" class="input-group" ng-class="{'has-error has-feedback': myForm.password.$dirty && myForm.password.$invalid, 'has-success has-feedback': myForm.password.$dirty && myForm.password.$valid  }">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control input-lg" name="password" style="border-radius: 0 5px 5px 0px;" ng-model="ctrl.user.password" placeholder="Password" required=>
                                    </div>
	                                  <div class="has-error" ng-show="myForm.$dirty && myForm.password.$touched">
	                                      <span ng-show="myForm.password.$error.required">This field is required.</span>
	                                  </div>
	                                  
                            <div class="input-group">
                                      <div class="checkbox">
										<label class="switch">
										  <input type="checkbox" ng-model="ctrl.user.rememberMe">
										  <span class="slider round"></span>
										</label>                                      
                                        <label class="remmber-label">
                                          Remember Me
                                        </label>
                                      </div>
                             </div>    


                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                      <button id="btn-login" type="submit" class="btn btn-lg btn-primary btn-block" ng-disabled="ctrl.submitted || myForm.$invalid">
                                      <span ng-show="ctrl.submitted"><i class="glyphicon glyphicon-refresh spinning" aria-hidden="true"></i></span> 
                                      Login</button>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; text-align: center;" >
										<div>
											If you don't have account? <a href="#!/register">Register</a>
										</div>
										<div>Mobile <a href="#!/mobile">Login With QR Code</a></div>
                                        </div>
                                    </div>
                                </div>    
                            </form>     



                        </div>                     
                    </div>  
		          <footer style="text-align:center;">
		                <div class="row">
		                    <div class="col-md-12">
		                        <div class="center-block" style="margin-top:10px;color: #000;">&copy;All right reserved by Senol Atac.</div>
		                    </div>
		                </div>
		        </footer>                    
        </div>
    </div>
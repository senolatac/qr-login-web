<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html lang="en" ng-app="myApp">
<jsp:include page="../back/header.jsp" />

  <body class="nav-md">
    <div class="container body">
      <div class="main_container"  ng-controller="UserMController as ctrl">
      

        
        <!-- page content -->
        <div class="right_col pageEnable" style="position: relative; display: block; height: 80vh;">
                <div >
			<div class="e-loadholder">
			  <div class="m-loader">
				  <div>
	                <span>Welcome,</span>
	                <h2><c:out value="${visitor.sessionUser.name}"/></h2>
	              </div>
			    <a href="/logout"><i class="fa fa-sign-out pull-right"></i> Logout</a>
			  </div>
			</div>
		</div> 
		
          <div class="">
            <div class="page-title">
              <div class="title_left">
				  <div class="alert alert-success alert-dismissable" ng-if="ctrl.successMessage && !ctrl.isModal">
				    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				    <span class="glyphicon glyphicon-ok"></span> <strong>{{'success' | translate}}</strong> {{ctrl.successMessage}}
				  </div>  
				  <div class="alert alert-danger alert-dismissable" ng-if="ctrl.errorMessage && !ctrl.isModal">
				    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				    <span class="glyphicon glyphicon-hand-right"></span> <strong>{{'error' | translate}}</strong> {{ctrl.errorMessage}}
				  </div> 				              
                <h3 class="langEnable">{{'users' | translate}}</h3>
              </div>

            </div>
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                
			        <div class="panel panel-primary filterable">
			            <div class="table-responsive"> 
			            <table class="table table-striped">
			                <thead>
			                    <tr class="filters">
			                        <th style="width: 40px;"><input type="text" class="form-control" placeholder="#" disabled></th>
			                        <th><input type="text" class="form-control" placeholder="Name" disabled></th>
			                        <th><input type="text" class="form-control" placeholder="Username" disabled></th>
			                        <th><input type="text" class="form-control" placeholder="Email" disabled></th>
			                        <th><input type="text" class="form-control" placeholder="Role" disabled></th>
			                        <th><input type="text" class="form-control" placeholder="Created Date" disabled></th>
			                        <th style="width: 150px;"></th>
			                    </tr>
			                </thead>
			                <tbody>
			                    <tr ng-repeat="u in ctrl.users | orderBy:ctrl.orderByField:ctrl.reverseSort" data-status="{{u.distributor?0:(u.company?1:3)}}">
			                        <td>{{ $index + 1 }}</td>
			                        <td>{{ u.name }}</td>
			                        <td>{{ u.ssoId }}</td>
			                        <td>{{ u.email }}</td>
			                        <td>{{u.enumProfileType | translate}}</td>
			                        <td>{{ u.createDate |  date:'MM/dd/yyyy'}}</td>
			                    </tr>
			                    <tr class="text-center" ng-show="!ctrl.users || ctrl.users.length==0"><td colspan="7">{{ 'noRecord' |translate }}</td></tr>
			                </tbody>
			            </table>
			            </div>
			        </div>
                </div>
              </div>         
        </div>
</div>
</div>        
        <!-- /page content -->
        
        <!-- footer content -->
        <footer>
          <div class="pull-right">
            <a href="https://www.udemy.com/user/senol-atac/">Senol Atac</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

<jsp:include page="../back/footer.jsp" />
    <script src="<c:url value='../../resources/js/service/user_m_service.js' />"></script>
    <script src="<c:url value='../../resources/js/controller/user_m_controller.js' />"></script>
	
  </body>
</html>
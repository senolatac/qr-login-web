<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html lang="en" ng-app="myApp">
<title>Qr Login - Home</title>
<jsp:include page="../back/header.jsp" />

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
      
        <div class="right_col pageEnable" style="position: relative; display: block; height: 80vh;">
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
  </body>
</html>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html lang="tr" ng-app="myApp">
<jsp:include page="public/back/header.jsp" />
    <body ng-controller="MainController">
      <ng-view></ng-view>
<jsp:include page="public/back/footer.jsp" />
  </body>
</html>    
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;url=/home" />
    <meta name="HandheldFriendly" content="true"/>
    <title>QR Code Login</title> 
	<link href="<c:url value='/resources/bower_components/ng-tags-input/ng-tags-input.min.css' />" rel="stylesheet"></link>	
    <link href="<c:url value='/resources/css/common.css' />" rel="stylesheet"></link>
    <link href="/resources/css/custom.min.css" rel="stylesheet">    
    <script src="/resources/js/lib/jquery.min.js"></script>
    <script src="/resources/js/util/common.js"></script> 
</head>
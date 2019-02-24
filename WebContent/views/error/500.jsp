<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Internal Server Error</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,900&amp;subset=latin-ext" rel="stylesheet"/>
        <link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet"></link>
<style>
.error-template {padding: 40px 15px;text-align: center;}
.error-actions {margin-top:15px;margin-bottom:15px;}
.error-actions .btn { margin-right:10px; }
</style>
</head>
<body>
<div class="container">
    <div class="row">
    <div class="error-template">
	    <h1>Oops!</h1>
	    <h2>500 Internal Server Error.</h2>
	    <div class="error-details">
		Sorry, an error has occured, Internal Server Error!<br>
	    </div>
	    <div class="error-actions">
		<a href="/" class="btn btn-primary">
		    <i class="fa fa-home"></i> Take Me Home </a>
		<a href="mailto:me@null-byte.info" class="btn btn-default">
		    <i class="fa fa-envelope"></i> Contact Support </a>
	    </div>
	</div>
    </div>
</div>
</body>
</html>
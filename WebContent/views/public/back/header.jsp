 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>QR Code Login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="/resources/js/lib/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="<c:url value='/resources/css/common.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,900&amp;subset=latin-ext" rel="stylesheet"/>
        <link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet"></link> 
        <script src="/resources/js/jms/sockjs-0.3.4.js"></script>
	    <script src="/resources/js/jms/stomp.js"></script>
	    <script src="/resources/js/jms/jquery.qrcode.min.js"></script>
    </head>
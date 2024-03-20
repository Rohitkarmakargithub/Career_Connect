<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>
    <%@ include file="all_component/all_css.jsp" %>
    <style>
        body {
            background-color: black;
            color: white;
        }

        .highlight1, .highlight2 {
            display: inline-block;
            text-shadow: 0 0 10px rgba(255, 252, 51, 0.8), 0 0 10px rgba(51, 134, 255, 0.8); /* Adjust the glow effect */
        }

        .highlight2 {
            color: yellow; /* Change the color to yellow */
        }
    </style>
</head>
<body>

<c:if test="${userobj.role ne 'admin' }">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>

<%@include file="all_component/navbar.jsp"%>
<div class="container-fluid">
<br>
<br>
    <div class="text-center">
        <h1 class="display-4">
            <span class="highlight1">C</span>
            <span class="highlight2">a</span>
            <span class="highlight1">r</span>
            <span class="highlight2">e</span>
            <span class="highlight1">e</span>
            <span class="highlight2">r</span>
            &nbsp;
            <span class="highlight1">C</span>
            <span class="highlight2">o</span>
            <span class="highlight1">n</span>
            <span class="highlight2">n</span>
            <span class="highlight1">e</span>
            <span class="highlight2">c</span>
            <span class="highlight1">t</span>
        </h1>
        <h1 class="text-black p-4 highlight1">Welcome Admin</h1>
    </div>
</div>

</body>
</html>

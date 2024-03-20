<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
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
            color: blue;
        }
    </style>
</head>

<body  style="margin: 0; padding: 0; background: linear-gradient(to bottom, #000000, #282828);">

	<br>
    <div class="container-fluid">
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
      </div>
	<div class= "continer-fluid">
    	<div class="row p-5">
        	<div class= "col-md-4 offset-md-4">
           		<div class="card" style="background-color: rgba(40, 40, 40, 0.8); backdrop-filter: blur(10px); color: #fff;">
               		<div class="card-body">
                   		<div class="text-center">
                       		<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
                   		</div>
                   		                   			<br>
                 
                   		<c:if test="${not empty succMsg }">
   							<h4 class="text-center text-danger">${succMsg }</h4>
   							<c:remove var="succMsg"/>
						</c:if>
                   		<form action="login" method="post">


                       		<div class="form-group">
                          		<label>Enter Email</label> <input type="email" required="required" class="form-control"
                              	id="exampLeInputEmail1" aria-describedby="emailHelp" name="email" placeholder="Enter Email">
                       		</div>
                       		<div class="form-group">
    							<label for= "exampleInputPassword1">Enter Password</label> <input
        						required= "required" type= "password" class= "form-control"
        						id="exampleInputPasswerd1" name="password"  placeholder="Enter Password">
							</div>
						
							<button type="submit" class= "btn btn-primary badge-pill btn-block">Login</button>
							<a href="signup.jsp" class="btn  badge-pill btn-block" style="background:#292929;">New User?Register</a>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
                       
	
</body>
</html>
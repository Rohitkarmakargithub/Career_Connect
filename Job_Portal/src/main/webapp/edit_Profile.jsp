<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Profile Page</title>
    <%@ include file="all_component/all_css.jsp"%>
</head>
<body style="background-color: #f7f7f7;">

<c:if test="${empty userobj}">
    <c:redirect url="login.jsp" />
</c:if>
<%@ include file="all_component/navbar.jsp"%>

<div class="container-fluid">
    <div class="row p-4">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body">
                    <div class="text-center">
                        <form action="update_profile" method="post">
                            <input type="hidden" name="id" value="${userobj.id}">

                            <div class="form-group">
                                <label for="fullNameInput">Enter Full Name</label>
                                <input type="text" required="required" class="form-control" id="fullNameInput"
                                       aria-describedby="emailHelp" name="name" value="${userobj.name}">
                            </div>

                            <div class="form-group">
                                <label for="qualificationInput">Enter Qualification</label>
                                <input type="text" required="required" class="form-control" id="qualificationInput"
                                       aria-describedby="emailHelp" name="qua" value="${userobj.qualification}">
                            </div>

                            <div class="form-group">
                                <label for="emailInput">Enter Email</label>
                                <input type="email" required="required" class="form-control" id="emailInput"
                                       aria-describedby="emailHelp" name="email" value="${userobj.email}">
                            </div>

                            <div class="form-group">
                                <label for="passwordInput">Enter Password</label>
                                <input required="required" type="password" class="form-control"
                                       id="passwordInput" name="ps" value="${userobj.password}">
                            </div>
						
    							
							


                            <button type="submit" class="btn btn-primary">Update Profile</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

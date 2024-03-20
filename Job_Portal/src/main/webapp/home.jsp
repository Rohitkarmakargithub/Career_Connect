<%@ page import="java.util.List" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.dao.JobDAO" %>
<%@ page import="com.dao.UserDAO" %>
<%@ page import="com.entity.Jobs" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="com.entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User: View Jobs</title>
    <%@ include file="all_component/all_css.jsp" %>
</head>
<body style="background-color: #fef1f2;">

<c:if test="${empty userobj}">
    <c:redirect url="Login.jsp" />
</c:if>

<%@ include file="all_component/navbar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h5 class="text-center text-primary">All Jobs</h5>
            
            <div class="card">
                <div class="card-body">
                    <form class="form-inline" action="more_view.jsp" method="get">
                        <div class="form-group col-md-5 mt-1">
                            <h5>Location</h5>
                        </div>
                        
                        <div class="form-group col-md-4 mt-1">
                            <h5>Category</h5>
                        </div>

                        <div class="form-group col-md-5">
                            <select name="loc" class="custom-select" id="inlineFormcustomselectPref">
                                <option selected value="Lo">Choose...</option>
                                <option value="Odisha">Odisha</option>
                                <option value="Jharkhand">Jharkhand</option>
                                <option value="Gujarat">Gujarat</option>
                                <option value="Bhubaneswar">Bhubaneswar</option>
                                <option value="Delhi">Delhi</option>
                                <option value="Bangalore">Bangalore</option>
                                <option value="Chennai">Chennai</option>
                                <option value="Hyderabad">Hyderabad</option>
                            </select>
                        </div>

                        <div class="form-group col-md-5">
                            <select class="custom-select" id="inlineFormcustomSelectPref" name="cat">
                                <option selected value="ca">Choose...</option>
                                <option value="IT">IT</option>
                                <option value="Developer">Developer</option>
                                <option value="Banking">Banking</option>
                                <option value="Engineering">Engineering</option>
                                <option value="Teacher">Teacher</option>
                            </select>
                        </div>

                        <button class="btn btn-success">Submit</button>
                    </form>

                    <% 
                        JobDAO dao = new JobDAO(DBConnect.getConn());
                        List<Jobs> list = dao.getAllJobsForUser();
                        for (Jobs j : list) {
                    %>
                        <div class="card mt-2">
                            <div class="card-body">
                                <div class="text-center text-primary">
                                    <i class="far fa-clipboard fa-2x"></i>
                                </div>

                                <h6><%= j.getTitle() %></h6>

                                <% if (j.getDescription().length() > 0 && j.getDescription().length() < 120) { %>
                                    <p><%=j.getDescription() %></p>
                                <% } else { %>
                                    <p><%=j.getDescription().substring(0, 120) %></p>
                                <% } %>
                                <br>

                                <div class="form-row">
                                    <div class="form-group col-md-3">
                                        <input type="text" class="form-control form-control-sm" value="Location: <%= j.getLocation() %>" readonly>
                                    </div>

                                    <div class="form-group col-md-3">
                                        <input type="text" class="form-control form-control-sm" value="Category: <%= j.getCategory() %>" readonly>
                                    </div>
                                </div>

                                <h6>Publish Date: <%= j.getPdate().toString() %></h6>
<div class="text-center">
    <%
    	UserDAO u=new UserDAO(DBConnect.getConn());
        int userId = ((User) session.getAttribute("userobj")).getId();
        String applyStatusFromDatabase = u.getUserById(userId).getApplyStatus();
    %>

    <form action="apply" method="get" style="display: inline;" onsubmit="handleApplySubmit(event, <%= j.getId() %>)">
        <input type="hidden" name="userId" value="<%= userId %>">
        <input type="hidden" name="id" value="<%= j.getId() %>">

        <% 
            if (applyStatusFromDatabase != null && applyStatusFromDatabase.contains(String.valueOf(j.getId()))) { 
        %>
            <button id="appliedButton_<%= j.getId() %>" type="button" class="btn btn-sm bg-success text-white" disabled>Applied</button>
        <% } else { %>
            <button id="applyButton_<%= j.getId() %>" type="submit" class="btn btn-sm bg-success text-white">Apply</button>
        <% } %>
    </form>

    <a href="one_view.jsp?id=<%= j.getId() %>" class="btn btn-sm bg-success text-white" style="display: inline;">View More</a>
</div>





                            </div>
                        </div>

                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

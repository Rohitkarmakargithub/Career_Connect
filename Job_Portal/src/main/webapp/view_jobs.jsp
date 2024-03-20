<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dao.JobDAO" %>
<%@ page import="com.entity.Jobs" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Jobs</title>
    <%@ include file="all_component/all_css.jsp" %>
    <style>
        .btn-group-bottom {
            margin-bottom: 10px;
        }
    </style>
</head>
<body style="background-color:#f7f7f7;">

    <%@ include file="all_component/navbar.jsp" %>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h5 class="text-center text-primary">All Jobs</h5>
                <c:if test="${not empty succMsg}">
                    <div class="alert alert-success" role="alert">${succMsg}</div>
                    <c:remove var="succMsg" />
                </c:if>
                <div class="card mt-2">
                    <div class="card-body">
                        <div class="text-center text-primary">
                            <i class="far fa-clipboard fa-2x"></i>
                        </div>

                        <% 
                           JobDAO dao = new JobDAO(DBConnect.getConn());
                           List<Jobs> list = dao.getAllJobs();
                           for (Jobs j : list) { 
                        %>

                            <h6><%= j.getTitle() %></h6>
                            <p><%= j.getDescription() %></p>
                            <br>

                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <input type="text" class="form-control" value="Location: <%= j.getLocation() %>" readonly>
                                </div>

                                <div class="form-group col-md-3">
                                    <input type="text" class="form-control" value="Category: <%= j.getCategory() %>" readonly>
                                </div>

                                <div class="form-group col-md-3">
                                    <input type="text" class="form-control" value="Status: <%= j.getStatus() %>" readonly>
                                </div>
                            </div>

                            <h6>Publish Date: <%= j.getPdate() %></h6>
                            <div class="text-center btn-group-bottom">
                                <a href="edit_job.jsp?id=<%=j.getId() %>" class="btn btn-sm bg-success text-white">edit</a>
                                <a href="delete?id=<%=j.getId() %>" class="btn btn-sm bg-danger text-white">delete</a>
                            </div>
                            <div style="marginBottom:10px"></div>
                            
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

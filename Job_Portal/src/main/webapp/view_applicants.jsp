<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Applicants</title>
    <style>
        table {
            width: 50%;
            margin: auto;
            text-align: center;
            font-size: 18px;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }
    </style>
</head>
<body>

    <%-- Establish a database connection --%>
    <%
        try {
            Connection conn = DBConnect.getConn();
            String sql = "SELECT user.id as user_id, user.name, user.email, jobs.id as job_id, jobs.title FROM user "
                       + " JOIN jobs ON FIND_IN_SET(jobs.id, user.apply_status)";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                %>
                <table>
                    <tr>
                        <th>User ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Job ID</th>
                        <th>Job Title</th>
                    </tr>
                    <%
                    while (rs.next()) {
                        int userId = rs.getInt("user_id");
                        String userName = rs.getString("name");
                        String userEmail = rs.getString("email");
                        int jobId = rs.getInt("job_id");
                        String jobTitle = rs.getString("title");
                        %>
                        <tr>
                            <td><%= userId %></td>
                            <td><%= userName %></td>
                            <td><%= userEmail %></td>
                            <td><%= jobId %></td>
                            <td><%= jobTitle %></td>
                        </tr>
                        <%
                    }
                    %>
                </table>
                <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

</body>
</html>

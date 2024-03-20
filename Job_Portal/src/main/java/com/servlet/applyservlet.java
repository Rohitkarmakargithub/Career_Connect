package com.servlet;

import java.io.IOException;
import java.util.Arrays;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/apply")
public class applyservlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int jobId = Integer.parseInt(request.getParameter("id"));

        UserDAO userDAO = new UserDAO(DBConnect.getConn());
        User user = userDAO.getUserById(userId);
        String applyStatus = user.getApplyStatus();
        if (applyStatus == null || applyStatus.isEmpty()) {
            applyStatus = String.valueOf(jobId);
        } else {
            applyStatus = applyStatus + "," + jobId;
        }
        user.setApplyStatus(applyStatus);

        if (userDAO.updateUser(user)) {
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}

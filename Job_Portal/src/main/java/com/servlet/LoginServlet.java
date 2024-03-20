package com.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;  // Change this import
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            HttpSession session = req.getSession();
            User user = new User();

            if ("admin@gmail.com".equals(email) && "admin".equals(password)) {
                session.setAttribute("userobj", user);
                user.setRole("admin");
                resp.sendRedirect("admin.jsp");
            } else {
                UserDAO dao = new UserDAO(DBConnect.getConn());
                user = dao.login(email, password);
                if (user != null) {
                    session.setAttribute("userobj", user);
                    resp.sendRedirect("home.jsp");
                } else {
                    session.setAttribute("succMsg", "invalid credentials");
                    resp.sendRedirect("login.jsp");
                }
            }

            

        } catch (Exception e) {
            resp.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
}

package com.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt; // Import BCrypt

@WebServlet("/update_profile")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String qua = req.getParameter("qua");
            String email = req.getParameter("email");
            String password = req.getParameter("ps");
            String applyStat = req.getParameter("apply_stat"); 


            UserDAO dao = new UserDAO(DBConnect.getConn());
            User u = new User();
            u.setId(id);
            u.setEmail(email);
            u.setName(name);
            u.setPassword(password);
            u.setQualification(qua);
            u.setApplyStatus(applyStat); 

            boolean f = dao.updateUser(u);

            HttpSession session = req.getSession();

            if (f) {
                session.setAttribute("successMsg", "Profile updated successfully");
                resp.sendRedirect("home.jsp");
            } else {
                session.setAttribute("errorMsg", "Something went wrong on the server");
                resp.sendRedirect("home.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


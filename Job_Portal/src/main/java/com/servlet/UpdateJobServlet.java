package com.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.dao.JobDAO;
import com.entity.Jobs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateJobServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String location = req.getParameter("location");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String desc = req.getParameter("desc");

            Jobs j=new Jobs();
            j.setId(id);
            j.setTitle(title);
            j.setDescription(desc);
            j.setLocation(location);
            j.setStatus(status);
            j.setCategory(category);
            HttpSession session = req.getSession();

            JobDAO dao = new JobDAO(DBConnect.getConn());

            boolean f = dao.updateJob(j);

            if (f) {
                session.setAttribute("succMsg", "Job updated successfully!");
                resp.sendRedirect("view_jobs.jsp");  // Redirect to a success page or any other page
            } else {
                session.setAttribute("succMsg", "Error occurred while adding job!");
                resp.sendRedirect("view_jobs.jsp");  // Redirect to an error page or the same page with an error message
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

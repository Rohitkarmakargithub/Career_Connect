package com.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Transport;
import javax.mail.*;
import javax.mail.internet.*;


import com.DB.DBConnect;
import com.dao.JobDAO;
import com.dao.UserDAO;
import com.entity.Jobs;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add_job")
public class AddPostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String title = req.getParameter("title");
            String location = req.getParameter("location");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String desc = req.getParameter("desc");

            Jobs j = new Jobs();
            j.setTitle(title);
            j.setDescription(desc);
            j.setLocation(location);
            j.setStatus(status);
            j.setCategory(category);

            HttpSession session = req.getSession();
            JobDAO jobDao = new JobDAO(DBConnect.getConn());
            UserDAO userDao = new UserDAO(DBConnect.getConn());

            boolean jobAdded = jobDao.addJobs(j);

            if (jobAdded) {
                session.setAttribute("succMsg", "Job added successfully!");
                List<User> users = userDao.getUsersWithJobNotifications();
                for (User user : users) {
                    String userEmail = user.getEmail();
                    
                    String subject = "New Job Opportunity: " + title;
                    String message = "A new job opportunity has been added:\n\n" +
                                     "Title: " + title + "\n" +
                                     "Location: " + location + "\n" +
                                     "Category: " + category + "\n" +
                                     "Description: " + desc + "\n\n" +
                                     "Visit our website for more details.";

                    // Send email notification to user
                    sendEmail(userEmail, subject, message);
                }

                resp.sendRedirect("add_job.jsp"); 
            } else {
                session.setAttribute("succMsg", "Error occurred while adding job!");
                resp.sendRedirect("add_job.jsp");  
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendEmail(String userEmail, String subject, String message) {
     
        String from = "rohitkarmakar2203@gmail.com";
        String password = "lieg awri xssx ubzh";

        String to = userEmail;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a new email message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            // Send the email
            Transport.send(mimeMessage);
            System.out.println("Email sent successfully to: " + userEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

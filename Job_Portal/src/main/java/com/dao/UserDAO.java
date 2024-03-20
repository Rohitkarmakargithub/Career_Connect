package com.dao;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.entity.User;

public class UserDAO {

    private Connection conn;
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public UserDAO(Connection conn) {
        super();
        this.conn = conn;
    }


    public boolean addUser(User user) {
        String sql = "INSERT INTO user (name, qualification, email, password, role) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (isEmailAlreadyRegistered(user.getEmail())) {
                logger.info("Email already registered: " + user.getEmail());
                return false; 
            }
            
            // Hash the password
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            ps.setString(1, user.getName());
            ps.setString(2, user.getQualification());
            ps.setString(3, user.getEmail());
            ps.setString(4, hashedPassword);
            ps.setString(5, "user");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isEmailAlreadyRegistered(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE email=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; 
                }
            }
        }
        return false;
    }

    public User login(String email, String password) {
        User u = null;
        try {
            String sql = "SELECT * FROM user WHERE email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password")); 
                    u.setQualification(rs.getString("qualification"));
                    u.setRole(rs.getString("role"));
                    u.setApplyStatus(rs.getString("apply_status"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean updateUser(User u) {
        boolean f = false;
        try {
            String sql = "UPDATE user SET name=?, qualification=?, email=?, password=?, apply_status=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setString(2, u.getQualification());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getApplyStatus());  
            ps.setInt(6, u.getId());

            int i = ps.executeUpdate();

            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public User getUserById(int userId) {
        User user = null;
        try {
            String sql = "SELECT * FROM user WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setQualification(rs.getString("qualification"));
                user.setRole(rs.getString("role"));
                user.setApplyStatus(rs.getString("apply_status"));  // Set apply_status
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public List<User> getUsersWithJobNotifications() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user"; // Retrieve all users
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                // Populate user object with data from result set
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


}


package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Jobs;

public class JobDAO {

    private Connection conn;

    public JobDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public boolean addJobs(Jobs j) {
        boolean f = false;
        try {
            
            String sql = "insert into jobs (title,description,category,status,location) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
        
            ps.setString(1, j.getTitle());
            ps.setString(2, j.getDescription());
            ps.setString(3, j.getCategory());
            ps.setString(4, j.getStatus());
            ps.setString(5, j.getLocation());
      
            int i = ps.executeUpdate();
            
            if (i == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
    public List<Jobs> getAllJobs() {
        List<Jobs> list = new ArrayList<>();
        Jobs j = null;

        try {
            String sql = "SELECT * FROM jobs ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                j = new Jobs();
                j.setId(rs.getInt(1));
                j.setTitle(rs.getString(2));
                j.setDescription(rs.getString(3));
                j.setCategory(rs.getString(4));
                j.setStatus(rs.getString(5));
                j.setLocation(rs.getString(6));
                j.setPdate(rs.getTimestamp(7)+"");
                

                list.add(j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public Jobs getJobById(int id) {
       
        Jobs j = null;

        try {
            String sql = "SELECT * FROM jobs where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                j = new Jobs();
                j.setId(rs.getInt(1));
                j.setTitle(rs.getString(2));
                j.setDescription(rs.getString(3));
                j.setCategory(rs.getString(4));
                j.setStatus(rs.getString(5));
                j.setLocation(rs.getString(6));
                j.setPdate(rs.getTimestamp(7)+"");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return j;
    }
    public boolean updateJob(Jobs j) {
        boolean f = false;
        try {
            String sql = "UPDATE jobs SET title=?, description=?, category=?, status=?, location=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, j.getTitle());
            ps.setString(2, j.getDescription());
            ps.setString(3, j.getCategory());
            ps.setString(4, j.getStatus());
            ps.setString(5, j.getLocation());
            ps.setInt(6, j.getId()); 

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
    public boolean deleteJob(int id) {
        boolean f = false;

        try {
            String sql = "DELETE FROM jobs WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
    public List<Jobs> getAllJobsForUser() {
        List<Jobs> list = new ArrayList<>();
        Jobs j = null;

        try {
            String sql = "SELECT * FROM jobs where status=? ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Active");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                j = new Jobs();
                j.setId(rs.getInt(1));
                j.setTitle(rs.getString(2));
                j.setDescription(rs.getString(3));
                j.setCategory(rs.getString(4));
                j.setStatus(rs.getString(5));
                j.setLocation(rs.getString(6));
                j.setPdate(rs.getTimestamp(7)+"");
                

                list.add(j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Jobs> getJobsAndLocationAndCate(String category, String location) {
        List<Jobs> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM jobs WHERE category=? AND location=? ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            ps.setString(2, location);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jobs j = new Jobs();
                j.setId(rs.getInt(1));
                j.setTitle(rs.getString(2));
                j.setDescription(rs.getString(3));
                j.setCategory(rs.getString(4));
                j.setStatus(rs.getString(5));
                j.setLocation(rs.getString(6));
                j.setPdate(rs.getString(7));
                list.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<Jobs> getJobsORLocationAndCate(String category, String location) {
        List<Jobs> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM jobs WHERE category=? OR location=? ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            ps.setString(2, location);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Jobs j = new Jobs();
                j.setId(rs.getInt(1));
                j.setTitle(rs.getString(2));
                j.setDescription(rs.getString(3));
                j.setCategory(rs.getString(4));
                j.setStatus(rs.getString(5));
                j.setLocation(rs.getString(6));
                j.setPdate(rs.getString(7));
                list.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}

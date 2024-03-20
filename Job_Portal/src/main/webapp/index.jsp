<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection" %>
<%@ page import="com.DB.DBConnect" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CareerConnect</title>
    <%@ include file="all_component/all_css.jsp" %>
    <style>
        body {
            background-color: black;
            color: white;
        }

        .highlight1, .highlight2 {
            display: inline-block;
            text-shadow: 0 0 10px rgba(255, 252, 51, 0.8), 0 0 10px rgba(51, 134, 255, 0.8); /* Adjust the glow effect */
        }

        .highlight2 {
            color: blue;
        }
    </style>
    
</head>

<body>
    <%@include file="all_component/navbar.jsp" %>

    <div class="container mt-5">
        <div class="text-center">
            <h1 class="display-4">
                <span class="highlight1">C</span>
                <span class="highlight2">a</span>
                <span class="highlight1">r</span>
                <span class="highlight2">e</span>
                <span class="highlight1">e</span>
                <span class="highlight2">r</span>
                &nbsp;
                <span class="highlight1">C</span>
                <span class="highlight2">o</span>
                <span class="highlight1">n</span>
                <span class="highlight2">n</span>
                <span class="highlight1">e</span>
                <span class="highlight2">c</span>
                <span class="highlight1">t</span>
            </h1>
            <p class="lead">
                <span class="highlight1">F</span>
                <span class="highlight2">i</span>
                <span class="highlight1">n</span>
                <span class="highlight2">d</span>
                &nbsp;
                <span class="highlight1">y</span>
                <span class="highlight2">o</span>
                <span class="highlight1">u</span>
                <span class="highlight2">r</span>
                &nbsp;
                <span class="highlight1">j</span>
                <span class="highlight2">o</span>
                <span class="highlight1">b</span>
                <span class="highlight2">s</span>
                &nbsp;
                <span class="highlight1">h</span>
                <span class="highlight2">e</span>
                <span class="highlight1">r</span>
                <span class="highlight2">e</span>
                <span class="highlight1">!</span>
            </p>
            <br>
            <br>
            <h4 >ABOUT US</h4>
            <p class="text-justify">
                At Career Connect, we believe in connecting individuals with their dream careers. Our platform is designed to bridge the gap between talented professionals and exciting job opportunities. With a commitment to empowering career growth, we provide a user-friendly interface that enables users to explore, apply, and secure their ideal jobs. Our mission is to foster a community where both employers and job seekers thrive, creating a seamless and rewarding experience in the journey towards success. Join Career Connect today and embark on a path that transforms aspirations into achievements.
            </p>
        </div>
    </div>
    
</body>
</html>

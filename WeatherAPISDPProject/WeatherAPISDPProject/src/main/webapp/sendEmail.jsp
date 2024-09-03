<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/style.css"> 
    <meta charset="UTF-8">
    <title>WEATHER EXPLORER</title>
</head>
<body>
<%@ include file="adminnavbar.jsp" %>
<div class="container">
   <center> <h1>Notify Users</h1> </center>

    <form action="/send-email" method="post">
        <label for="location">Location:</label>
        <select name="location" id="location">
            <c:forEach items="${locations}" var="location">
                <option value="${location}">${location}</option>
            </c:forEach>
        </select>
        <br>

        <label for="subject">Email Subject:</label>
        <input type="text" name="subject" id="subject" required>
        <br>

        <label for="message">Email Message:</label>
        <textarea name="message" id="message" rows="4" required></textarea>
        <br>

        <button type="submit">Send Email</button>
    </form>

    <h2>Registered Users in Selected Location</h2>
    <ul>
        <c:forEach items="${users}" var="user">
            <li>${user.name} - ${user.email}</li>
        </c:forEach>
    </ul>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/style.css"> 
    <meta charset="UTF-8">
    <title>WEATHER EXPLORER</title>
</head>
<style>
.black-button {
    background-color: black;
    color: white;
}
</style>
<body>
<%@ include file="adminnavbar.jsp" %>
<c:if test="${not empty message}">
    <div style="color: green; font-weight: bold">
        ${message}
    </div>
</c:if>
<div class="container">
    <center><h1>Alert Message</h1></center>
    <form action="sendWeatherAlerts" method="post">
        <label for="subject">Subject:</label><br>
        <input type="text" id="subject" name="subject"><br><br>
        <label for="message">Message:</label><br>
        <textarea id="message" name="message" rows="4" cols="50"></textarea><br><br>

</select>
    <input type="submit" value="Send Alerts" class="black-button">
    </form>
</div>
</body>
</html>
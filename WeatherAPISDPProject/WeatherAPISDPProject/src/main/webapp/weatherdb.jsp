<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WEATHER EXPLORER</title>
    
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    body {
    background-image: url('../images/dark-1836972_1280.jpg');
        background-size: cover;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
</style>

<body>
<%@ include file="usernavbar.jsp" %>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <h1 class="text-center">Weather Data</h1>
                <form action="/search" method="post">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" name="city" placeholder="Enter city">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
                
                <c:if test="${not empty weatherData}">
                    <div class="card">
                        <div class="card-body">
                            <h2 class="card-title">Weather for ${weatherData.city}</h2>
                            <p class="card-text">Date: ${weatherData.date}</p>
                            <p class="card-text">Temperature: <fmt:formatNumber value="${weatherData.temperature}" pattern="#.#" />°C</p>
                            <p class="card-text">Description: ${weatherData.description}</p>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <!-- Include Bootstrap JavaScript (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
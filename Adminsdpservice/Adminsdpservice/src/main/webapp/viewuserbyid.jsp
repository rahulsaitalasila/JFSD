<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%> 
<%@ taglib uri="jakarta.tags.core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c1" %>
 
<html> 
<head> 
 
<link type="text/css" rel="stylesheet" href="css/style.css"> 
 
<style> 
 
   .card {
        border: 2px solid black; 
        border-radius: 10px; 
        padding: 20px; 
        margin: 20px; 
        box-shadow: 0 4px 12px 0 rgba(0, 0, 0, 0.2); 
        max-width: 400px; 
        background-color: dimgray; 
        color: dimgray;
    }

    .card-title {
        font-weight: bold;
        font-size: 18px;
        margin-bottom: 10px; 
    }
</style> 
</head> 
<body> 


<%@ include file="adminnavbar.jsp" %>
    <div class="card">

ID : ${user.id}<br>
Name : ${user.name}<br>
Email : ${user.email}<br>
Location : ${user.location}<br>
Contact No : ${user.contact}<br>
Status : ${user.active}
</div>
</body>
</html>
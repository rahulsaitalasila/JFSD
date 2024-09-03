<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%> 
<%@ taglib uri="jakarta.tags.core" prefix="c"%> 
 
<html> 
<head> 
 
<link type="text/css" rel="stylesheet" href="css/style.css"> 
 
<style> 
 table {
      border-collapse: collapse;
      width: 80%;
      margin: 20px auto;
      background-color: white; /* Set the background color of the table to white */
    }

    table, th, td {
      border: 1px solid #696969;
      padding: 8px;
      text-align: left;
    }
    

    th {
      background-color: #696969;
      color: #696969;
    }

    
          .delete-button {
            padding: 6px 12px;
            background-color: #ff0000;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }


        .delete-button:hover {
            background-color: #cc3d27;
        }
        .view-button {
            background-color: black;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 4px;
        }
 
</style> 
</head> 
<body> 
 
 <%@ include file="adminnavbar.jsp" %>
 
<br> 
 
<h3 align="center"><u>View All Users</u></h3> 
 
<table align=center  border=2>  
<tr bgcolor="black" style="color:white"> 
<td>ID</td> 
<td>NAME</td> 
<td>LOCATION</td> 
<td>EMAIL ID</td> 
<td>CONTACT NO</td> 
<td>ACTION</td>
</tr> 
 
<c:forEach items="${userdata}"  var="user"> 
<tr> 
<td><c:out value="${user.id}" /></td> 
<td><c:out value="${user.name}" /></td> 
<td><c:out value="${user.location}" /></td> 
<td><c:out value="${user.email}" /></td> 
<td><c:out value="${user.contact}" /></td> 
<td>
<a class ="view-button" href='<c:url value="view?id=${user.id}"></c:url>'>View</a>
</td>
 
</tr> 
</c:forEach> 
 
</table> 
 
</body> 
</html>
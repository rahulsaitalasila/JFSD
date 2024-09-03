<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%>

<html>
<head>

<link type="text/css" rel="stylesheet" href="css/style.css">

<style>


</style>
</head>
<body>

 <%@ include file="navbar.jsp" %>

<br>
<span class="blink">
<h3 align=center style="color:red">${message}</h3>
</span>

<h3 align=center><u>Admin Login</u></h3>

<br>



<div class="form-container">
    <h2>Login</h2>
    <form method="post" action="checkadminlogin">
        <input type="text" name="uname" placeholder="Username" style="width: 100%; padding: 10px; margin-bottom: 10px;">
        <input type="password" name="pwd" placeholder="Password" style="width: 100%; padding: 10px; margin-bottom: 10px;">
        <button type="submit" value="Login" style="width: 100%; padding: 10px; background-color: black; color: #fff; border: none; border-radius: 5px;">Login</button>
    </form>
</div>

</body>
</html>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<link type="text/css" rel="stylesheet" href="css/style.css">

<head>
<meta charset="UTF-8">
<title>Message</title>
</head>
<body>
<%@ include file="navbar.jsp" %>



<br>

<h3 align=center><u>User Registration</u></h3>

<span class="blink">
<h3 align=center style="color:white">${message}</h3>
</span>

<br>

<div class="form-container">

<form method="post" action="insertuser">

<table align=center>

<tr>
<td><label>Name</label></td>
<td>
<input type="text" name="name" required="required"  />
</td>
</tr>

<tr><td></td></tr>
<tr>
<td><label>Email ID</label></td>
<td><input type="email" name="email" required/></td>
</tr>

<tr><td></td></tr>

<tr>
<td><label>Password</label></td>
<td><input type="password" name="pwd" required/></td>
</tr>

<tr><td></td></tr>

<tr>
<td><label>Location</label></td>
<td><input type="text" name="location" required/></td>
</tr>

<tr><td></td></tr>

<tr>
<td><label>Contact No</label></td>
<td><input type="text" name="contact" pattern="[789][0-9]{9}"  placeholder="Must be 10 digits" required/></td>
</tr>

<tr><td></td></tr>
<tr><td></td></tr>

<tr align=center>
<td colspan=2><input type="submit" value="Register" style="width: 100%; padding: 10px; background-color: black; color: #fff; border: none; border-radius: 5px;"></td>
</tr>
</table>

</form>

</div>
</body>
</html>
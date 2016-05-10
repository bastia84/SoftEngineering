<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentication Manager</title>
</head>
<body>
<form action="NewUserServlet">
<center>    
<p>DIRECTIONS: If you are changing the password to a current user please fill in all fields.</p>
<p>If you are creating a new user please leave "Old Password"" Field Empty</p>
<div id="newPassWordDiv">
	<div><label for="Username">Username</label><input type="text" name="Username"></div>
    <div><label for="oldPWord">Old Password</label><input type="password" name="oldPWord"></div>
    <div><label for="newPWord">New Password</label><input type="password" name="newPWord" ></div>
    <div><label for="newPWord2">Repeat New Password</label><input type="password" name="newPWord2" ></div>
	<div><input type="submit" value="Save"></div>
</div>
${messages}
</center>
</form>

</body>


</html>
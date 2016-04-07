<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page import="test.hi" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
<link rel="stylesheet" type="text/css" href="ASRC.css">
<title>Software Engineering Project</title>

</head>
<body id = "body1" style="background: url(http://www.asrcfederal.com/dnc/PublishingImages/disa.jpg) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover; ">	


<div id= "header"> 
<h1><b>ASRC Talent Management</b></h1>
</div>
<table id= "table1" style="width:100%">
<tr><td><button  id ="b1" class="button" onclick="window.location.href = 'file:///C:/Users/Brian/workspace1/ManagedSkills/Skills.html'">Skills</button></td> 
<td style="float:center; padding-left:265px"><button id ="b2" class="button" onclick="window.location.href = 'file:///C:/Users/Brian/workspace1/ManagedSkills/Productivity.html'">Productivity</button></td> 
<td style="float:right"><button id="b3" class="button" onclick="window.location.href = 'file:///C:/Users/Brian/workspace1/ManagedSkills/Forecasting.html'">Forecasting </button></tr>
</table>

<form method="post"  action="AuthenticationServlet">
        <div id="form1" class="form1" style="margin-left: 45%; margin-top: 10%" >
  		Username:<br>
  		<input type="text" name="Username"><br>
  		Password:<br>
  		<input type="password" name="Password">
  		
		</div>
 <input type="submit" value="Login" id=login class="loginbutton"  style="margin-left: 46.5%; margin-top: 1.5%">
${messages}
 </form>
 <button id=MA disabled style="position: absolute;right:2px;bottom:2px;">Manage Authentication</button>
</body>

</html>
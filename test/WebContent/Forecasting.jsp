<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="ASRC.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<title>Software Engineering Project</title>

</head>
<body id = "body1" style="background: url(http://www.asrcfederal.com/dnc/PublishingImages/disa.jpg) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover; ">
  <table border=”1”>
     <tr>
      <td>Cookie Name </td>
      <td>Cookie Value </td>
      <td>Cookie Max Age  </td>
     </tr>	
<%
Cookie[] cookies = request.getCookies();

if(cookies != null){	
	int j = 0;

  for(int i=0 ;i<cookies.length;i++ ){
    Cookie cookie = cookies[i];
    out.println(" <tr> ");
    out.println("<td>" + cookie.getName() + "</td>" );
    out.println("<td>" + cookie.getValue() + "</td>" );
    out.println("<td>" + cookie.getMaxAge() + "</td>" );
    out.println(" </tr> ");
    if (cookie.getName().equals("userID")){
    	System.out.println(cookie.getName());
    	j++;}
    
  }
	 if (j<1){response.sendRedirect("http://localhost:8080/test/MyJsp.jsp");}
  }
else {
	response.sendRedirect("http://localhost:8080/test/MyJsp.jsp");
}

%>
</table>

<div id= "header"> 
<h1><b>ASRC Talent Management: Forecasting</b></h1>
</div>

<table id= "table1" style="width:100%">
<tr><td><button  id ="b1" class="button" onclick="window.location.href = 'file:///C:/Users/Brian/workspace1/ManagedSkills/Skills.html'">Skills</button></td> 
<td style="float:center; padding-left:265px"><button id ="b2" class="button" onclick="window.location.href = 'file:///C:/Users/Brian/workspace1/ManagedSkills/Productivity.html'">Productivity</button></td> 
<td style="float:right"><button id="b3" class="button" onclick="window.location.href = 'file:///C:/Users/Brian/workspace1/ManagedSkills/Forecasting.html'">Forecasting </button></tr>
</table>

<div id="form2" class="form2" style="margin-left: 2%; margin-top: 0%" >
 
Start Date:
<input type="date" name="date" id="date" value="" />
End Date:
<input type="date" name="date" id="date1" value="" />
 
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
</div>
<script>
   (function() {
      var elem = document.createElement('input');
      elem.setAttribute('type', 'date');
 
      if ( elem.type === 'text' ) {
         $('#date').datepicker() &&  $('#date1').datepicker(); 
      }
   })();
 
</script>

</body>

</html>

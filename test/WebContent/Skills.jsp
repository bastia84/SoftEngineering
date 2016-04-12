<!DOCTYPE html>
<html>
<head >
<link rel="stylesheet" type="text/css" href="ASRC.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<meta charset="ISO-8859-1">
<title>Software Engineering Project</title>
</head>
<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;border-color:#aabcfe;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;}
.tg .tg-fefd{color:#000000;vertical-align:top}
.tg .tg-6k2t{background-color:#D2E4FC;vertical-align:top}
.tg .tg-yw4l{vertical-align:top}
</style>
<body id = "body1" style="background: url(http://www.asrcfederal.com/dnc/PublishingImages/disa.jpg) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover; ">



<div id= "header"> 
<h1><b>ASRC Talent Management: Skills</b></h1>
</div>

<table id= "table1" style="width:100%">
<tr><td><button  id ="b1" class="button" onclick="window.location.href = 'http://localhost:8080/test/Skills.jsp'">Skills</button></td> 
<td style="float:center; padding-left:265px"><button id ="b2" class="button" onclick="window.location.href = 'http://localhost:8080/test/Productivity.jsp'">Productivity</button></td> 
<td style="float:right"><button id="b3" class="button" onclick="window.location.href = 'http://localhost:8080/test/Forecasting.jsp'">Forecasting </button></tr>
</table>
<table class="tg"  id="dropdown" style= "margin-left:2%; margin-top: 5%">
  <tr>
    <th >Department: <select>
    <option value="All">All</option>
 
  <%String[] Dept1 = null;
  Dept1 = (String[]) request.getAttribute("Departments");
  System.out.println(Dept1.length);
  for(int i = 0; i < Dept1.length; i++){
  %>

 <option><%= Dept1[i] %></option>
<%} %>
</select></th>
  </tr>

  <tr>
    <td style="padding-left:2px">Employee: <select>
  <option value="volvo">All</option>
  <option value="saab">Emp 1</option>
  <option value="opel">Emp 2</option>
  <option value="audi">Emp 3</option>
</select></td>
  </tr>
</table>
<div id="table1" style= "margin-left: 40%; margin-top: -2%">

<table class="tg">
  <tr>
    <th class="tg-fefd">Employee</th>
    <th class="tg-fefd">Department</th>
    <th class="tg-fefd">Skill 1</th>
    <th class="tg-fefd">Skill 2</th>
    <th class="tg-fefd">Skill 3</th>
  </tr>
  <tr>
    <td class="tg-6k2t"></td>
    <td class="tg-6k2t"></td>
    <td class="tg-6k2t"><input type="checkbox" name="query_myTextEditBox"></td>
    <td class="tg-6k2t"><input type="checkbox" name="query_myTextEditBox"></td>
    <td class="tg-6k2t"><input type="checkbox" name="query_myTextEditBox"></td>
  </tr>
  <tr>
    <td class="tg-yw4l"></td>
    <td class="tg-yw4l"></td>
    <td class="tg-yw4l"><input type="checkbox" name="query_myTextEditBox"></td>
    <td class="tg-yw4l"><input type="checkbox" name="query_myTextEditBox"></td>
    <td class="tg-yw4l"><input type="checkbox" name="query_myTextEditBox"></td>
  </tr>
  <tr>
    <td class="tg-6k2t"></td>
    <td class="tg-6k2t"></td>
    <td class="tg-6k2t"><input type="checkbox" name="query_myTextEditBox"></td>
    <td class="tg-6k2t"><input type="checkbox" name="query_myTextEditBox"></td>
    <td class="tg-6k2t"><input type="checkbox" name="query_myTextEditBox"></td>
  </tr>
  <tr>
    <td class="tg-yw4l"></td>
    <td class="tg-yw4l"></td>
    <td class="tg-yw4l"><input type="checkbox" name="query_myTextEditBox"></td>
    <td class="tg-yw4l"><input type="checkbox" name="query_myTextEditBox"></td>
    <td class="tg-yw4l"><input type="checkbox" name="query_myTextEditBox"></td>
  </tr>
</table></div>
  ${Departments}
</body>

</html>

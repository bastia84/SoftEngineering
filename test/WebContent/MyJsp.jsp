<!-- START OF FILE -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="ASRC.css">
<title>Software Engineering Project</title>

</head>
<body id="body1"
	style="background: url(http://www.asrcfederal.com/dnc/PublishingImages/disa.jpg) no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover;">

	<div id="header">
		<h1>
			<b>ASRC Talent Management</b>
		</h1>
	</div>

	<table id="table1" style="width: 100%">
		<tr>
			<td>
				<button id="b1" class="button"
					onclick="window.location.href = 'http://localhost:8080/test/LoadDept'">Skills</button>
			</td>
			<!-- Button 1 will redirect to Skills Page -->

			<td style="float: center; padding-left: 265px"><button id="b2"
					class="button"
					onclick="window.location.href = 'http://localhost:8080/test/LoadProdDept'">Productivity</button>
			</td>
			<!-- Button 2 will redirect to Productivity Page -->

			<td style="float: right">
				<button id="b3" class="button"
					onclick="window.location.href = 'http://localhost:8080/test/Forecasting.jsp'">Forecasting
				</button> <!-- Button 3 will redirect to Forecasting Page -->
			</td>
		</tr>
	</table>

	<!-- Input fields for username and password are in this form -->
	<form method="post" action="AuthenticationServlet">
		<div id="form1" class="form1"
			style="margin-left: 45%; margin-top: 10%">
			Username:<br> <input type="text" name="Username"><br>
			Password:<br> <input type="password" name="Password">
		</div>

		<!-- This is the login button. Once pressed the Servlet "AuthenticationServlet" is called
			 to verify the data entered -->
		<input type="submit" value="Login" id=login class="loginbutton"
			style="margin-left: 46.5%; margin-top: 1.5%"> ${messages}
		<!-- This messages variable above will display an error message if failed login -->
	</form>

	<!-- Button to manage authentications (INACTIVE) -->
	<button id=MA disabled
		style="position: absolute; right: 2px; bottom: 2px;">Manage
		Authentication</button>
</body>
</html>
<!-- END OF FILE -->
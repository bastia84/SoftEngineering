<!-- START OF FILE -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >

<!-- Import CSS Files for style of various elements in file -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="TableStyle.css">
<link rel="stylesheet" type="text/css" href="ASRC.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<title>Software Engineering Project</title>
</head>

<body id = "body1" style="background: url(http://www.asrcfederal.com/dnc/PublishingImages/disa.jpg) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover; ">

	<div id="header">
		<h1>
			<b>ASRC Talent Management: Forecasting</b>
		</h1>
	</div>

	<table id="table1" style="width: 100%">
		<tr>
			<td>
				<button id="b1" class="button"
					onclick="window.location.href = 'http://localhost:8080/test/Skills.jsp'">Skills</button>
			</td>
			<!-- Button 1 will redirect to Skills Page -->
			
			<td style="float: center; padding-left: 265px">
			<button id="b2"	class="button"
					onclick="window.location.href = 'http://localhost:8080/test/Productivity.jsp'">Productivity</button>
			</td>
			<!-- Button 2 will redirect to Productivity Page -->
			<td style="float: right">
				<button id="b3" class="button"
					onclick="window.location.href = 'http://localhost:8080/test/Forecasting.jsp'">Forecasting
				</button>
			</td>
			<!-- Button 3 will redirect to Forecasting Page -->
		</tr>
	</table>

	<div id="form2" class="form2" style="margin-left: 2%; margin-top: 0%">


		<form name="d" action="LoadForeDept">
			<table class="tg" id="dropdown"
				style="float: left; width: 250px; height: 20px; margin-left: 2%; margin-top: .5%">
				<tr>
					<th>Project: <select name="Project" id="Project"
						onchange="javascript:document.d.submit()">
							<!-- When a selection is made the Servlet "LoadForeDept" 
								 is called to populate the Employee drop-down -->
							<!-- This Java code loops through the array of Projects and populates the drop-down -->
							<%
								String[] Prj1 = (String[]) request.getAttribute("Projects");
								if (Prj1 != null) {
									System.out.println(Prj1.length);
									for (int k = 0; k < Prj1.length; k++) {
							%>

							<option><%=Prj1[k]%></option>
							<%
								}
								}
							%>
					</select></th>
				</tr>
			</table>
		</form>

	<!-- This form is for Departments -->
		<form name="c" action="LoadForeCharge">
				<input type="hidden" name="selectedProj" id="selectedProj" value="${selectedProj}">
				<table class="tg" id="dropdown"
				style="float: left; width: 250px; height: 20px; margin-left: 2%; margin-top: .5%">
				<tr>
					
					<th>Department: <select name="Department" id="Department" onchange="javascript:document.c.submit()">
					<option></option>
							<!-- When a selection is made the Servlet "LoadForCharge" 
								 is called to populate the Charges drop-down below-->
							<!-- This Java code loops through the array of departments and populates the drop-down -->
							<%
							
								String[] dept1 = (String[]) request.getAttribute("Departments");
								if (dept1 != null) {
									System.out.println(dept1.length);
									for (int k = 0; k < dept1.length; k++) {
							%>

							<option><%=dept1[k]%></option>
							<%
								}
								}
							
						%>
					</select></th>
				</tr>
			</table>
		</form>
				
		<form name="s" action="SubmitForeSearch">
		
		<input type="datetime-local" name="date1" id="date1"
					value="Start Date:" /> <input type="datetime-local" name="date2"
					id="date2" value="End Date:" />

				<script src="http://code.jquery.com/jquery-1.11.2.js"></script>
				<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
			
			
			

			<!-- The following script modifies the date entry from the default.
		 Default allows for selection of all dates. In our case we only need month and year -->
			<script>
		(function() {
			var elem = document.createElement('input');
			elem.setAttribute('type', 'date');

			if (elem.type === 'text') {
				$('#date1')
						.datepicker(
								{
									changeMonth : true,
									changeYear : true,
									showButtonPanel : true,
									onClose : function() {
										var iMonth = $(
												"#ui-datepicker-div .ui-datepicker-month :selected")
												.val();
										var iYear = $(
												"#ui-datepicker-div .ui-datepicker-year :selected")
												.val();
										$(this).datepicker('setDate',
												new Date(iYear, iMonth, 1));
									}
								})
						&& $('#date2')
								.datepicker(
										{
											changeMonth : true,
											changeYear : true,
											showButtonPanel : true,
											
											onClose : function() {
												var iMonth = $(
														"#ui-datepicker-div .ui-datepicker-month :selected")
														.val();
												var iYear = $(
														"#ui-datepicker-div .ui-datepicker-year :selected")
														.val();
												$(this).datepicker(
														'setDate',
														new Date(iYear, iMonth,
																1));
											}
										});
			}
		})();
	</script>
		
				<input type="hidden" name="selectedProj" id="selectedProj" value="${selectedProj}">
				<input type="hidden" name="selectedDept" id="selectedDept" value="${selectedDept}">
				<table class="tg" id="dropdown"
				style="float: left; width: 250px; height: 20px; margin-left: 2%; margin-top: .5%">
				<tr>
					<th>Charge #: <select name="JFunction" id="JFunction" onchange="javascript:document.s.submit()">
					<option></option>
							<!-- When a selection is made the Servlet "SubmitForeSearch" 
								 is called to populate table below with the results from the query -->
							<!-- This Java code loops through the array of departments and populates the drop-down -->
							<%
							
								String[] Charge1 = (String[]) request.getAttribute("JFunctions");
								if (Charge1 != null) {
									System.out.println(Charge1.length);
									for (int k = 0; k < Charge1.length; k++) {
							%>

							<option><%=Charge1[k]%></option>
							<%
								}
								}
							
						%>
					</select></th>
				</tr>
			</table>

</form>
</div>

				
	
	<div style="width: 400px">
			<button type="button" value="New Search" id=NewSearch class="Loginbutton" onclick="redirect()">New Search</button>
	</div>
	
	<script>
	function redirect(){
		document.location.href="/LoadForecasting";
		System.out.println("Work");
	}
	</script>
	
	<!-- This is the table where the results from query will be displayed -->
	<div id="table1" style="margin: 100px 30%; width: 60%; float: right">
		<table class="tg">
			<tr>
				<th class="tg-fefd">Project</th>
				<th class="tg-fefd">Department</th>
				<th class="tg-fefd">Charge #</th>
				<th class="tg-fefd">Hours Scheduled</th>
				<th class="tg-fefd">Month</th>
			</tr>


			<%
							
							String[] Depts = (String[]) request.getAttribute("resultDepartments");
							String[] Projs = (String[]) request.getAttribute("resultProjects");
							String[] Chargs = (String[]) request.getAttribute("resultCharges");
							Float[] hrs = (Float[]) request.getAttribute("resulthrs");
							String[] time = (String []) request.getAttribute("resulttimes");
							if (Depts != null) {

								for (int z = 0; z < Depts.length; z++) {
									
									if(z%2 == 0){
						%>
			<tr>
				<td class="tg-6k2t"><%=Projs[z]%></td>
				<td class="tg-6k2t"><%=Depts[z]%></td>
				<td class="tg-6k2t"><%=Chargs[z]%></td>
				<td class="tg-6k2t"><%=hrs[z]%></td>
				<td class="tg-6k2t"><%=time[z]%></td>
			</tr>
			<%
							}
									else {
										%>

			<tr>
				<td class="tg-yw4l"><%=Projs[z]%></td>
				<td class="tg-yw4l"><%=Depts[z]%></td>
				<td class="tg-yw4l"><%=Chargs[z]%></td>
				<td class="tg-yw4l"><%=hrs[z]%></td>
				<td class="tg-6k2t"><%=time[z]%></td>
				
			</tr>

			<%	
								}
							}
							}
						%>

		</table>
	</div>
	${messages}

<form action="LogOutServlet">
<button id=LogOut disabled style="position: absolute;right:2px;bottom:2px;">Log Out</button>
</form>
</body>

</html>
<!-- END OF FILE -->

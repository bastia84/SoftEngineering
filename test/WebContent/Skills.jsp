<!-- START OF FILE -->
<!DOCTYPE html>
<html>
<head>

<!-- Import CSS Files for style of various elements in file -->
<link rel="stylesheet" type="text/css" href="ASRC.css">
<link rel="stylesheet" type="text/css" href="TableStyle.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<meta charset="ISO-8859-1">
<%@ page import="test.Employee"%>
<title>Software Engineering Project</title>
</head>

<body id="body1"
	style="background: url(http://www.asrcfederal.com/dnc/PublishingImages/disa.jpg) no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover;">



	<div id="header">
		<h1>
			<b>ASRC Talent Management: Skills</b>
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

	<!-- This input field is a drop-down list which is populated from the Servlet "LoadSkilDept" on load -->
	<div>
		<form name="d" action="LoadEmployee">
			<table class="tg" id="dropdown"
				style="margin-left: 2%; margin-top: 5%">

				<tr>
					<th>Department: <select name="Department"
						onchange="javascript:document.d.submit()">
							<!-- When a selection is made the Servlet "LoadProdEmployee" 
						 is called to populate the Employee drop-down -->
							

							<!-- This Java code loops through the array of departments and populates the drop-down -->
							<%
								int i;
								String[] Dept1 = (String[]) request.getAttribute("Departments");
								if (Dept1 != null) {
									System.out.println(Dept1.length);
									for (i = 0; i < Dept1.length; i++) {
							%>

							<option><%=Dept1[i]%></option>
							<%
							}
								if(i > 1){
									%>
							<option value="All">All</option>
							<%	
								}
								}
							
						%>
					</select></th>
				</tr>
			</table>
			
		</form>

		<!-- This input field is a drop-down list which is populated from the Servlet "LoadEmployee" when
		 the Department from the above input field is selected -->
		<form name="e" action="SubmitSkillSearch">
		<table class="tg" id="dropdown"
			style="margin-left: 2%; margin-top: 5%">
			<tr>
				<td style="padding-left: 2px">Employee: <select name="Employee" onchange="javascript:document.e.submit()">
					
						<!-- This Java code loops through the array of employees and populates the drop-down -->
						<%	
							int k;
							String[] Emp1 = (String[]) request.getAttribute("Employees");
							if (Emp1 != null) {
								System.out.println(Emp1.length);
								for (k = 0; k < Emp1.length; k++) {
						%>

						<option><%=Emp1[k]%></option>
						<%
							}
								if(k > 1){
									%>
							<option value="All">All</option>
							<%	
								}
								}
							
						%>

				</select></td>
			</tr>
		</table>
		<%=request.getAttribute("selectedD")%>
		<input type="hidden" name="selectedD" id="selectedD" value="${selectedD}">
		</form>
	</div>
	
	<!-- This is the table where the results from query will be displayed
		 We will have Java code to populate the table through a loop -->
	<div id="table1" style="margin-left: 40%; margin-top: -2%">
		<table class="tg">
			<tr>
				<th class="tg-fefd">Employee</th>
				<th class="tg-fefd">Department</th>
				<th class="tg-fefd">Skill 1</th>
				<th class="tg-fefd">Skill 2</th>
				<th class="tg-fefd">Skill 3</th>
			</tr>
			<%
							int z;
							Employee[] Worker = (Employee[]) request.getAttribute("TableEmployee");
							if (Worker != null) {
								System.out.println(Worker.length);
								for (z = 0; z < Worker.length; z++) {
									String [] skills = Worker[z].getSkills();
									String skill1, skill2, skill3;
									if (skills.length < 1){
										continue;
									}
									
									else if (skills.length == 1){
										skill1 = skills[0];
										skill2 = "";
										skill3 = "";
									}
									else if (skills.length == 2){
										skill1 = skills[0];
										skill2 = skills[1];
										skill3 = "";
									}
									
									else {
										skill1 = skills[0];
										skill2 = skills[1];
										skill3 = skills[2];
									}
									if(z%2 == 0){
						%>
			<tr>
				<td class="tg-6k2t"><%=Worker[z].getName()%></td>
				<td class="tg-6k2t"><%=Worker[z].getDepartment()%></td>
				<td class="tg-6k2t"><%=skill1%></td>
				<td class="tg-6k2t"><%=skill2%></td>
				<td class="tg-6k2t"><%=skill3%></td>
			</tr>
			<%
							}
									else {
										%>

			<tr>
				<td class="tg-yw4l"><%=Worker[z].getName()%></td>
				<td class="tg-yw4l"><%=Worker[z].getDepartment()%></td>
				<td class="tg-yw4l"><%=skill1%></td>
				<td class="tg-yw4l"><%=skill2%></td>
				<td class="tg-yw4l"><%=skill3%></td>
			</tr>

			<%	
								}
							}
							}
						%>

		</table>
		
	</div>
</body>
</html>
<!-- END OF FILE -->

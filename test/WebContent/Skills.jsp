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
					onclick="window.location.href = 'http://localhost:8080/test/LoadSkillDept'">Skills</button>
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
		<input type="hidden" name="selectedD" id="selectedD" value="${selectedD}">
		</form>
	</div>
	
	<div style="float: right; width: 130px; align: center">
			<button type="button" value="New Search" id=NewSearch class="Loginbutton" onclick="redirect()">New Search</button>
	</div>
	
	<script>
	function redirect(){
		document.location.href="/test/LoadSkillDept";
		System.out.println("Work");
	}
	</script>
	
	
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
	${messages}
	<script type="text/javascript" charset="utf-8">var TgTableSort=window.TgTableSort||function(n,t){"use strict";function r(n,t){for(var e=[],o=n.childNodes,i=0;i<o.length;++i){var u=o[i];if("."==t.substring(0,1)){var a=t.substring(1);f(u,a)&&e.push(u)}else u.nodeName.toLowerCase()==t&&e.push(u);var c=r(u,t);e=e.concat(c)}return e}function e(n,t){var e=[],o=r(n,"tr");return o.forEach(function(n){var o=r(n,"td");t>=0&&t<o.length&&e.push(o[t])}),e}function o(n){return n.textContent||n.innerText||""}function i(n){return n.innerHTML||""}function u(n,t){var r=e(n,t);return r.map(o)}function a(n,t){var r=e(n,t);return r.map(i)}function c(n){var t=n.className||"";return t.match(/\S+/g)||[]}function f(n,t){return-1!=c(n).indexOf(t)}function s(n,t){f(n,t)||(n.className+=" "+t)}function d(n,t){if(f(n,t)){var r=c(n),e=r.indexOf(t);r.splice(e,1),n.className=r.join(" ")}}function v(n){d(n,L),d(n,E)}function l(n,t,e){r(n,"."+E).map(v),r(n,"."+L).map(v),e==T?s(t,E):s(t,L)}function g(n){return function(t,r){var e=n*t.str.localeCompare(r.str);return 0==e&&(e=t.index-r.index),e}}function h(n){return function(t,r){var e=+t.str,o=+r.str;return e==o?t.index-r.index:n*(e-o)}}function m(n,t,r){var e=u(n,t),o=e.map(function(n,t){return{str:n,index:t}}),i=e&&-1==e.map(isNaN).indexOf(!0),a=i?h(r):g(r);return o.sort(a),o.map(function(n){return n.index})}function p(n,t,r,o){for(var i=f(o,E)?N:T,u=m(n,r,i),c=0;t>c;++c){var s=e(n,c),d=a(n,c);s.forEach(function(n,t){n.innerHTML=d[u[t]]})}l(n,o,i)}function x(n,t){var r=t.length;t.forEach(function(t,e){t.addEventListener("click",function(){p(n,r,e,t)}),s(t,"tg-sort-header")})}var T=1,N=-1,E="tg-sort-asc",L="tg-sort-desc";return function(t){var e=n.getElementById(t),o=r(e,"tr"),i=o.length>0?r(o[0],"td"):[];0==i.length&&(i=r(o[0],"th"));for(var u=1;u<o.length;++u){var a=r(o[u],"td");if(a.length!=i.length)return}x(e,i)}}(document);document.addEventListener("DOMContentLoaded",function(n){TgTableSort("tg-PJUO3")});</script>
	<button id=LogOut style="position: absolute;right:2px;bottom:2px;" onclick="LogOutRedirect()">Log Out</button>
<script>
	function LogOutRedirect(){
		document.location.href="MyJsp.jsp";
	}
	</script>
</body>
</html>
<!-- END OF FILE -->

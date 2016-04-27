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
								int k;
								String[] Prj1 = (String[]) request.getAttribute("Projects");
								if (Prj1 != null) {
									if (Prj1.length > 1){
										%>
										<option></option>
										<option value="All">All</option>
									<%
									}
									System.out.println(Prj1.length);
									for (k = 0; k < Prj1.length; k++) {
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
					
							<!-- When a selection is made the Servlet "LoadForCharge" 
								 is called to populate the Charges drop-down below-->
							<!-- This Java code loops through the array of departments and populates the drop-down -->
							<%
								int i;
								String[] dept1 = (String[]) request.getAttribute("Departments");
								if (dept1 != null) {
									if (dept1.length > 1){
										%>
										<option></option>
										<option value="All">All</option>
									<%
									}
									System.out.println(dept1.length);
									for (i = 0; i < dept1.length; i++) {
							%>

							<option><%=dept1[i]%></option>
							<%
								}
								}
							
						%>
					</select></th>
				</tr>
			</table>
		</form>
				
		<form name="s" action="SubmitForeSearch">
			<input type="hidden" name="selectedProj" id="selectedProj" value="${selectedProj}">
				<input type="hidden" name="selectedDept" id="selectedDept" value="${selectedDept}">
				<table class="tg" id="dropdown"
				style="float: left; width: 250px; height: 20px; margin-left: 2%; margin-top: .5%">
				<tr>
					<th>Charge #: <select name="JFunction" id="JFunction" >
					
							<!-- When a selection is made the Servlet "SubmitForeSearch" 
								 is called to populate table below with the results from the query -->
							<!-- This Java code loops through the array of departments and populates the drop-down -->
							<%
								int z;
								String[] Charge1 = (String[]) request.getAttribute("JFunctions");
								if (Charge1 != null) {
									System.out.println(Charge1.length);
									for (z = 0; z < Charge1.length; z++) {
							%>

							<option><%=Charge1[z]%></option>
							<%
								}
								}
							
						%>
					</select></th>
				</tr>
			</table>
		<div id="form2" class="form2" style="margin-left: 2%; margin-top: 0%">
		<input type="datetime-local" name="date1" id="date1"
					value="Start Date:" /> <input type="datetime-local" name="date2"
					id="date2" value="End Date:" />

				<script src="http://code.jquery.com/jquery-1.11.2.js"></script>
				<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
			</div>
			
			

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
		
			



				
	
	<div style="width: 400px">
		<div style="float: right; width: 130px; align: center">
			<button type="button" value="New Search" id=NewSearch class="Loginbutton" onclick="redirect()">New Search</button>
		</div>
		<div style="float: right; width: 225px; align: center">
			<input type="submit" value="Submit Search" id=Search>
		</div>
	</div>
	
	</form>
		
	
	<script>
	function redirect(){
		document.location.href="/test/LoadForecasting";
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

								for (int m = 0; m < Depts.length; m++) {
									
									if(m%2 == 0){
						%>
			<tr>
				<td class="tg-6k2t"><%=Projs[m]%></td>
				<td class="tg-6k2t"><%=Depts[m]%></td>
				<td class="tg-6k2t"><%=Chargs[m]%></td>
				<td class="tg-6k2t"><%=hrs[m]%></td>
				<td class="tg-6k2t"><%=time[m]%></td>
			</tr>
			<%
							}
									else {
										%>

			<tr>
				<td class="tg-yw4l"><%=Projs[m]%></td>
				<td class="tg-yw4l"><%=Depts[m]%></td>
				<td class="tg-yw4l"><%=Chargs[m]%></td>
				<td class="tg-yw4l"><%=hrs[m]%></td>
				<td class="tg-6k2t"><%=time[m]%></td>
				
			</tr>

			<%	
								}
							}
							}
						%>

		</table>
	</div>
	
	<script type="text/javascript" charset="utf-8">var TgTableSort=window.TgTableSort||function(n,t){"use strict";function r(n,t){for(var e=[],o=n.childNodes,i=0;i<o.length;++i){var u=o[i];if("."==t.substring(0,1)){var a=t.substring(1);f(u,a)&&e.push(u)}else u.nodeName.toLowerCase()==t&&e.push(u);var c=r(u,t);e=e.concat(c)}return e}function e(n,t){var e=[],o=r(n,"tr");return o.forEach(function(n){var o=r(n,"td");t>=0&&t<o.length&&e.push(o[t])}),e}function o(n){return n.textContent||n.innerText||""}function i(n){return n.innerHTML||""}function u(n,t){var r=e(n,t);return r.map(o)}function a(n,t){var r=e(n,t);return r.map(i)}function c(n){var t=n.className||"";return t.match(/\S+/g)||[]}function f(n,t){return-1!=c(n).indexOf(t)}function s(n,t){f(n,t)||(n.className+=" "+t)}function d(n,t){if(f(n,t)){var r=c(n),e=r.indexOf(t);r.splice(e,1),n.className=r.join(" ")}}function v(n){d(n,L),d(n,E)}function l(n,t,e){r(n,"."+E).map(v),r(n,"."+L).map(v),e==T?s(t,E):s(t,L)}function g(n){return function(t,r){var e=n*t.str.localeCompare(r.str);return 0==e&&(e=t.index-r.index),e}}function h(n){return function(t,r){var e=+t.str,o=+r.str;return e==o?t.index-r.index:n*(e-o)}}function m(n,t,r){var e=u(n,t),o=e.map(function(n,t){return{str:n,index:t}}),i=e&&-1==e.map(isNaN).indexOf(!0),a=i?h(r):g(r);return o.sort(a),o.map(function(n){return n.index})}function p(n,t,r,o){for(var i=f(o,E)?N:T,u=m(n,r,i),c=0;t>c;++c){var s=e(n,c),d=a(n,c);s.forEach(function(n,t){n.innerHTML=d[u[t]]})}l(n,o,i)}function x(n,t){var r=t.length;t.forEach(function(t,e){t.addEventListener("click",function(){p(n,r,e,t)}),s(t,"tg-sort-header")})}var T=1,N=-1,E="tg-sort-asc",L="tg-sort-desc";return function(t){var e=n.getElementById(t),o=r(e,"tr"),i=o.length>0?r(o[0],"td"):[];0==i.length&&(i=r(o[0],"th"));for(var u=1;u<o.length;++u){var a=r(o[u],"td");if(a.length!=i.length)return}x(e,i)}}(document);document.addEventListener("DOMContentLoaded",function(n){TgTableSort("tg-PJUO3")});</script>
	${messages}


<button id=LogOut style="position: absolute;right:2px;bottom:2px;" onclick="LogOutRedirect()">Log Out</button>
<script>
	function LogOutRedirect(){
		document.location.href="MyJsp.jsp";
	}
	</script>
</body>

</html>
<!-- END OF FILE -->

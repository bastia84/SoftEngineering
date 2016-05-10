import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.Employee;
import test.dbConn;
import test.LineChart;

/**
 * Servlet implementation class LoadEmployee
 */
@WebServlet("/SubmitProdSearch")
public class SubmitProdSearch extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT, RESULT1, AVERAGEdept, AVERAGEemp;

	public String emp, charge, startDate, endDate, dept;
	ArrayList<Employee> emps = new ArrayList<Employee>();
	ArrayList<Float> avgDept = new ArrayList<Float>();
	HashMap<String, Float> avgEmp = new HashMap<String, Float>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitProdSearch() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 *      Additions to method stub include: Gathering of all employees available from SQL Database based on department selected
	 * 
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String errMessage;
		float hoursWorked = 0;
		String dept = "";

		emps.clear(); // clear array

		emp = request.getParameter("selectedEmp"); // get selected employee
		charge = request.getParameter("Charge"); // get selected charge
		startDate = request.getParameter("date1"); // get selected Start date
		endDate = request.getParameter("date2"); // get selected end date
		dept = request.getParameter("selectedDept"); // get selected department
		String emp1, dept1, charge1;

		try {

			if (emp.equals("") || charge.equals("") || dept.equals("") || startDate.equals("Start Date:") || endDate.equals("End Date:")) {
				errMessage = "<script>alert('Make sure all parameters for search are initilized')</script>";
				request.setAttribute("errMessage", errMessage);

			}

			else {


				// set strings for query based on selection in dropdowns
				if (emp.equals("All")) {
					emp1 = " LIKE '%' ";
				}

				else {
					emp1 = "='" + emp + "'";
				}

				if (dept.equals("All")) {
					dept1 = " LIKE '%' ";
				}

				else {
					dept1 = "='" + dept + "'";
				}

				if (charge.equals("All")) {
					charge1 = " LIKE '%' ";
				}

				else {
					charge1 = "='" + charge + "'";
				}

				// create new connection to DB, Run SQL Query and store ResultSet
				RESULT = new dbConn().start("SELECT SUM(sngHours) AS HoursWorked, COUNT(Worker) As cnt, SummaryOrg, Worker FROM dbo.Productivity "
						+ "WHERE Worker" + emp1 
						+ " AND SummaryOrg" + dept1 
						+ " AND JobFunction" + charge1 
						+ " AND Month>'" + startDate 
						+ "' AND Month<'" + endDate
						+ "' GROUP BY Worker, SummaryOrg");

				RESULT1 = new dbConn().start("SELECT sngHours, SummaryOrg, Worker, Month FROM dbo.Productivity "
						+ "WHERE Worker" + emp1
						+ " AND SummaryOrg" + dept1 
						+ " AND JobFunction" + charge1 
						+ " AND Month>'" + startDate 
						+ "' AND Month<'" + endDate
						+ "' GROUP BY sngHours, Month, Worker, SummaryOrg "
						+ "ORDER BY Month ASC");

				AVERAGEdept = new dbConn()
						.start("SELECT AVG(sngHours) FROM (SELECT sngHours, SummaryOrg, Worker FROM dbo.Productivity "
								+ "WHERE SummaryOrg" + dept1
								+ "AND JobFunction IN ("
									+ "SELECT DISTINCT JobFunction FROM dbo.Productivity "
									+ "WHERE SummaryOrg" + dept1 
									+ " AND Worker"+ emp1 
									+ " AND JobFunction" + charge1 + ") "
								+ "AND Month>'" + startDate 
								+ "' AND Month<'" + endDate
								+ "' GROUP BY SummaryOrg, Worker, sngHours) inner_query");

				AVERAGEemp = new dbConn()
						.start("SELECT AVG(sngHours), Worker FROM "
									+ "(SELECT sngHours, SummaryOrg, Worker, Month FROM dbo.Productivity "
									+ "WHERE Worker" + emp1 
									+ " AND SummaryOrg" + dept1 
									+ " AND JobFunction" + charge1 
									+ " AND Month>'" + startDate 
									+ "' AND Month<'" + endDate 
									+ "' GROUP BY sngHours, Month, Worker, SummaryOrg) inner_query "
								+ "GROUP BY Worker");

				@SuppressWarnings("unused")
				LineChart chart = new LineChart(RESULT1);

				RESULT.next(); // go to first result
				AVERAGEemp.next();

				while (!RESULT.isAfterLast()) { // while there are still elements in result set

					hoursWorked = RESULT.getFloat("HoursWorked"); // get hours worked from resultset
					dept1 = RESULT.getString("SummaryOrg"); // get department from resultset
					emp1 = RESULT.getString("Worker"); // get employee from resultset
					int cnt = RESULT.getInt("cnt"); // get cnt closed jobs
					Employee worker = new Employee(emp1, dept1, hoursWorked, cnt); // create new employee with elements from resultset
					emps.add(worker); // add worker to array

					avgEmp.put(AVERAGEemp.getString("Worker"), AVERAGEemp.getFloat(1));
					AVERAGEemp.next();
					RESULT.next();

				}

				AVERAGEdept.next();
				avgDept.add(AVERAGEdept.getFloat(1));
				String chartPath = "C:/Users/GIANLUCA/git/test/WebContent/LineChart.png";

				request.setAttribute("averageDept", avgDept.toArray(new Float[avgDept.size()]));
				request.setAttribute("Departments", createArray(dept));
				request.setAttribute("Employees", createArray(emp));
				request.setAttribute("Charges", createArray(charge));
				request.setAttribute("avgEmp", avgEmp);				
				request.setAttribute("charge", charge);
				request.setAttribute("chartPath", chartPath);
				
				request.setAttribute("TableEmployee", emps.toArray(new Employee[emps.size()]));
			}

		} catch (Exception e) {
			e.printStackTrace();
			errMessage = "<script>alert('Search didn't return any results')</script>";
			request.setAttribute("errMessage", errMessage);
		}

		request.getRequestDispatcher("Productivity.jsp").forward(request, response);

	}
	
	/**
	 * This method creates an Array of String containing only one String
	 * 
	 * @param toAdd The string to be added to the Array of String
	 * @return		The Array of String
	 */
	public String[] createArray(String toAdd){
		ArrayList<String> List = new ArrayList<String>();
		List.add(toAdd);		
		String[] createdArray = List.toArray(new String[List.size()]);
		return createdArray;
 	}
	

}

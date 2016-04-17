import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.Employee;
import test.dbConn;

/**
 * Servlet implementation class LoadEmployee
 */
@WebServlet("/SubmitProdSearch")
public class SubmitProdSearch extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;
	public String emp, charge, startDate, endDate, dept;
	ArrayList<Employee> emps = new ArrayList<Employee>();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitProdSearch() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Additions to method stub include: Gathering of all employees
	 *      available from SQL Database based on department selected
	 * 
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		float hoursWorked = 0;
		String dept = "";
		try {
			emp = request.getParameter("Employee");
			charge = request.getParameter("Charge");
			startDate = request.getParameter("date1");
			endDate = request.getParameter("date2");
			dept = request.getParameter("selectedD");
			String emp1, dept1, charge1;
			
			if (emp.equals("All")){
				emp1 = " LIKE '%' ";
			}
			
			else {emp1 = "='" + emp + "'";}
			
			if (dept.equals("All")){
				dept1 = " LIKE '%' ";
			}
			
			else {dept1 = "='" + dept + "'";}
			
			if (charge.equals("All")){
				charge1 = "LIKE '%' ";
			}
			
			else {charge1 = "='" + charge + "'";}
			
			RESULT = new dbConn().start("SELECT SUM(sngHours) AS HoursWorked, SummaryOrg, Worker FROM dbo.Productivity WHERE Worker" + emp1 + " AND SummaryOrg" + dept1 + " AND JobFunction" + charge1 + " AND Month>'" + startDate + "' AND Month<'" + endDate 	+ "' GROUP BY Worker, SummaryOrg");
			
			
			//IF EMPTY WILL THROW AN ERROR
		RESULT.next();
		emps.clear();
		
		
		while(!RESULT.isAfterLast()){
			hoursWorked = RESULT.getFloat("HoursWorked");
			dept = RESULT.getString("SummaryOrg");
			emp = RESULT.getString("Worker");
			
			Employee worker = new Employee(emp, dept , hoursWorked);
			emps.add(worker);
			
			RESULT.next();
		
		}
		
		request.setAttribute("charge", charge);
		
		Employee[] TableEmployee = emps.toArray(new Employee[emps.size()]);
		request.setAttribute("TableEmployee", TableEmployee);
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		request.getRequestDispatcher("Productivity.jsp").forward(request, response);

		// ADDITIONAL CODE TO REPOPULATE THE DEPARTMENTS WILL NEED TO BE ADDED
	}

}

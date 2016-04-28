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
		String errMessage;
		float hoursWorked = 0;
		String dept = "";
		
		
			
			emps.clear(); // clear array
			
			emp = request.getParameter("selectedEmp");			//get selected employee
			charge = request.getParameter("Charge");		//get selected charge
			startDate = request.getParameter("date1");		//get selected Start date
			endDate = request.getParameter("date2");		//get selected end date
			dept = request.getParameter("selectedDept");		//get selected department
			String emp1, dept1, charge1;		
			
			
			try {
				
				System.out.println(endDate +" "+ startDate +" " + emp + " " + charge);
			
				if(emp.equals("") || charge.equals("") || dept.equals("") || startDate.equals("Start Date:") || endDate.equals("End Date:")){
					System.out.println("Work");
					errMessage = "<script>alert('Make sure all parameters for search are initilized')</script>";
					request.setAttribute("errMessage", errMessage);
				}
			
				
			//set strings for query based on selection in dropdowns
			if (emp.equals("All")){
				emp1 = " LIKE '%' ";
			}
			
			else {emp1 = "='" + emp + "'";}
			
			if (dept.equals("All")){
				dept1 = " LIKE '%' ";
			}
			
			else {dept1 = "='" + dept + "'";}
			
			if (charge.equals("All")){
				charge1 = " LIKE '%' ";
			}
			
			else {charge1 = "='" + charge + "'";}
			
			//create new connection to DB, Run SQL Query and store ResultSet
			RESULT = new dbConn().start("SELECT SUM(sngHours) AS HoursWorked, SummaryOrg, Worker FROM dbo.Productivity WHERE Worker" + emp1 + " AND SummaryOrg" + dept1 + " AND JobFunction" + charge1 + " AND Month>'" + startDate + "' AND Month<'" + endDate 	+ "' GROUP BY Worker, SummaryOrg");
			
			
			RESULT.next(); //go to first result		
		
		while(!RESULT.isAfterLast()){ //while there are still elements in result set
			
			hoursWorked = RESULT.getFloat("HoursWorked");		//get hours worked from resultset
			dept = RESULT.getString("SummaryOrg");				//get department from resultset
			emp = RESULT.getString("Worker");					//get employee from resultset
			
			Employee worker = new Employee(emp, dept , hoursWorked); //create new employee with elements from resultset
			emps.add(worker);		//add worker to array
			
			RESULT.next();
			
		
		}
		//prepare elements to be sent to JSP page
		request.setAttribute("charge", charge);
		
		
		Employee[] TableEmployee = emps.toArray(new Employee[emps.size()]);
		request.setAttribute("TableEmployee", TableEmployee);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		request.getRequestDispatcher("Productivity.jsp").forward(request, response);

	}

}

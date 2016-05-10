
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dbConn;

/**
 * Servlet implementation class LoadProdEmployee
 */
@WebServlet("/LoadProdEmployee")
public class LoadProdEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;
	public ArrayList<String> Employee = new ArrayList<String>();
	public ArrayList<String> Charge = new ArrayList<String>();
	public String dept, errMessage;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadProdEmployee() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 *      Additions to method stub include: Gathering of all employees available from SQL Database based on department selected
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		dept = request.getParameter("Department"); // Get the department selected on webpage
		try {

			if (dept.equals("")) {
				errMessage = "<script>alert('Make sure all parameters for search are initilized')</script>";
				request.setAttribute("errMessage", errMessage);
			} else {
				if (dept.equals("All")) {// If "All" run query and select all names (without duplicates)
					RESULT = new dbConn().start(
							"SELECT DISTINCT Worker FROM dbo.Productivity ORDER BY Worker");
				}

				else {// Else run query and select all name within that department (without duplicates)
					RESULT = new dbConn()
							.start("SELECT DISTINCT Worker FROM dbo.Productivity "
									+ "WHERE SummaryOrg ='" + dept
									+ "' ORDER BY Worker");
				}

				RESULT.next(); // Go to first employee
				Employee.clear();
				while (!RESULT.isAfterLast()) { // While there are still employees in ResultSet

					Employee.add(RESULT.getString("Worker")); // Add the employee to the employee array
					RESULT.next();
				}
				ArrayList<String> e = new ArrayList<String>();
				e.add(dept);

				request.setAttribute("selectedDept", dept);
				request.setAttribute("Departments", e.toArray(new String[e.size()]));
				request.setAttribute("Employees", Employee.toArray(new String[Employee.size()])); // Set Employee array on webpage
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("Productivity.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

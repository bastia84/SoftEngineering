
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
 * Servlet implementation class LoadProdCharge
 */
@WebServlet("/LoadProdCharge")
public class LoadProdCharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;
	public ArrayList<String> JFunction = new ArrayList<String>();
	public String emp, dept, errMessage;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadProdCharge() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		emp = request.getParameter("Employee"); // Get the employee selected on webpage
		dept = request.getParameter("selectedDept"); // Get the department selected on webpage
		try {
			if (emp.equals("") || dept.equals("")) {
				errMessage = "<script>alert('Make sure all parameters for search are initilized')</script>";
				request.setAttribute("errMessage", errMessage);
			}
			else {

				if (dept.equals("All") && emp.equals("All")) {// If "All" run query and select all names (without duplicates)
					RESULT = new dbConn().start(
							"SELECT DISTINCT JobFunction FROM dbo.Productivity "
									+ "WHERE SummaryOrg LIKE '%' "
									+ "AND Worker LIKE '%' "
									+ "ORDER BY JobFunction");
				}

				else if (dept.equals("All") && !(emp.equals("All"))) {// Else run query and select all name within that department (without duplicates)
					RESULT = new dbConn()
							.start("SELECT DISTINCT JobFunction FROM dbo.Productivity "
									+ "WHERE SummaryOrg LIKE '%' "
									+ "AND Worker='" + emp
									+ "' ORDER BY JobFunction");
				}

				else if (!dept.equals("All") && emp.equals("All")) {// Else run query and select all name within that department (without duplicates)
					RESULT = new dbConn()
							.start("SELECT DISTINCT JobFunction FROM dbo.Productivity "
									+ "WHERE SummaryOrg='" + dept
									+ "' AND Worker LIKE '%' "
									+ "ORDER BY JobFunction");
				}

				else {
					RESULT = new dbConn()
							.start("SELECT DISTINCT JobFunction FROM dbo.Productivity "
									+ "WHERE SummaryOrg='" + dept
									+ "' AND Worker='" + emp
									+ "' ORDER BY JobFunction");
				}

				RESULT.next(); // Go to first employee
				JFunction.clear();
				while (!RESULT.isAfterLast()) { // While there are still employees in ResultSet

					JFunction.add(RESULT.getString("JobFunction")); // Add the employee to the array
					RESULT.next();
				}

				ArrayList<String> e = new ArrayList<String>();
				e.add(emp);

				ArrayList<String> d = new ArrayList<String>();
				d.add(dept);

				// prepare elements to be sent to JSP page
				request.setAttribute("selectedEmp", emp);
				request.setAttribute("selectedDept", dept);
				request.setAttribute("Departments", d.toArray(new String[d.size()]));
				request.setAttribute("Employees", e.toArray(new String[e.size()]));
				request.setAttribute("Charges", JFunction.toArray(new String[JFunction.size()]));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/Productivity.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

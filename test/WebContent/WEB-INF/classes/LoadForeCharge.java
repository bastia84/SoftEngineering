
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
 * Servlet implementation class LoadForeDept
 */
@WebServlet("/LoadForeCharge")
public class LoadForeCharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;
	public ArrayList<String> JFunction = new ArrayList<String>();
	public String proj, dept, errMessage, proj1, dept1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadForeCharge() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proj = request.getParameter("selectedProj"); // Get the project selected on webpage
		dept = request.getParameter("Department"); // Get the department selected on webpage

		try {

			if (proj.equals("") || dept.equals("")) {
				errMessage = "<script>alert('Make sure all parameters for search are initilized')</script>";
				request.setAttribute("errMessage", errMessage);
			}
			else {
			
			if (proj.equals("All")) {
				proj1 = " LIKE '%' ";
			}

			else {
				proj1 = "='" + proj + "'";
			}

			if (dept.equals("All")) {
				dept1 = " LIKE '%' ";
			}

			else {
				dept1 = "='" + dept + "'";
			}
			
			JFunction.clear(); // clear array

			// create new connection to DB, Run SQL Query and store ResultSet
			RESULT = new dbConn().start("SELECT DISTINCT JobFunction FROM dbo.Forecasting "
					+ "WHERE Project " + proj1
					+ " AND SummaryOrg" + dept1
					+ " ORDER BY JobFunction");

			RESULT.next(); // Go to first project

			while (!RESULT.isAfterLast()) { // While there are still elements in ResultSet

				JFunction.add(RESULT.getString("JobFunction")); // Add the charge # to the charge array
				RESULT.next();
			}

			ArrayList<String> p = new ArrayList<String>();
			p.add(proj);

			ArrayList<String> d = new ArrayList<String>();
			d.add(dept);

			// prepare elements to be sent to JSP page
			request.setAttribute("Projects", p.toArray(new String[p.size()]));
			request.setAttribute("Departments", d.toArray(new String[d.size()]));
			request.setAttribute("JFunctions", JFunction.toArray(new String[JFunction.size()]));
			request.setAttribute("selectedProj", proj);
			request.setAttribute("selectedDept", dept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Forecasting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

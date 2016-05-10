
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
 * Servlet implementation class LoadProdDept
 */
@WebServlet("/LoadProdDept")
public class LoadProdDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;
	public ArrayList<String> Depts = new ArrayList<String>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadProdDept() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 *      Additions to method stub include: Gathering of all Departments from Database
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			Depts.clear(); // clear all arrays

			// create connection to DB, Run SQL Query and get ResultSet
			RESULT = new dbConn().start(
					"SELECT DISTINCT SummaryOrg FROM dbo.Productivity "
							+ "ORDER BY SummaryOrg");

			RESULT.next(); // go to first result

			while (!RESULT.isAfterLast()) { // while there are still Departments in ResultSet

				Depts.add(RESULT.getString("SummaryOrg")); // Add the department to the Department array
				RESULT.next();
			}

			// prepare elements to be sent to JSP page
			request.setAttribute("Departments", Depts.toArray(new String[Depts.size()]));

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


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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Additions to method stub include: Gathering of all Departments from
	 *      Database
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			RESULT = new dbConn().start(
					"SELECT DISTINCT SummaryOrg FROM dbo.Productivity ORDER BY SummaryOrg"); // Run
																								// Query
																								// to
																								// get
																								// all
																								// departments
																								// in
																								// Database
			RESULT.next();
			Depts.clear();

			while (!RESULT.isAfterLast()) { // while there are still Departments
											// in ResultSet

				Depts.add(RESULT.getString("SummaryOrg")); // Add the department
															// to the Department
															// array
				RESULT.next();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] Departments = Depts.toArray(new String[Depts.size()]); // change
		// arrayList
		// into
		// Array

		request.setAttribute("Departments", Departments); // Set Departments
															// array on webpage
		request.getRequestDispatcher("Productivity.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

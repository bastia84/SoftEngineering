
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
	public String proj;
	public String dept;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadForeCharge() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proj = request.getParameter("selectedProj"); // Get the
		// project
		// selected on
		// webpage
		dept = request.getParameter("Department");
		
		System.out.println(proj +" " + dept);
		
		try {
			
			JFunction.clear(); //clear array
			
			//create new connection to DB, Run SQL Query and store ResultSet
			RESULT = new dbConn().start("SELECT DISTINCT JobFunction FROM dbo.Forecasting WHERE Project ='"	+ proj + "' AND SummaryOrg='" + dept + "' ORDER BY JobFunction");
				
			RESULT.next(); // Go to first project
			
			
			while (!RESULT.isAfterLast()) { // While there are still elements in ResultSet
				
				System.out.println(RESULT.getString("JobFunction"));
				JFunction.add(RESULT.getString("JobFunction")); // Add the charge # to the charge array
				
				RESULT.next();
			}
			RESULT.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ArrayList<String> p = new ArrayList<String>();
		p.add(proj);
		String[] Projects = p.toArray(new String[p.size()]);
		request.setAttribute("Projects", Projects);
		
		ArrayList<String> d = new ArrayList<String>();
		d.add(dept);
		String[] Departments = d.toArray(new String[d.size()]);
		request.setAttribute("Departments", Departments);
		
		//prepare elements to be sent to JSP page
		String selectedProj = proj;
		request.setAttribute("selectedProj", selectedProj);
		String selectedDept = dept;
		request.setAttribute("selectedDept", selectedDept);
		String[] JFunctions = JFunction.toArray(new String[JFunction.size()]);
		request.setAttribute("JFunctions", JFunctions);
		
		request.getRequestDispatcher("/Forecasting.jsp").forward(request, response);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


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
 * Servlet implementation class SubmitForeSearch
 */
@WebServlet("/SubmitForeSearch")
public class SubmitForeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String proj, dept, dateStart, dateEnd, chargeIn, errMessage, proj1, dept1, charge1;
	public static ResultSet RESULT;
	public ArrayList<String> Department = new ArrayList<String>();
	public ArrayList<String> Charge = new ArrayList<String>();
	public ArrayList<String> Project = new ArrayList<String>();
	public ArrayList<Float> hrs = new ArrayList<Float>();
	public ArrayList<String> times = new ArrayList<String>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitForeSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		proj = request.getParameter("selectedProj"); // get selected project
		dept = request.getParameter("selectedDept"); // get selected department
		dateStart = request.getParameter("date1"); // get selected date start
		dateEnd = request.getParameter("date2"); // get selected date end
		chargeIn = request.getParameter("JFunction"); // get selected charge #

		try {

			// clear all arrays
			Department.clear();
			Project.clear();
			Charge.clear();
			hrs.clear();
			times.clear();

			if (proj.equals("")
					|| chargeIn.equals("")
					|| dept.equals("")
					|| dateStart.equals("Start Date:")
					|| dateEnd.equals("End Date:")) {

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
				
				
				
				if (chargeIn.equals("All")) {
					charge1 = " LIKE '%' ";
				}

				else {
					charge1 = "='" + chargeIn + "'";
				}
				 
				// create new connection to DB, run query and return Result Set
				RESULT = new dbConn()
						.start("SELECT SummaryOrg, Project, JobFunction, sngETCHours, Month FROM dbo.Forecasting"
								+ " WHERE Project " + proj1
								+ " AND SummaryOrg " + dept1
								+ " AND JobFunction " + charge1
								+ " AND Month>'" + dateStart
								+ "' AND Month<'" + dateEnd
								+ "' GROUP BY Project, JobFunction, SummaryOrg, sngETCHours, Month ORDER BY Project, JobFunction");

				RESULT.next(); // Go to first result

				while (!RESULT.isAfterLast()) { // while still elements in result set

					Department.add(RESULT.getString("SummaryOrg")); // get department from resultset
					Project.add(RESULT.getString("Project")); // get project from resultset
					Charge.add(RESULT.getString("JobFunction")); // get charge from resultset
					hrs.add(RESULT.getFloat("sngETCHours")); // get hours from resultset
					times.add(RESULT.getString("Month")); // get date from resultset

					RESULT.next(); // go to next result
				}
				RESULT.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			errMessage = "<script>alert('Search didn't return any results')</script>";
			request.setAttribute("errMessage", errMessage);
		}

		// get ready to pass back elements to JSP page
		request.setAttribute("Departments", createArray(dept));
		request.setAttribute("Projects", createArray(proj));
		request.setAttribute("JFunctions", createArray(chargeIn));
		request.setAttribute("resultDepartments", Department.toArray(new String[Department.size()]));
		request.setAttribute("resultProjects", Project.toArray(new String[Project.size()]));
		request.setAttribute("resultCharges", Charge.toArray(new String[Charge.size()]));
		request.setAttribute("resulthrs", hrs.toArray(new Float[hrs.size()]));
		request.setAttribute("resulttimes", times.toArray(new String[times.size()]));
		
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

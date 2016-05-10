
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dbConn;
import test.Employee;

/**
 * Servlet implementation class SubmitSkillSearch
 */
@WebServlet("/SubmitSkillSearch")
public class SubmitSkillSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String emp, dept, skill, errMessage;
	private static ResultSet RESULT;
	public ArrayList<Employee> emps = new ArrayList<Employee>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitSkillSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		emp = request.getParameter("Employee");
		dept = request.getParameter("selectedD");

		try {

			if (emp.equals("") || dept.equals("")) {
				errMessage = "<script>alert('Make sure all parameters for search are initilized')</script>";
				request.setAttribute("errMessage", errMessage);

			}

			else {

				if (emp.equals("All") && dept.equals("All")) {

					RESULT = new dbConn().start(
							"SELECT COUNT(JobFunction) AS experience, JobFunction, SummaryOrg, Worker FROM dbo.Productivity WHERE Worker LIKE '%' AND SummaryOrg LIKE '%' GROUP BY Worker, JobFunction, SummaryOrg ORDER BY Worker, experience DESC");
				}

				else if (!emp.equals("All") && dept.equals("All")) {
					RESULT = new dbConn()
							.start("SELECT COUNT(JobFunction) AS experience, JobFunction, SummaryOrg, Worker FROM dbo.Productivity "
									+ "WHERE Worker='" + emp 
									+ "' AND SummaryOrg LIKE '%'"
									+ " GROUP BY Worker, JobFunction, SummaryOrg "
									+ "ORDER BY Worker, experience DESC");
				}

				else if (emp.equals("All") && !dept.equals("All")) {
					RESULT = new dbConn()
							.start("SELECT COUNT(JobFunction) AS experience, JobFunction, SummaryOrg, Worker FROM dbo.Productivity "
									+ "WHERE Worker LIKE '%' "
									+ "AND SummaryOrg='" + dept 
									+ "' GROUP BY Worker, JobFunction, SummaryOrg "
									+ "ORDER BY Worker, experience DESC");
				}

				else {
					RESULT = new dbConn()
							.start("SELECT COUNT(JobFunction) AS experience, JobFunction, SummaryOrg, Worker FROM dbo.Productivity "
							+ "WHERE Worker='" + emp
							+ "' AND SummaryOrg='" + dept 
							+ "' GROUP BY Worker, JobFunction, SummaryOrg "
							+ "ORDER BY Worker, experience DESC");
				}

				RESULT.next();
				emps.clear();
				emp = "";
				skill = "";
				HashMap<String, ArrayList<String>> workerSkill = new HashMap<String, ArrayList<String>>();

				while (!RESULT.isAfterLast()) {
					skill = RESULT.getString("JobFunction");
					emp = RESULT.getString("Worker");

					if (workerSkill.containsKey(emp)) {
						workerSkill.get(emp).add(skill);
					}

					else {
						ArrayList<String> s = new ArrayList<String>();
						s.add(skill);
						workerSkill.put(emp, s);
					}
					RESULT.next();
				}

				for (String str : workerSkill.keySet()) {
					Employee worker = new Employee(str, dept, workerSkill.get(str).toArray());
					emps.add(worker);
				}

				request.setAttribute("TableEmployee", emps.toArray(new Employee[emps.size()]));
				request.setAttribute("Employees", createArray(emp));
				request.setAttribute("Departments", createArray(dept));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			errMessage = "<script>alert('Search didn't return any results')</script>";
			request.setAttribute("errMessage", errMessage);
		}
		request.getRequestDispatcher("Skills.jsp").forward(request, response);
	}

	/**
	 * This method creates an Array of String containing only one String
	 * 
	 * @param toAdd
	 *            The string to be added to the Array of String
	 * @return The Array of String
	 */
	public String[] createArray(String toAdd) {
		ArrayList<String> List = new ArrayList<String>();
		List.add(toAdd);
		String[] createdArray = List.toArray(new String[List.size()]);
		return createdArray;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

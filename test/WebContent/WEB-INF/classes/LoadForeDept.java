	

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
@WebServlet("/LoadForeDept")
public class LoadForeDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;
	public ArrayList<String> Department = new ArrayList<String>();
	public String proj;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadForeDept() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		proj = request.getParameter("Project"); 	//get selected project
		ArrayList<String> p = new ArrayList<String>();
		p.add(proj);
		try {
			Department.clear(); // clear array
			
			//create connection to DB, Run SQL Query, store ResultSet
			RESULT = new dbConn().start("SELECT DISTINCT SummaryOrg FROM dbo.Forecasting WHERE Project ='" + proj + "' ORDER BY SummaryOrg");
			

			RESULT.next(); // Go to first department
			
			
			while (!RESULT.isAfterLast()) { // While there are still elements in ResultSet
				
				Department.add(RESULT.getString("SummaryOrg")); // Add the departments to the department array
				RESULT.next();
			}
			
			RESULT.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String[] Projects = p.toArray(new String[p.size()]);
		
		System.out.println("array length" + Projects.length);
		request.setAttribute("Projects", Projects);
		
		
		//prepare elements to be sent to JSP page
		String selectedProj = proj;
		request.setAttribute("selectedProj", selectedProj);
		String[] Departments = Department.toArray(new String[Department.size()]);
		request.setAttribute("Departments", Departments);
		
		request.getRequestDispatcher("Forecasting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

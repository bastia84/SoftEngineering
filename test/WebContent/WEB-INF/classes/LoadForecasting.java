

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
 * Servlet implementation class LoadForecasting
 */
@WebServlet("/LoadForecasting")
public class LoadForecasting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;
	public ArrayList<String> Project = new ArrayList<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadForecasting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Project.clear();				//clear all arrays
			
			//create new connection to DB, run query, get ResultSet
			RESULT = new dbConn().start(
					"SELECT DISTINCT Project FROM dbo.Forecasting ORDER BY Project"); 
			
			RESULT.next();					//go to first result
			
			while (!RESULT.isAfterLast()) { // while there are still Projects in ResultSet

				Project.add(RESULT.getString("Project")); // Add the project to the projects array
				RESULT.next();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		//prepare elements to send back to JSP page
		String[] Projects = Project.toArray(new String[Project.size()]); 
		request.setAttribute("Projects", Projects); 
		
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

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
 * Servlet implementation class LoadDept
 */
@WebServlet("/LoadDept")
public class LoadDept extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;
	public ArrayList<String> Depts = new ArrayList<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadDept() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
				RESULT = new dbConn().start("SELECT DISTINCT SummaryOrg FROM dbo.Productivity");
				RESULT.next();
				Depts.clear();
			while (!RESULT.isAfterLast()){
				
				Depts.add(RESULT.getString("SummaryOrg"));
				//System.out.println(RESULT.getString("SummaryOrg"));
				RESULT.next();
				}
		}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		String[] Departments = Depts.toArray(new String[Depts.size()]);
		
		request.setAttribute("Departments", Departments);
	    request.getRequestDispatcher("/Skills.jsp").forward(request, response);;
	    //response.sendRedirect("/Skills.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

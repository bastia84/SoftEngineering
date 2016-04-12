

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
 * Servlet implementation class LoadEmployee
 */
@WebServlet("/LoadEmployee")
public class LoadEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;
	public ArrayList<String> Employee = new ArrayList<String>();
	public ArrayList<String> Depts = new ArrayList<String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String dept = request.getParameter("Department");
			Depts.add(dept);
			System.out.println(dept);
			RESULT = new dbConn().start("SELECT DISTINCT Worker FROM dbo.Productivity WHERE SummaryOrg ='" + dept + "'");
			RESULT.next();
			Employee.clear();
		while (!RESULT.isAfterLast()){
			
			Employee.add(RESULT.getString("Worker"));
			//System.out.println(RESULT.getString("SummaryOrg"));
			RESULT.next();
			}
	}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	String[] Employees = Employee.toArray(new String[Employee.size()]);
	/*String[] Departments = Depts.toArray(new String[Depts.size()]);
	request.setAttribute("Departments", Departments);*/
	request.setAttribute("Employees", Employees);
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



import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public String emp, dept;
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
			
			System.out.println(emp + " " + dept);
			try {
			if(emp.equals("All") && dept.equals("All")){
			
				RESULT = new dbConn().start("SELECT COUNT(JobFunction) AS experience, JobFunction, SummaryOrg, Worker FROM dbo.Productivity WHERE Worker LIKE '%' AND SummaryOrg LIKE '%' GROUP BY Worker, JobFunction, SummaryOrg ORDER BY Worker, experience DESC");
			}
			
			else if (! emp.equals("All") && dept.equals("All")){
				RESULT = new dbConn().start("SELECT COUNT(JobFunction) AS experience, JobFunction, SummaryOrg, Worker FROM dbo.Productivity WHERE Worker='" + emp + "' AND SummaryOrg LIKE '%' GROUP BY Worker, JobFunction, SummaryOrg ORDER BY Worker, experience DESC");
			}
			
			else if (emp.equals("All") && ! dept.equals("All")){
				RESULT = new dbConn().start("SELECT COUNT(JobFunction) AS experience, JobFunction, SummaryOrg, Worker FROM dbo.Productivity WHERE Worker LIKE '%' AND SummaryOrg='" + dept + "' GROUP BY Worker, JobFunction, SummaryOrg ORDER BY Worker, experience DESC");
			}
			
			else {
				RESULT = new dbConn().start("SELECT COUNT(JobFunction) AS experience, JobFunction, SummaryOrg, Worker FROM dbo.Productivity WHERE Worker='" + emp + "' AND SummaryOrg='" + dept + "' GROUP BY Worker, JobFunction, SummaryOrg ORDER BY Worker, experience DESC");
			}
			
			RESULT.next();
			emps.clear();
			emp = "";
			int z;
			String emp1;
			ArrayList<String> skill = new ArrayList<String>();
			emp = RESULT.getString("Worker");
			
			while (!RESULT.isAfterLast()){
				z = 0;
				emp1 = RESULT.getString("Worker");
				
				if(emp.equals(emp1) && z < 3)
				{
					System.out.println("SAME");
					skill.add(RESULT.getString("JobFunction"));
					dept = RESULT.getString("SummaryOrg");
					z++;
				
				}
				
				else {
					System.out.println("ADDING");
					String[] skills = skill.toArray(new String[skill.size()]);
					Employee worker = new Employee(emp, dept, skills);
					emps.add(worker);
					skill.clear();
					emp = emp1;
				}
				
					
				
				RESULT.next();
								
				
			}
				System.out.println("ADDING");
				String[] skills = skill.toArray(new String[skill.size()]);
				Employee worker = new Employee(emp, dept, skills);
				emps.add(worker);
				skill.clear();
				
				Employee[] TableEmployee = emps.toArray(new Employee[emps.size()]);
				request.setAttribute("TableEmployee", TableEmployee);
			
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("Skills.jsp").forward(request, response);
		}
		
		
	
		

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

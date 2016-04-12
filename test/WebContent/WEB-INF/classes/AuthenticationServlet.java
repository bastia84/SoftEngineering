

import java.io.IOException;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dbConn;



/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
	private static ResultSet RESULT;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String messages="";
		String password = request.getParameter("Password");
		
		System.out.println("Work");
		try {
			System.out.println(password);
			RESULT = new dbConn().start("SELECT UName FROM dbo.Authen WHERE PWord='" + password + "'");
				
			if (RESULT.next()){
				String user = RESULT.getString("UName");
				if (user.equals(request.getParameter("Username"))){
					//messages = "<script>alert('Welcome " + user + "')</script>";
					//Cookie userIdCookie = new Cookie("userID", user);
					//userIdCookie.setPath("/");
					//response.addCookie(userIdCookie);
					response.sendRedirect("http://localhost:8080/test/LoadDept");

					
				}	
			}
			else {
				messages = "<script>alert('Invalid Username/Password')</script>";
				request.setAttribute("messages", messages);
				request.getRequestDispatcher("/MyJsp.jsp").forward(request, response);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

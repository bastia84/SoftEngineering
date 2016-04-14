import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Additions to method stub include: Validation of credentials from Log
	 *      In page. If Log In successful redirect to Skills webpage load
	 *      servlet
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String messages = "";
		String password = request.getParameter("Password"); // Get password from
															// input field

		try {

			RESULT = new dbConn()
					.start("SELECT UName FROM dbo.Authen WHERE PWord='"
							+ password + "'"); // Run SQL Query to database

			if (RESULT.next()) { // If ResultSet has a value keep going
				String user = RESULT.getString("UName"); // Get the username
															// from ResultSet
				
				if (user.equals(request.getParameter("Username"))) { // If the
																		// username
																		// from
																		// the
																		// ResultSet
																		// and
																		// the
																		// username
																		// from
																		// the
																		// input
																		// field
																		// are
																		// the
																		// same
																		// redirect
					response.sendRedirect(
							"http://localhost:8080/test/LoadDept");
				}
			} else { // Else display error message
				messages = "<script>alert('Invalid Username/Password')</script>";
				request.setAttribute("messages", messages);
				request.getRequestDispatcher("/MyJsp.jsp").forward(request,
						response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

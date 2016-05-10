
import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dbConn;

/**
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultSet RESULT;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String messages = "";
		String username = request.getParameter("Username"); // Get username
		String oldPWord = request.getParameter("oldPWord"); // Get Password
		String newPWord = request.getParameter("newPWord");
		String newPWord2 = request.getParameter("newPWord2");
		System.out.println(newPWord);
		System.out.println(newPWord2);
		try {

			RESULT = new dbConn()
					.start("SELECT PWord FROM dbo.Authen WHERE Uname='"
							+ username + "'"); // Run SQL Query to database
			
			

			if (RESULT.next()) { // If ResultSet has a value keep going
				
				if (oldPWord.equals("")) {
					// Display error message
					messages = "<script>alert('Username Already Exists. Please try another or Enter old Password to update Passwords')</script>";
					
				} else {
					
					if (oldPWord.equals(RESULT.getString("PWord"))){
					if (newPWord.equals(newPWord2)) {
						RESULT = new dbConn()
								.start("UPDATE dbo.Authen SET PWord='" + newPWord + "' WHERE UName='" + username + "'");
						messages = "<script>alert('Password has been updated successfully')</script>";
					} else {
						messages = "<script>alert('New Passwords do not Match. Please try again')</script>";
						
					}
					}
					else {
						messages = "<script>alert('Username and Old Password do not match')</script>";
					}
				}
			} else if ((oldPWord.equals("")) && !(RESULT.next())) {
				if (newPWord.equals(request.getParameter("newPWord2"))) {
					RESULT = new dbConn()
							.start("INSERT INTO dbo.Authen (Uname, PWord) VALUES ('" + username + "', '" + newPWord + "')");
					messages = "<script>alert('New User has been created')</script>";
				} else {
					messages = "<script>alert('New Passwords do not Match. Please try again')</script>";
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("messages", messages);
		request.getRequestDispatcher("/ManageAuthPage.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class creates a connection to the database, runs the query string and
 * returns a ResultSet
 * 
 * @author GIANLUCA
 * @version 4/12/16
 */
public class dbConn {

	/**
	 * This function will call a function to run the query and returns a result
	 * set with the result of the query. If query doens't return values than
	 * ResultSet is null
	 * 
	 * @param queryIn
	 *            String - This is the SQL Query that will be passed to the
	 *            database to retrieve results
	 * @return ResultSet This is the collection of results.
	 * @throws SQLException
	 */
	public ResultSet start(String queryIn) throws SQLException {
		String query = queryIn;
		return getData(query);

	}

	/**
	 * This function creates the connection to the database then creates an
	 * empty ResultSet and finally runs the query collecting the results
	 * 
	 * @param query
	 *            String - SQL String to be passed to the database to retrieve
	 *            ResultSet
	 * @return ResultSet Collection of Results
	 * @throws SQLException
	 */
	public ResultSet getData(String query) throws SQLException {
		Connection CON = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // connect
																			// to
																			// database
																			// utilizing
																			// Java
																			// DataBase
																			// Connector
			CON = DriverManager.getConnection(URL, USER, PASS);
			RESULT = CON.createStatement().executeQuery(query); // execute SQL
																// query and
																// assign
																// ResultSet
		}

		catch (SQLException ex) { // Catch SQL Exceptions
			System.err.println(">> " + ex.getMessage());
		}

		catch (ClassNotFoundException e) { // Catch connection issues
			e.printStackTrace();
		}

		return RESULT; // Return Results
	}

	// Path to the database, with security standards
	private String URL = "jdbc:sqlserver://localhost;databaseName={SoftEngineering};integratedSecurity=true;";

	// Because we are using integrated security USER and PASS are empty
	private String USER = "";
	private String PASS = "";

	private static ResultSet RESULT;
}


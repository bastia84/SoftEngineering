package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConn {

public ResultSet start(String queryIn) throws SQLException{
	String query = queryIn;
	System.out.println(query);
	return getData(query);
	
}
	
public ResultSet getData(String query) throws SQLException{
	Connection CON = null;
	
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		CON = DriverManager.getConnection(URL, USER, PASS);
		RESULT = CON.createStatement().executeQuery(query);
		/*if (RESULT.next()){
			System.out.println(RESULT.getString("UName"));
		}
		else {
			System.out.println("nothing");
		}*/
	}
	catch (SQLException ex){
		System.err.println(">> " + ex.getMessage());
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return RESULT;
}
	

private String URL ="jdbc:sqlserver://localhost;databaseName={SoftEngineering};integratedSecurity=true;";
private String USER = "";
private String PASS = "";
private static ResultSet RESULT;
}

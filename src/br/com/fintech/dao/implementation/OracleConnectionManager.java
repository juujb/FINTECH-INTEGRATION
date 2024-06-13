package br.com.fintech.dao.implementation;
  
import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnectionManager {
		
	private static OracleConnectionManager instance;
	
	private OracleConnectionManager() {}
	
	public static OracleConnectionManager getInstance() {
		if (instance == null) {
			instance = new OracleConnectionManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		Connection connection = null;
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
  
        	connection = DriverManager.getConnection(
        			"jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
        			"RM553298", "250103");
  
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return connection;
    }
}

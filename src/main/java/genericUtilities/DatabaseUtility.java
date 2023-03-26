package genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	private Connection connection;
	
	public void databaseInitialization(String jdbcUrl, String username, String password) {
		Driver dbDriver = null;
		try {
			dbDriver = new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(dbDriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getDataFromDatabase(String query, String colName) throws SQLException {
		
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
				
		List<String> list = new ArrayList<>();
		while(result.next()) {
			list.add(result.getString(colName));
		}
		return list;
	}
	
	public int modifyDatabase(String query) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int result = 0;
		try {
			result = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	public void closeDatabase() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

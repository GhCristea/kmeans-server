/**
 * 
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Cristea Gheorghita
 *
 */
public class DbAccess {

	final String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
	final String DBMS = "jdbc:mysql";
	final String SERVER = "localhost";
	final String DATABASE = "MApDB";
	final String PORT = "3306";
	final String USER_ID = "MapUser";
	final String PASSWORD = "map";
	Connection conn;

	/**
	 * 
	 */
	public DbAccess() {
		// TODO Auto-generated constructor stub
	}

	public void initConnection() throws DatabaseConnectionException {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE, USER_ID, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	void closeConnection() throws SQLException {
		conn.close();
	}

}

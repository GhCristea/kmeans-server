/**
 * 
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to initiate the connection with database.
 * @author Cristea Gheorghita
 *
 */
public class DbAccess {

	private final String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
	private final String DBMS = "jdbc:mysql";
	private final String SERVER = "localhost";
	private final String DATABASE = "MApDB";
	private final String PORT = "3306";
	private final String USER_ID = "MapUser";
	private final String PASSWORD = "map";
	private Connection connection;

	
/**
 * Initiate the connection with database.
 * @throws DatabaseConnectionException
 */
	public void initConnection() throws DatabaseConnectionException {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE, USER_ID, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getter for connection.
	 * @return connection.
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Close connection.
	 * @throws SQLException
	 */
	void closeConnection() throws SQLException {
		connection.close();
	}

}

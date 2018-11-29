/**
 * 
 */
package database;

/**
 * @author Cristea Gheorghita
 *
 */
public class DatabaseConnectionException extends Exception {

	String message = "database connection error";
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DatabaseConnectionException() {
	}

	/**
	 * @param arg0
	 */
	public DatabaseConnectionException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public DatabaseConnectionException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DatabaseConnectionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public DatabaseConnectionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}

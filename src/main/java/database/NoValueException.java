/**
 * 
 */
package database;

/**
 * @author Cristea Gheorghita
 *
 */
public class NoValueException extends Exception {

	String message = "no value";
	/**
	 * 
	 */
	private static final long serialVersionUID = 3404663938936582571L;

	/**
	 * 
	 */
	public NoValueException() {
	}

	/**
	 * @param message
	 */
	public NoValueException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NoValueException(Throwable cause) {
		super(cause);
		}

	/**
	 * @param message
	 * @param cause
	 */
	public NoValueException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NoValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}

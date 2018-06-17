/**
 * 
 */
package database;

/**
 * @author Cristea Gheorghita
 *
 */
public class EmptySetException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EmptySetException() {
	}

	/**
	 * @param message
	 */
	public EmptySetException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public EmptySetException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EmptySetException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public EmptySetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

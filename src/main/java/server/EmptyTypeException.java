package server;

public class EmptyTypeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3636351170349428912L;
	String message = "empty set";
	
	@Override
	public String getMessage() {
		return message;
	}

}

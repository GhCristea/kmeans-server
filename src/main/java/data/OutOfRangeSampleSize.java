package data;

public class OutOfRangeSampleSize extends Throwable {

	String message = "your k number is greater then the possible number of clusters";
	/**
	 * 
	 */
	private static final long serialVersionUID = 97797707055738020L;

	public OutOfRangeSampleSize() {
	}

	public void print() {
		System.out.println(message);

	}

	@Override
	public String getMessage() {
		return message;
	}
}

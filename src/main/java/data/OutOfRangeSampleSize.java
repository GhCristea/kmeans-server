package data;

public class OutOfRangeSampleSize extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OutOfRangeSampleSize() {
	};
	public void print() {
		System.out.println("il numero k di cluster inserito da tastiera "
				+ "è maggiore maggiore rispetto al numero di centroidi "
				+ "generabili dall'insieme di transazioni.");
		
	}

}

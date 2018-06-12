package data;

/**
 * Abstract class Attribute.
 * 
 * @author Cristea Gheorghita
 *
 */

public abstract class Attribute {

	private String name;
	private int index;

	/**
	 * Class constructor.
	 * 
	 * @param setName
	 *            attribute's name
	 * @param setIndex
	 *            attribute's index
	 */
	public Attribute(final String setName, final int setIndex) {
		this.index = setIndex;
		this.name = setName;
	}

	/**
	 * Index getter.
	 * 
	 * @return attribute's index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Name getter.
	 * 
	 * @return attribute's name
	 */
	public String getName() {
		return name;
	}

	/*
	* Returns attribute's name.
	 */
	@Override
	public String toString() {
		return name;
	}

}

package data;

import java.io.Serializable;

/**
 * Abstract class Attribute.
 * 
 * @author Cristea Gheorghita
 *
 */

public abstract class Attribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4296182488932556748L;
	private String name;
	private int index;

	/**
	 * Class constructor.
	 * 
	 * @param setName
	 *            {@link Attribute}'s name
	 * @param setIndex
	 *            {@link Attribute}'s index
	 */
	public Attribute(final String setName, final int setIndex) {
		this.index = setIndex;
		this.name = setName;
	}

	/**
	 * Index getter.
	 * 
	 * @return {@link Attribute}'s index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Name getter.
	 * 
	 * @return {@link Attribute}'s name
	 */
	public String getName() {
		return name;
	}

	/*
	* Returns {@link Attribute}'s name.
	 */
	@Override
	public String toString() {
		return name;
	}

}

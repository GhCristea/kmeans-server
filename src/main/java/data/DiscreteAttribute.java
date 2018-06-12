package data;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class for modelling a discrete attribute. extends {@link Attribute}.
 * implements {@link Iterable<String>}.
 * 
 * @author Cristea Gheorghita
 */
public final class DiscreteAttribute extends Attribute implements Iterable<String> {

	private TreeSet<String> values;

	/**
	 * Class construcor.
	 * 
	 * @param setName
	 *            name of attribute
	 * @param setIndex
	 *            index of attribute
	 * @param setValues
	 *            value of attribute
	 */
	public DiscreteAttribute(final String setName, final int setIndex, final TreeSet<String> setValues) {
		super(setName, setIndex);
		this.values = setValues;
	}

	/**
	 * Gets the set of continuous attribute's distinct values-
	 * 
	 * @return number of continuous values
	 */
	public int getNumberOfDistinctValues() {
		return values.size();
	}

	/**
	 * Calculate the frequency of the specified value into data.
	 * 
	 * @param data
	 *            input table
	 * @param idList
	 *            indexes of values
	 * @param value
	 *            value to calculate the frequecy
	 * @return frequency of value (Integer)
	 */
	public int frequency(final Data data, final Set<Integer> idList, final String value) {
		int frequency = 0;
		for (final Integer integer : idList) {
			if (data.getAttributeValue(integer, getIndex()).equals(value)) {
				frequency++;
			}
		}
		return frequency;
	}

	/**
	 * (no-javadoc)
	 * 
	 * @see {@link java.lang.Iterable<java.lang.String>.iterator}
	 */
	@Override
	public Iterator<String> iterator() {
		return values.iterator();
	}

}

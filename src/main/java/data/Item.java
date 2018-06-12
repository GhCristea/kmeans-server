package data;

import java.util.Set;

/**
 * Class for modelling pairs of ({@link Attribute}, value).
 * @author Cristea Gheorghita
 *
 */
public abstract class Item {
	
	private Attribute attribute;
	Object value;
	
	/**
	 * Class constructor.
	 * @param setAttribute {@link Attribute} to set.
	 * @param setValue value to set.
	 */
	public Item(final Attribute setAttribute, final Object setValue) {
		this.attribute = setAttribute;
		this.value = setValue;
	}
	
	
	/**
	 * Getter for attribute.
	 * @return item's {@link Attribute}
	 */
	public Attribute getAttribute() {
		return attribute;
	}
	
	/**
	 * Getter for the value.
	 * @return Item's value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @return Item's value.
	 */
	@Override
	public String toString() {
		
		return "" +value;
	}
	
/**
 * Abstract method who claculate the distance between two Items.
 * @param inputItem the {@link Item} to calculate the distance to.
 * @return distance between the current {@link Item} and the input {@link Item}.
 */
	public abstract double distance(final Object inputItem);
	
	/**
	 * Updade the value with {@link Data#computePrototype(Set, Attribute)}.
	 * @param data data to compute the prototype.
	 * @param clusteredData indexes for values.
	 */
	public void update(final Data data, final Set<Integer> clusteredData) {
		value = data.computePrototype(clusteredData, attribute);
	}
	
	
}

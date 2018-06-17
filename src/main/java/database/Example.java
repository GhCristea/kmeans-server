package database;

import java.util.ArrayList;
import java.util.List;

/**
 * Class reppresenting a row of values.
 * 
 * @author Cristea Gheorghita
 *
 */
public class Example implements Comparable<Example> {

	private List<Object> example = new ArrayList<Object>();

	/**
	 * Add a value to the {@link Example}.
	 * @param value value to add.
	 */
	public void add(final Object value) {
		example.add(value);
	}

	/**
	 * Getter for a value.
	 * @param index index of the return value.
	 * @return value indexed at index.
	 */
	public Object get(final int index) {
		return example.get(index);
	}
	
	/**
	 * implements {@link Comparable#compareTo(Object)}.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compareTo(final Example example) {

		int index = 0;
		for (final Object value : example.example) {
			if (!value.equals(this.example.get(index)))
				return ((Comparable) value).compareTo(example.get(index));
			index++;
		}
		return 0;
	}

	/**
	 * @return the row of values.
	 */
	@Override
	public String toString() {
		String string = "";
		for (final Object value : example)
			string += value.toString() + " ";
		return string;
	}

}
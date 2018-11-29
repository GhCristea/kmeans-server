package data;

import java.io.Serializable;
import java.util.Set;

/**
 * Class for modelling a collection of {@link Item}.
 * 
 * @author Cristea Gheorghita
 *
 */
public class Tuple implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 567877450055162399L;
	Item[] tuple;

	/**
	 * Class constructor.
	 * 
	 * @param size
	 *            size for {@link Item} collection.
	 */
	public Tuple(final int size) {
		tuple = new Item[size];
	}

	/**
	 * Getter for size of {@link Item}'s collection.
	 * 
	 * @return size of {@link Item}'s collection.
	 */
	public int getLength() {
		return tuple.length;
	}

	/**
	 * Getter for a specified {@link Item} into collection.
	 * 
	 * @param index
	 *            pecified index.
	 * @return {@link Item} located at index
	 */
	public Item get(final int index) {
		return tuple[index];
	}

	/**
	 * Adds an {@link Item} into collection.
	 * 
	 * @param inputItem
	 *            {@link Item} to add.
	 * @param index
	 *            location for the input {@link Item}.
	 */
	public void add(final Item inputItem, final int index) {
		tuple[index] = inputItem;
	}

	/**
	 * Calculate the distance between two {@link Tuple}.
	 * 
	 * @param inputTuple
	 *            {@link Tuple} to calcolate the distance to.
	 * @return the distance between current {@link Tuple} and the imput
	 *         {@link Tuple}.
	 */
	public double getDistance(final Tuple inputTuple) {
		double distance = 0.0;
		for (int i = 0; i < inputTuple.getLength(); i++) {
			distance += tuple[i].distance(inputTuple.get(i));
		}
		return distance;
	}

	/**
	 * Avverage distance between the current {@link Tuple} and tuples indexed in
	 * clusterdData from {@link Data}.
	 * 
	 * @param data
	 *            input {@link Data}.
	 * @param clusteredData
	 *            index for Tuples.
	 * @return calculated value.
	 */
	public double avgDistance(final Data data, final Set<Integer> clusteredData) {
		
		double totalDistance = 0.0;

		for (Integer integer : clusteredData) {
			totalDistance += getDistance(data.getItemSet(integer));
		}
		
		return totalDistance / clusteredData.size();
	}
	
	@Override
	public String toString() {
		String out = ""; 
		for (int i = 0; i < tuple.length; i++) {
			out+= tuple[i].toString();
		}
		return out;
	}
}

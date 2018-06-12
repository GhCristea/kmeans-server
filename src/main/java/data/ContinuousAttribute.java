package data;

/**
 * Specialized class for continuous attribute.
 * extends {@link Attribute}.
 * @author Cristea Gheorghita
 *
 */
 public class ContinuousAttribute extends Attribute {

	private double minRange;
	private double maxRange;

	/**
	 * Class constructor.
	 * 
	 * @param name
	 *            {@link ContinuousAttribute}'s name
	 * @param index
	 *            {@link ContinuousAttribute}'s index
	 * @param minimum
	 *            minimum range
	 * @param maximum
	 *            maximum range
	 */
	public ContinuousAttribute(final String name, final int index, final double minimum, final double maximum) {
		super(name, index);
		this.minRange = minimum;
		this.maxRange = maximum;
	}

	/**
	 * Calculate the scalled value.
	 * 
	 * @param value
	 *            to scale
	 * @return scalled value
	 */
	double getScaledValue(final Double value) {
		return (double) (value - minRange) / (maxRange - minRange);
	}
}

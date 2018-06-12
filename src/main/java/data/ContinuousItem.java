/**
 * 
 */
package data;

/**
 * Class for modelling pairs of ({@link ContinuousAttribute}, value).
 * extends {@link Item}.
 * @author Cristea Gheorghita
 *
 */
public final class ContinuousItem extends Item {

	/**
	 * Class constructor.
	 * @param attribute {@link Item}'s {@link Attribute}
	 * @param value {@link Item}'s's value.
	 */
	public ContinuousItem(final Attribute attribute, final Object value) {
		super(attribute, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data.Item#distance(finaljava.lang.Object)
	 */
	@Override
	public double distance(final Object intputItem) {
		final double thisValue = ((ContinuousAttribute) getAttribute()).getScaledValue((Double) getValue());
		final double inputValue = ((ContinuousAttribute) ((ContinuousItem) intputItem).getAttribute())
				.getScaledValue((Double) ((Item) intputItem).getValue());
		return Math.abs(inputValue - thisValue);
	}

}

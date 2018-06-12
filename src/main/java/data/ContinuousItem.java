/**
 * 
 */
package data;

/**
 * Class for modelling pairs of (Attribute, Value).
 * @author Cristea Gheorghita
 *
 */
public class ContinuousItem extends Item {

	/**
	 * Class constructor.
	 * @param attribute attribute
	 * @param value attribute's value.
	 */
	public ContinuousItem(final Attribute attribute, final Object value) {
		super(attribute, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data.Item#distance(java.lang.Object)
	 */
	@Override
	public double distance(Object intputItem) {
		double thisValue = ((ContinuousAttribute) getAttribute()).getScaledValue((Double) getValue());
		double inputValue = ((ContinuousAttribute) ((ContinuousItem) intputItem).getAttribute())
				.getScaledValue((Double) ((Item) intputItem).getValue());
		return Math.abs(inputValue - thisValue);
	}

}

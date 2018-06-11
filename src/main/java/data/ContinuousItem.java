/**
 * 
 */
package data;

/**
 * @author Cristea Gheorghita
 *
 */
public class ContinuousItem extends Item {

	/**
	 * @param attribute
	 * @param value
	 */
	public ContinuousItem(Attribute attribute, Object value) {
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

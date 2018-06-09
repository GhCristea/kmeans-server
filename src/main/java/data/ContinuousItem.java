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
		// double thisValue =
		//System.out.println(this.getValue());
		 System.out.println(((ContinuousAttribute)getAttribute()).getScaledValue((Double)this.getValue()));
		// double inputValue
		// =((ContinuousAttribute)((ContinuousItem)intputItem).getAttribute()).getScaledValue((double)((Item)intputItem).getValue());
		return 0;// Math.abs(inputValue-thisValue);
	}

}

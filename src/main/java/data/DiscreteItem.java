package data;

/**
 * Class for modelling the pairs ({@link DiscreteAttribute}, value). extends
 * {@link Item}.
 * 
 * @author Cristea Gheorghita
 *
 */
public final class DiscreteItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7394584854256268249L;

	/**
	 * Class constructor.
	 * 
	 * @param attribute
	 *            {@link Item}'s {@link DiscreteAttribute}
	 * @param value
	 *            {@link Item}'s value
	 */
	public DiscreteItem(final DiscreteAttribute attribute, final String value) {
		super(attribute, value);
	}

	@Override
	public double distance(final Object item) {
		if (getValue().equals(((Item) item).getValue())) {
			return 0;
		}
		return 1;
	}

}

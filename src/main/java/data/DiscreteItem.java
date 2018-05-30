package data;

public class DiscreteItem extends Item {

	public DiscreteItem(DiscreteAttribute attribute, String value) {
		super(attribute, value);
	}

	@Override
	public double distance(Object a) {
		if (getValue().equals(a)) {
			return 0;
		}
		return 1;
	}

}

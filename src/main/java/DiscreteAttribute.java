
public class DiscreteAttribute extends Attribute {
	public DiscreteAttribute(String name, int index, String values[]) {
		super(name, index);
		this.values = values;
	}

	public int getNumberOfDistinctValues() {
		return values.length;
	}

	public String getValues(int i) {
		return values[i];
	}

	public int frequency(Data data, ArraySet idList, String v) {
		int out = 0;
		for (int i = 0; i < idList.toArray().length; i++) {
			if (data.getAttributeValue(i, getIndex()).equals(v)) {
				out++;
			}
		}
		return out;
	}

	private String values[];

}

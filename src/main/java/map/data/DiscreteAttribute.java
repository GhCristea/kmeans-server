package map.data;

public class DiscreteAttribute extends Attribute {
	private String values[];

	public DiscreteAttribute(String name, int index, String values[]) {
		super(name, index);
		this.values = values;
	}

	public int getNumberOfDistinctValues() {
		return values.length;
	}

	public String getValue(int i) {
		return values[i];
	}

	public int frequency(Data data, ArraySet idList, String v) {
		int out = 0;
		for (int i = 0; i < idList.toArray().length; i++) {
			if (data.getAttributeValue(idList.toArray()[i], getIndex()).equals(v)) {
				out++;
			}
		}
		return out;
	}

}

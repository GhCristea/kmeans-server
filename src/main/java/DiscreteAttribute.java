
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
		
		for (int i = 0; i < data.getNumberOfExamples(); i++) {
			if(v == data.getAttributeValue(i, getIndex())) {
				idList.add(i);
			}
		}
		int a[] = idList.toArray();
		
		return a.length;
	}
	
	private String values[];
	
	
}

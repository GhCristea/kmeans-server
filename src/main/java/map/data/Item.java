package map.data;

public abstract class Item {
	private Attribute attribute;
	Object value;
	
	public Item(Attribute attribute, Object value) {
		this.attribute = attribute;
		this.value = value;
	}
	
	
	public Attribute getAttribute() {
		return attribute;
	}
	
	public Object getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		
		return (String)value;
	}
	

	public abstract double distance(Object a);
	
	void update(Data data, ArraySet clusteredData) {
		value = data.computePrototype(clusteredData, attribute);
	}
	
	
}

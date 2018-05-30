package map.data;

public abstract class Attribute {
	public Attribute(String name, int index) {
		this.index = index;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {	
		return name;
	}

	private String name;
	private int index;
	
	
}

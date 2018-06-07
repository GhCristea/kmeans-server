package data;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class DiscreteAttribute extends Attribute implements Iterable<String>{
	private TreeSet<String> values;

	public DiscreteAttribute(String name, int index, TreeSet<String> values) {
		super(name, index);
		this.values = values;
	}

	public int getNumberOfDistinctValues() {
		return values.size();
	}


	public int frequency(Data data, Set<Integer> idList, String v) {
		int out = 0;
		for (int i = 0; i < idList.toArray().length; i++) {
			if (data.getAttributeValue((int) idList.toArray()[i], getIndex()).equals(v)) {
				out++;
			}
		}
		return out;
	}

	@Override
	public Iterator<String> iterator() {
		return values.iterator();
	}

}

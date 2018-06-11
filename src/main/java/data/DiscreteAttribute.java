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


	public int frequency(Data data, Set<Integer> idList, String value) {
		int frequency = 0;
		for (Integer integer : idList) 
		{
			if (data.getAttributeValue(integer, getIndex()).equals(value)) {
				frequency++;
			}
		}
		return frequency;
	}

	@Override
	public Iterator<String> iterator() {
		return values.iterator();
	}

}

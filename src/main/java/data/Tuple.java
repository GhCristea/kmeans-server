package data;

import java.util.Set;

public class Tuple {
	Item [] tuple;
	
	public Tuple(int size) {
		tuple = new Item[size];
	}
	
	public int getLength() {
		return tuple.length;
	}
	
	public Item get(int i) {
		return tuple[i];
	}
	
	public void add(Item c, int i) {
		tuple[i] = c;
	}
	
	public double getDistance(Tuple obj) {
		double out = .0;
		for (int i = 0; i<obj.getLength(); i++) {
			out += tuple[i].distance(obj.get(i));
		}
		return out;
	}
	
	public double avgDistance(Data data, Set<Integer> clusteredData) {
		double out = 0.0, sum = 0.0;
		
		for (int i = 0; i < clusteredData.size(); i++) {
			sum+=getDistance(data.getItemSet(i));
		}
		
		out = sum / clusteredData.size();
		
		return out;
	}
	
}

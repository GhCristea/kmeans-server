package data;

import java.util.Set;

public class Tuple {
	Item[] tuple;

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
		double distance = 0.0;
		for (int i = 0; i < obj.getLength(); i++) {
			distance += tuple[i].distance(obj.get(i));
		}
		return distance;
	}

	public double avgDistance(Data data, Set<Integer> clusteredData) {
		double avverage = 0.0, total = 0.0;

		for (Integer integer : clusteredData) {
			total += getDistance(data.getItemSet(integer));
		}

		avverage = total / clusteredData.size();

		return avverage;
	}

}

package data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Data {

	private List<Example> data;
	private List<Attribute> explanatorySet = new LinkedList<>();

	class Example implements Comparable<Example> {

		List<Object> example = new ArrayList<Object>();

		void add(Object inputObject) {
			example.add(inputObject);
		}

		Object get(int index) {
			return example.get(index);
		}

		@Override
		public int compareTo(Example objectToCompare) {
			for (int i = 0; i < example.size(); i++) {
				boolean equalsTillNow = example.get(i).equals(objectToCompare.get(i));
				if (!equalsTillNow) {
					return ((String) objectToCompare.get(i)).compareTo((String) example.get(i));
				}
			}
			return 0;
		}

		@Override
		public String toString() {
			String listToString = "[";
			for (Object indexExaple : example) {
				listToString += indexExaple.toString() + ", ";
			}
			return listToString.substring(0, listToString.length() - 2) + "]";
		}

	}

	public Data() {

		TreeSet<Example> tempData = new TreeSet<>();
		Example ex0 = new Example();
		ex0.add(new String("Sunny"));
		ex0.add(new String("Hot"));
		ex0.add(new String("High"));
		ex0.add(new String("Weak"));
		ex0.add(new String("No"));

		Example ex1 = new Example();
		ex1.add(new String("Sunny"));
		ex1.add(new String("Hot"));
		ex1.add(new String("High"));
		ex1.add(new String("Strong"));
		ex1.add(new String("No"));

		Example ex2 = new Example();
		ex2.add(new String("Overcast"));
		ex2.add(new String("Hot"));
		ex2.add(new String("High"));
		ex2.add(new String("Weak"));
		ex2.add(new String("Yes"));

		Example ex3 = new Example();
		ex3.add(new String("Rain"));
		ex3.add(new String("Mild"));
		ex3.add(new String("High"));
		ex3.add(new String("Weak"));
		ex3.add(new String("Yes"));

		Example ex4 = new Example();
		ex4.add(new String("Rain"));
		ex4.add(new String("Cool"));
		ex4.add(new String("Normal"));
		ex4.add(new String("Weak"));
		ex4.add(new String("Yes"));

		Example ex5 = new Example();
		ex5.add(new String("Rain"));
		ex5.add(new String("Cool"));
		ex5.add(new String("Normal"));
		ex5.add(new String("Strong"));
		ex5.add(new String("No"));

		Example ex6 = new Example();
		ex6.add(new String("Overcast"));
		ex6.add(new String("Cool"));
		ex6.add(new String("Normal"));
		ex6.add(new String("Strong"));
		ex6.add(new String("Yes"));

		Example ex7 = new Example();
		ex7.add(new String("Sunny"));
		ex7.add(new String("Mild"));
		ex7.add(new String("High"));
		ex7.add(new String("Weak"));
		ex7.add(new String("No"));

		Example ex8 = new Example();
		ex8.add(new String("Sunny"));
		ex8.add(new String("Cool"));
		ex8.add(new String("Normal"));
		ex8.add(new String("Weak"));
		ex8.add(new String("Yes"));

		Example ex9 = new Example();
		ex9.add(new String("Rain"));
		ex9.add(new String("Mild"));
		ex9.add(new String("Normal"));
		ex9.add(new String("Weak"));
		ex9.add(new String("Yes"));

		Example ex10 = new Example();
		ex10.add(new String("Sunny"));
		ex10.add(new String("Mild"));
		ex10.add(new String("Normal"));
		ex10.add(new String("Strong"));
		ex10.add(new String("Yes"));

		Example ex11 = new Example();
		ex11.add(new String("Overcast"));
		ex11.add(new String("Mild"));
		ex11.add(new String("High"));
		ex11.add(new String("Strong"));
		ex11.add(new String("Yes"));

		Example ex12 = new Example();
		ex12.add(new String("Overcast"));
		ex12.add(new String("Hot"));
		ex12.add(new String("Normal"));
		ex12.add(new String("Weak"));
		ex12.add(new String("Yes"));

		Example ex13 = new Example();
		ex13.add(new String("Rain"));
		ex13.add(new String("Mild"));
		ex13.add(new String("High"));
		ex13.add(new String("Strong"));
		ex13.add(new String("No"));

		tempData.add(ex0);
		tempData.add(ex1);
		tempData.add(ex2);
		tempData.add(ex3);
		tempData.add(ex4);
		tempData.add(ex5);
		tempData.add(ex6);
		tempData.add(ex7);
		tempData.add(ex8);
		tempData.add(ex9);
		tempData.add(ex10);
		tempData.add(ex11);
		tempData.add(ex12);
		tempData.add(ex13);
		data = new ArrayList<Example>(tempData);

		TreeSet<String> outLookValues = new TreeSet<>();
		outLookValues.add("Overcast");
		outLookValues.add("Rain");
		outLookValues.add("Sunny");
		Attribute discreteAtt = new DiscreteAttribute("Outlook", 0, outLookValues);
		explanatorySet.add(discreteAtt);

		TreeSet<String> temperatureValues = new TreeSet<>();
		temperatureValues.add("Mild");
		temperatureValues.add("Cool");
		temperatureValues.add("Hot");
		discreteAtt = new DiscreteAttribute("Temperature", 1, temperatureValues);
		explanatorySet.add(discreteAtt);

		TreeSet<String> humidityValues = new TreeSet<>();
		humidityValues.add("High");
		humidityValues.add("Normal");
		discreteAtt = new DiscreteAttribute("Humidity", 2, humidityValues);
		explanatorySet.add(discreteAtt);

		TreeSet<String> windValues = new TreeSet<>();
		windValues.add("Weak");
		windValues.add("Strong");
		discreteAtt = new DiscreteAttribute("Wind", 3, windValues);
		explanatorySet.add(discreteAtt);

		TreeSet<String> playValues = new TreeSet<>();
		playValues.add("Yes");
		playValues.add("No");
		discreteAtt = new DiscreteAttribute("PlayTennis", 4, playValues);
		explanatorySet.add(discreteAtt);
	}

	public int getNumberOfExamples() {
		return data.size();
	}

	public int getNumberOfExplanatoryAttributes() {
		return explanatorySet.size();
	}

	public Object getAttributeValue(int exampleIndex, int attributeIndex) {
		return data.get(attributeIndex).get(exampleIndex);
	}

	public Attribute getAttribute(int index) {
		return explanatorySet.get(index);
	}

	public List<Attribute> getAttributeSchema() {
		return explanatorySet;
	}

	@Override
	public String toString() {
		String out = "";
		int i = 0;
		for (; i < explanatorySet.size() - 1; i++) {
			out += explanatorySet.get(i).getName() + ",";
		}
		out += explanatorySet.get(i).getName() + "\n";

		for (i = 0; i < data.size(); i++) {
			out += (i + 1) + ":";
			for (int j = 0; j < explanatorySet.size(); j++) {
				out += data.get(i).get(j) + ",";
			}
			out += "\n";
		}
		return out;
	}

	/*
	 * public static void main(String args[]) { Data trainingSet = new Data();
	 * System.out.println(trainingSet);
	 * 
	 * }
	 */
	public Object computePrototype(Set<Integer> clusteredData, Attribute attribute) {

		Object out = computePrototype(clusteredData, (DiscreteAttribute) attribute);
		return out;
	}

	private String computePrototype(Set<Integer> idList, DiscreteAttribute attribute) {
		String out = "";
		// TODO
		return out;
	}

	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(explanatorySet.size());
		for (int i = 0; i < explanatorySet.size(); i++) {
			tuple.add(new DiscreteItem((DiscreteAttribute) explanatorySet.get(i), (String) data.get(index).get(i)), i);
		}
		return tuple;
	}

	public int[] sampling(int k) throws OutOfRangeSampleSize {
		int[] centroidIndexes = new int[k];
		// scegli k centroid differenti in data
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < k; i++) {
			boolean found = false;
			int c;

			do {
				found = false;
				c = rand.nextInt(getNumberOfExamples());
				// verifica se il nuovo centroide non esiste gia in centroidIndexes

				for (int j = 0; j < i; j++) {
					if (compare(centroidIndexes[j], c)) {
						found = true;
						break;
					}
				}

			} while (found);
			centroidIndexes[i] = c;
		}

		return centroidIndexes;
	}

	private boolean compare(int i, int c) {

		for (int j = 0; j < getNumberOfExplanatoryAttributes(); j++) {
			if (!(data.get(c).get(j).equals(data.get(i).get(j))))
				return false;
		}
		return true;
	}

}

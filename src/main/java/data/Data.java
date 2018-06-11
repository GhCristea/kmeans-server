package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.EmptySetException;
import database.Example;
import database.NoValueException;
import database.QUERY_TYPE;
import database.TableData;
import database.TableSchema;

public class Data {

	private List<Example> data;
	private List<Attribute> explanatorySet = new LinkedList<>();

	public Data(String tableName)
			throws DatabaseConnectionException, SQLException, EmptySetException, NoValueException {

		DbAccess database = new DbAccess();
		database.initConnection();

		TableData tableData = new TableData(database);
		data = tableData.getDistinctTransazioni(tableName);

		TableSchema tableSchema = new TableSchema(database, tableName);

		for (int i = 0; i < tableSchema.getNumberOfAttributes(); i++) {
			TableSchema.Column column = tableSchema.getColumn(i);

			if (column.isNumber()) {
				double maximum = (float) tableData.getAggregateColumnValue(tableName, column, QUERY_TYPE.MAX);
				double minimum = (float) tableData.getAggregateColumnValue(tableName, column, QUERY_TYPE.MIN);
				explanatorySet.add(new ContinuousAttribute(column.getColumnName(), i, minimum, maximum));
			} else {
				Set<String> discreteValues = new TreeSet<>();
				Set<Object> distinctDiscreteValues = tableData.getDistinctColumnValues(tableName, column);
				for (Object object : distinctDiscreteValues) {
					discreteValues.add((String) object);
				}
				explanatorySet.add(new DiscreteAttribute(column.getColumnName(), i, (TreeSet<String>) discreteValues));
			}
		}

	}

	public int getNumberOfExamples() {
		return data.size();
	}

	public int getNumberOfExplanatoryAttributes() {
		return explanatorySet.size();
	}

	public Object getAttributeValue(int exampleIndex, int attributeIndex) {
		return data.get(exampleIndex).get(attributeIndex);
	}

	public Attribute getAttribute(int index) {
		return explanatorySet.get(index);
	}

	public List<Attribute> getAttributeSchema() {
		return explanatorySet;
	}

	@Override
	public String toString() {
		String string = "";
		int i = 0;
		for (; i < explanatorySet.size() - 1; ++i) {
			string += explanatorySet.get(i).getName() + ",";
		}
		string += explanatorySet.get(i).getName() + "\n";

		for (i = 0; i < data.size(); i++) {
			string += (i + 1) + ":";
			for (int j = 0; j < explanatorySet.size(); j++) {
				string += data.get(i).get(j).toString() + ",";
			}
			string += "\n";
		}
		return string;
	}

	/*
	 * public static void main(String args[]) { Data trainingSet = new Data();
	 * System.out.println(trainingSet);
	 * 
	 * }
	 */

	public Object computePrototype(Set<Integer> clusteredData, Attribute attribute) {

		if (attribute instanceof ContinuousAttribute)
			return computePrototype(clusteredData, (ContinuousAttribute) attribute);
		return computePrototype(clusteredData, (DiscreteAttribute) attribute);

	}

	Double computePrototype(Set<Integer> idList, ContinuousAttribute attribute) {
		Double count = 0.0;
		for (Integer index : idList) {
			count += (Double) data.get(index).get(attribute.getIndex());
		}
		return count / idList.size();
	}
	
	String computePrototype(Set<Integer> idList, DiscreteAttribute attribute) {
		String max = "";
		int max_freq = 0;
		ArrayList<String> controlled = new ArrayList<String>();
		for (Integer index : idList) {
			String to_control = (String) data.get(index).get(attribute.getIndex());
			boolean checked = false;
			for (String s : controlled) {
				if (s.equals(to_control)) {
					checked = true;
					break;
				}
			}
			if (!checked) {
				controlled.add(to_control);
				int freq = attribute.frequency(this, idList, to_control);
				if (max_freq < freq) {
					max = to_control;
					max_freq = freq;
				}
			}
		}
		return max;
	}


	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(explanatorySet.size());
		for (Attribute attribute : explanatorySet) {
			if (attribute instanceof DiscreteAttribute) {
				tuple.add(new DiscreteItem((DiscreteAttribute) attribute, (String) data.get(index).get(attribute.getIndex())),
						attribute.getIndex());
			} else {
				tuple.add(new ContinuousItem((ContinuousAttribute) attribute, (Double) data.get(index).get(attribute.getIndex())),
						attribute.getIndex());
			}
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

	private boolean compare(int row1, int row2) {

		for (int i = 0; i < getNumberOfExplanatoryAttributes(); i++) {
			if (!(data.get(row1).get(i).equals(data.get(row2).get(i))))
				return false;
		}
		return true;
	}

}

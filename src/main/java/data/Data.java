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

/**
 * Class Data who's storing data from the database.
 * 
 * @author Cristea Gheorghita
 *
 */
public class Data {

	private List<Example> data;
	private List<Attribute> explanatorySet = new LinkedList<>();

	/**
	 * Class constructor.
	 * 
	 * @param tableName
	 *            name of database table.
	 * 
	 * @throws DatabaseConnectionException
	 * @throws SQLException
	 * @throws EmptySetException
	 * @throws NoValueException
	 */
	public Data(final String tableName)
			throws DatabaseConnectionException, SQLException, EmptySetException, NoValueException {

		DbAccess database = new DbAccess();
		database.initConnection();

		TableData tableData = new TableData(database);
		data = tableData.getDistinctTransactions(tableName);

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

	/**
	 * 
	 * @return number of examples.
	 */
	public int getNumberOfExamples() {
		return data.size();
	}

	/**
	 * @return number of collumns of database.
	 */
	public int getNumberOfExplanatoryAttributes() {
		return explanatorySet.size();
	}

	/**
	 * Gets a value from data.
	 * 
	 * @param exampleIndex
	 *            index for the value in data.
	 * @param attributeIndex
	 *            index for the attribute in data.
	 * @return indexed value at exampleIndex for the attribute inexed at
	 *         attributeIndex in data
	 */
	public Object getAttributeValue(final int exampleIndex, final int attributeIndex) {
		return data.get(exampleIndex).get(attributeIndex);
	}

	/**
	 * Gets an attribute.
	 * 
	 * @param index
	 *            index of the attribute.
	 * @return the attribute indexed at index in explanatorySet.
	 */
	public Attribute getAttribute(final int index) {
		return explanatorySet.get(index);
	}

	/**
	 * Getter for database's schema.
	 * 
	 * @return the explanatory set
	 */
	public List<Attribute> getAttributeSchema() {
		return explanatorySet;
	}

	/**
	 * Computes prototype for continuous attribute or discrete attribute.
	 * 
	 * @param clusteredData
	 *            indexes of attributes
	 * @param attribute
	 *            attribute
	 * @return calls {@link data.Data#computePrototype(Set, ContinuousAttribute)} or
	 *         {@link data.Data#computePrototype(Set, DiscreteAttribute)}
	 */
	public Object computePrototype(final Set<Integer> clusteredData, final Attribute attribute) {

		if (attribute instanceof ContinuousAttribute) {
			return computePrototype(clusteredData, (ContinuousAttribute) attribute);
		}
		return computePrototype(clusteredData, (DiscreteAttribute) attribute);

	}

	/**
	 * Compute's a prototype for a continuous attribute.
	 * 
	 * @param idList
	 *            list of attribute indexes
	 * @param attribute
	 *            continuous attribute
	 * @return computed value (continuous
	 */
	Double computePrototype(final Set<Integer> idList, final ContinuousAttribute attribute) {
		Double count = 0.0;
		for (final Integer index : idList) {
			count += (Double) data.get(index).get(attribute.getIndex());
		}
		return count / idList.size();
	}

	/**
	 * Compute prototype for discrete attribute.
	 * 
	 * @param idList
	 *            list with attribute's index.
	 * @param attribute
	 *            discrete attribute
	 * @return computed value
	 */
	String computePrototype(final Set<Integer> idList, final DiscreteAttribute attribute) {
		String maxOccorence = "";
		int freqOccorence = 0;
		ArrayList<String> controlled = new ArrayList<String>();
		for (final Integer index : idList) {
			final String stringToControl = (String) data.get(index).get(attribute.getIndex());
			boolean checked = false;
			for (final String s : controlled) {
				if (s.equals(stringToControl)) {
					checked = true;
					break;
				}
			}
			if (!checked) {
				controlled.add(stringToControl);
				final int currentFrequency = attribute.frequency(this, idList, stringToControl);
				if (freqOccorence < currentFrequency) {
					maxOccorence = stringToControl;
					freqOccorence = currentFrequency;
				}
			}
		}
		return maxOccorence;
	}

	/**
	 * Gets the set of items - pairs of pairs of ({@link data.Attribute} - value)
	 * indexed at index
	 * 
	 * @param index
	 *            index for values
	 * @return the set of items with values indexed at index
	 */
	public Tuple getItemSet(final int index) {
		Tuple tuple = new Tuple(explanatorySet.size());
		for (final Attribute attribute : explanatorySet) {
			if (attribute instanceof DiscreteAttribute) {
				tuple.add(new DiscreteItem((DiscreteAttribute) attribute,
						(String) data.get(index).get(attribute.getIndex())), attribute.getIndex());
			} else {
				tuple.add(new ContinuousItem((ContinuousAttribute) attribute,
						(Double) data.get(index).get(attribute.getIndex())), attribute.getIndex());
			}
		}
		return tuple;
	}

	/**
	 * Generates a set of clusters.
	 * 
	 * @param numberOfClusters
	 *            number of clusters to generate.
	 * @return set of the clusters generated
	 * @throws OutOfRangeSampleSize
	 */
	public int[] sampling(final int numberOfClusters) throws OutOfRangeSampleSize {
		int[] centroidIndexes = new int[numberOfClusters];
		// scegli k centroid differenti in data
		final Random rand = new Random(System.currentTimeMillis());
		// rand.setSeed(System.currentTimeMillis());

		for (int i = 0; i < numberOfClusters; i++) {
			boolean found = false;
			int randomIndex;

			do {
				found = false;
				randomIndex = rand.nextInt(getNumberOfExamples());
				// verifica se il nuovo centroide non esiste gia in centroidIndexes

				for (int j = 0; j < i; j++) {
					if (compare(centroidIndexes[j], randomIndex)) {
						found = true;
						break;
					}
				}

			} while (found);
			centroidIndexes[i] = randomIndex;
		}

		return centroidIndexes;
	}

	/**
	 * Compare two rows of the table.
	 * 
	 * @param row1
	 * @param row2
	 * @return true if the two rows are equal, false otherwise
	 */
	private boolean compare(final int row1, final int row2) {

		for (int i = 0; i < getNumberOfExplanatoryAttributes(); i++) {
			if (!(data.get(row1).get(i).equals(data.get(row2).get(i)))) {
				return false;
			}
		}
		return true;
	}

}

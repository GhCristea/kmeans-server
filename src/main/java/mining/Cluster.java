package mining;

import java.util.HashSet;
import java.util.Set;

import data.Data;
import data.Tuple;

/**
 * 
 * @author docente
 *
 */
public class Cluster {
	private Tuple centroid;

	private Set<Integer> clusteredData;

	/*
	 * Cluster(){
	 * 
	 * }
	 */

	/**
	 * Class constructor.
	 * 
	 * @param inputCentroid
	 *            {@link Tuple}.
	 */
	Cluster(final Tuple inputCentroid) {
		this.centroid = inputCentroid;
		clusteredData = new HashSet<>();
	}

	/**
	 * Getter for attribute centroid.
	 * 
	 * @return centroid.
	 */
	Tuple getCentroid() {
		return centroid;
	}

	/**
	 * Update every Item element of centroid calling
	 * {@link data.Item#update(Data, Set)}.
	 * 
	 * @param data
	 *            data to get prototypes.
	 */
	void computeCentroid(final Data data) {
		for (int i = 0; i < centroid.getLength(); i++) {
			centroid.get(i).update(data, clusteredData);
		}
	}

	/**
	 * Adds a transaction to the curent array.
	 * 
	 * @param identifier
	 *            id.
	 * @return true if the tuple is changing cluster.
	 */
	boolean addData(final int identifier) {
		return clusteredData.add(identifier);

	}

	/**
	 * verfy if a transaction is clustered in the current array.
	 * 
	 * @param identifier
	 *            identifier.
	 * @return true if the transaction is clusetered in the current array.
	 */
	boolean contain(final int identifier) {
		return clusteredData.contains(identifier);
	}

	/**
	 * remove the tuple that has changed the cluster.
	 * @param identifier current id.
	 */
	void removeTuple(final int identifier) {
		clusteredData.remove(identifier);
	}

	@Override
	public final String toString() {
		String string = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++)
			string += centroid.get(i) + " ";
		string += ")";
		return string;
	}

	/**
	 * More explicit toString.
	 * 
	 * @param data
	 *            data from database.
	 * @return centroids + Examples from data
	 */
	public String toString(Data data) {
		String string = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++)
			string += centroid.get(i) + " ";
		string += ")\nExamples:\n";

		for (Integer integer : clusteredData) {
			string += "[";
			for (int j = 0; j < data.getNumberOfExplanatoryAttributes(); j++)
				string += data.getAttributeValue(integer, j) + ", ";
			string += "] dist=" + getCentroid().getDistance(data.getItemSet(integer)) + "\n";
		}
		string += "\nAvgDistance=" + getCentroid().avgDistance(data, clusteredData);
		return string;
	}
}

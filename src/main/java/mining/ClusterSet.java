package mining;

import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

/**
 * Class to manipulate a set of {@link Cluster}.
 * @author Cristea Gheorghita
 *
 */
public class ClusterSet {

	private Cluster[] clusterCollection;
	int numberOfClusters = 0;

	/**
	 * Class contructor.
	 * @param means number of cluster to generate.
	 */
	public ClusterSet(final int means) {
		clusterCollection = new Cluster[means];
	}

	/**
	 * Adds a {@link Cluster} to our set.
	 * @param cluster {@link Cluster} to add.
	 */
	public void addCluster(final Cluster cluster) {
		clusterCollection[numberOfClusters] = cluster;
		numberOfClusters++;
	}

	/**
	 * Getter for an indexed {@link Cluster}.
	 * @param index index.
	 * @return {@link Cluster} indexed at index.
	 */
	public Cluster getCluster(final int index) {
		return clusterCollection[index];
	}

	/**
	 * Initialize centroids using {@link Data#sampling(int)}.
	 * @param data {@link data.Data}
	 * @throws OutOfRangeSampleSize
	 */
	public void initializeCentroids(final Data data) throws OutOfRangeSampleSize {
		int centroidIndexes[] = data.sampling(clusterCollection.length);
		for (int i = 0; i < centroidIndexes.length; i++) {
			final Tuple centroid = data.getItemSet(centroidIndexes[i]);
			addCluster(new Cluster(centroid));
		}
	}

	/**
	 * Gets te nearest {@link Cluster} using distance for measure.
	 * @param tuple {@link Tuple}.
	 * @return nearest {@link Cluster}.
	 */
	Cluster nearestCluster(final Tuple tuple) {
		double min = tuple.getDistance(clusterCollection[0].getCentroid());
		Cluster nearestCluster = clusterCollection[0];
		for (int i = 1; i < clusterCollection.length; i++) {
			double currentMin = tuple.getDistance(clusterCollection[i].getCentroid());
			if (min > currentMin) {
				min = currentMin;
				nearestCluster = clusterCollection[i];
			}
		}
		return nearestCluster;
	}

	/**
	 * Gets the {@link Cluster} indexed at identifier.
	 * @param identifier index.
	 * @return {@link Cluster} indexed at identifier.
	 */
	public Cluster currentCluster(final int identifier) {
		for (Cluster cluster : clusterCollection) {
			if(cluster.contain(identifier))
				return cluster;
		}
		return null;
	}

	/**
	 * Update each centroid calling {@link Cluster#computeCentroid(Data)}.
	 * @param data {@link Data} parameter for {@link Cluster#computeCentroid(Data)}.
	 */
	public void updateCentroids(final Data data) {
		for (int i = 0; i < clusterCollection.length; i++) {
			clusterCollection[i].computeCentroid(data);
		}
	}

	/**
	 * @return the list of {@link Cluster}.
	 */
	@Override
	public final String toString() {
		String string = "";
		for (Integer i = 0; i < clusterCollection.length; i++) {
			string += i + clusterCollection[i].toString() + "\n";
		}
		return string;
	}

	/**
	 * Calls {@link Cluster#toString(Data)} for each {@link Cluster} into set.
	 * @param data {@link data.Data} parameter for {@link Cluster#toString(Data)}.
	 * @return {@link Cluster#toString(Data)} for each {@link Cluster}.
	 */
	public String toString(final Data data) {
		String string = "";
		for (int i = 0; i < clusterCollection.length; i++) {
			if (clusterCollection[i] != null)
				string += i + ":" + clusterCollection[i].toString(data) + "\n";
		}
		return string;
	}
}

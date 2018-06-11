package mining;

import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

public class ClusterSet {

	private Cluster[] clusterCollection;
	int numberOfClusters = 0;

	public ClusterSet(int k) {
		clusterCollection = new Cluster[k];
	}

	public void addCluster(Cluster cluster) {
		clusterCollection[numberOfClusters] = cluster;
		numberOfClusters++;
	}

	public Cluster getCluster(int index) {
		return clusterCollection[index];
	}

	public void initializeCentroids(Data data) throws OutOfRangeSampleSize {
		int centroidIndexes[] = data.sampling(clusterCollection.length);
		for (int i = 0; i < centroidIndexes.length; i++) {
			Tuple centroidI = data.getItemSet(centroidIndexes[i]);
			addCluster(new Cluster(centroidI));
		}
	}

	Cluster nearestCluster(Tuple tuple) {
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

	public Cluster currentCluster(int id) {
		for (Cluster cluster : clusterCollection) {
			if(cluster.contain(id))
				return cluster;
		}
		return null;
	}

	public void updateCentroids(Data data) {
		for (int i = 0; i < clusterCollection.length; i++) {
			clusterCollection[i].computeCentroid(data);
		}
	}

	@Override
	public String toString() {
		String string = "";
		for (Integer i = 0; i < clusterCollection.length; i++) {
			string += i + clusterCollection[i].toString() + "\n";
		}
		return string;
	}

	public String toString(Data data) {
		String string = "";
		for (int i = 0; i < clusterCollection.length; i++) {
			if (clusterCollection[i] != null)
				string += i + ":" + clusterCollection[i].toString(data) + "\n";
		}
		return string;
	}
}

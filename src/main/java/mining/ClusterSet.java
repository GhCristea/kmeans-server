package mining;

import data.Data;
import data.OutOfRangeSampleSize;
import data.Tuple;

public class ClusterSet {

	private Cluster[] arrayOfClusters;
	int numberOfClusters = 0;

	public ClusterSet(int k) {
		arrayOfClusters = new Cluster[k];
	}

	public void addCluster(Cluster c) {
		this.arrayOfClusters[numberOfClusters] = c;
		numberOfClusters++;
	}

	public Cluster getCluster(int index) {
		return arrayOfClusters[index];
	}

	public void initializeCentroids(Data data) throws OutOfRangeSampleSize {
		int centroidIndexes[] = data.sampling(arrayOfClusters.length);
		for (int i = 0; i < centroidIndexes.length; i++) {
			Tuple centroidI = data.getItemSet(centroidIndexes[i]);
			addCluster(new Cluster(centroidI));
		}
	}

	Cluster nearestCluster(Tuple tuple) {
		double min = tuple.getDistance(arrayOfClusters[0].getCentroid());
		Cluster nearestCluster = arrayOfClusters[0];
		for (int i = 1; i < arrayOfClusters.length; i++) {
			double currentMin = tuple.getDistance(arrayOfClusters[i].getCentroid());
			if (min > currentMin) {
				min = currentMin;
				nearestCluster = arrayOfClusters[i];
			}
		}
		return nearestCluster;
	}

	public Cluster currentCluster(int id) {
		for (int i = 0; i < arrayOfClusters.length; i++) {
			if (arrayOfClusters[i].contain(id)) {
				return arrayOfClusters[i];
			}
		}
		return null;
	}

	public void updateCentroids(Data data) {
		for (int i = 0; i < arrayOfClusters.length; i++) {
			arrayOfClusters[i].computeCentroid(data);
		}
	}

	@Override
	public String toString() {
		String out = "";
		for (Integer i = 0; i < arrayOfClusters.length; i++) {
			out += i + arrayOfClusters[i].toString() + "\n";
		}
		return out;
	}

	public String toString(Data data) {
		String out = "";
		for (int i = 0; i < arrayOfClusters.length; i++) {
			if (arrayOfClusters[i] != null)
				out += i + ":" + arrayOfClusters[i].toString(data) + "\n";
		}
		return out;
	}
}

package mining;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.Data;
import data.OutOfRangeSampleSize;

public class KmeansMiner {

	private ClusterSet clusterSet;

	public KmeansMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
		clusterSet = (ClusterSet) inputStream.readObject();
		inputStream.close();
	}

	public KmeansMiner(int k) {
		clusterSet = new ClusterSet(k);
	}

	public ClusterSet getClusterSet() {
		return clusterSet;
	}

	public int kmeans(Data data) throws OutOfRangeSampleSize {
		int numberOfIterations = 0;
		clusterSet.initializeCentroids(data);
		boolean changedCluster = false;

		do {
			numberOfIterations++;
			changedCluster = false;

			for (int i = 0; i < data.getNumberOfExamples(); i++) {
				Cluster nearestCluster = clusterSet.nearestCluster(data.getItemSet(i));
				Cluster oldCluster = clusterSet.currentCluster(i);
				boolean currentChange = nearestCluster.addData(i);

				if (currentChange)
					changedCluster = true;

				if (currentChange && oldCluster != null)
					oldCluster.removeTuple(i);
			}

			clusterSet.updateCentroids(data);

		} while (changedCluster);

		return numberOfIterations;
	}

	public void salva(String fileName) throws FileNotFoundException, IOException {
		FileOutputStream inputFile = new FileOutputStream(fileName);
		ObjectOutputStream outObject = new ObjectOutputStream(inputFile);
		outObject.writeObject(clusterSet);
		outObject.close();
	}
}

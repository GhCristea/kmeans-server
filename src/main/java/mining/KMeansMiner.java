package mining;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;

import data.Data;
import data.OutOfRangeSampleSize;
import database.DatabaseConnectionException;
import database.EmptySetException;
import database.NoValueException;

public class KMeansMiner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5834754062973042220L;
	private ClusterSet clusterSet;

	/**
	 * Class constructor.
	 * @param fileName name of input file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public KMeansMiner(final String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
		clusterSet = (ClusterSet) inputStream.readObject();
		inputStream.close();
	}

	/**
	 * Class constructor.
	 * @param means number of clusters.
	 */
	public KMeansMiner(final int means){
		clusterSet = new ClusterSet(means);
	}

	/**
	 * Getter for {@link ClusterSet}.
	 * @return the {@link ClusterSet}.
	 */
	public ClusterSet getClusterSet() {
		return clusterSet;
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws OutOfRangeSampleSize
	 */
	public int kmeans(final Data data) throws OutOfRangeSampleSize {
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

	/**
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salva(String fileName) throws FileNotFoundException, IOException {
		FileOutputStream inputFile = new FileOutputStream(fileName);
		ObjectOutputStream outObject = new ObjectOutputStream(inputFile);
		outObject.writeObject(clusterSet);
		outObject.close();
	}
	/*
	public static void main(String[] args) {
		KMeansMiner kMeansMiner = new KMeansMiner(3);
		try {
			Data data = new Data("playtennis");
			int num = kMeansMiner.kmeans(data);
			System.out.println(num + "\n" + kMeansMiner.getClusterSet().toString(data));
		} catch (DatabaseConnectionException | SQLException | EmptySetException | NoValueException | OutOfRangeSampleSize e) {
			e.printStackTrace();
		}
		
		
	}*/
}

package mining;

import data.Data;
import data.OutOfRangeSampleSize;

public class KmeansMiner {

	private ClusterSet c;
	
	public KmeansMiner(int k) {
		c = new ClusterSet(k);
	}
	
	public ClusterSet getC() {
		return c;
	}
	
	public int kmeans(Data data) throws OutOfRangeSampleSize {
		int numberOfIterations=0;
		c.initializeCentroids(data);
		boolean changedCluster=false;
		
		do {
			numberOfIterations++;
			changedCluster=false;
			
			for (int i = 0; i < data.getNumberOfExamples(); i++) {
				Cluster nearestCluste=c.nearestCluster(data.getItemSet(i));
				Cluster oldCluster=c.currentCluster(i);
				boolean currentChange=nearestCluste.addData(i);
				
				if(currentChange)
					changedCluster=true;
				
				if(currentChange && oldCluster!=null)
					oldCluster.removeTuple(i);
			}
			
			c.updateCentroids(data);
			
		} while (changedCluster);
		
		return numberOfIterations;
	}
}

package map.data;

public class ClusterSet {

	private Cluster[] c;
	int i = 0;

	public ClusterSet(int k) {
		c = new Cluster[k];
	}

	public void add(Cluster c) {
		this.c[i] = c;
		i++;
	}

	public Cluster get(int index) {
		return c[index];
	}

	public void initializeCentroids(Data data) {
		int centroidIndexes[] = data.sampling(c.length);
		for (int i = 0; i < centroidIndexes.length; i++) {
			Tuple centroidI = data.getItemSet(centroidIndexes[i]);
			add(new Cluster(centroidI));
		}
	}

	Cluster nearestCluster(Tuple tuple) {
		double min = tuple.getDistance(c[0].getCentroid());
		Cluster outC = c[0];
		for (int i = 1; i < c.length; i++) {
			if (min < tuple.getDistance(c[i].getCentroid())) {
				min = tuple.getDistance(c[i].getCentroid());
				outC = c[i];
			}
		}
		return outC;
	}

	public Cluster currentCluster(int id) {
		for (int i = 0; i < c.length; i++) {
			if (c[i].contain(id)) {
				return c[i];
			}
		}
		return null;
	}

	public void updateCentroids(Data data) {
		for (int i = 0; i < c.length; i++) {
			c[i].computeCentroid(data);
		}
	}
	
	@Override
	public String toString() {
		String out = "";
		for (Integer i = 0; i < c.length; i++) {
			out +=  i + c[i].toString() + "\n";
		}
		return out;
	}
	
	public String toString(Data data) {
		String out = "";
		for (int i = 0; i < c.length; i++) {
			if(c[i] != null)
				out+=i+":"+c[i].toString(data)+"\n";
		}
		return out;
	}

}

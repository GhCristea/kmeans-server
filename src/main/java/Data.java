import java.util.Random;

public class Data {

	private Object data [][];
	private int numberOfExamples;
	private Attribute explanatorySet[];
	
	
	Data(){
		
		//data
		
		data = new Object [][] 
				{{"Sunny","Hot", "High", "Weak", "No" },
				{"Sunny","Hot", "High", "Strong", "No"},
				{"Overcast","Hot", "High", "Weak", "Yes"},
				{"Rain", "Mild", "High", "Weak", "Yes"},
				{"Rain", "Cool", "Normal", "Weak", "Yes"},
				{"Rain", "Cool", "Normal", "Strong", "No"},
				{"Overcast", "Cool", "Normal", "Strong", "Yes"},
				{"Sunny", "Mild", "High", "Weak", "No"},
				{"Sunny", "Cool", "Normal", "Weak", "Yes"},
				{"Rain", "Mild", "Normal", "Weak", "Yes"},
				{"Sunny", "Mild", "Normal", "Strong", "Yes"},
				{"Overcast", "Mild", "High", "Strong", "Yes"},
				{"Overcast","Hot", "Normal", "Weak", "Yes"},
				{"Rain", "Mild", "High", "Strong", "No"}};

		
		// numberOfExamples
		
		 numberOfExamples=14;		 
		 
		
		//explanatory Set
		
		explanatorySet = new Attribute[5];

		// TO DO : avvalorare ciascune elemento di attributeSet con un oggetto della classe DiscreteAttribute che modella il corrispondente attributo (e.g. outlook, temperature,etc)
		// nel seguito si fornisce l'esempio per outlook
		
		String outLookValues[]=new String[3];
		outLookValues[0]="Overcast";
		outLookValues[1]="Rain";
		outLookValues[2]="Sunny";
		explanatorySet[0] = new DiscreteAttribute("Outlook",0, outLookValues);
		
		String temperatureValues[] = new String[3];
		temperatureValues[0] = "Mild";
		temperatureValues[0] = "Cool";
		temperatureValues[0] = "Hot";
		explanatorySet[1] = new DiscreteAttribute("Temperature", 1, temperatureValues);
		
		
		String humidityValues[] = new String[2];
		humidityValues[0] = "High";
		humidityValues[1] = "Normal";
		explanatorySet[2] = new DiscreteAttribute("Humidity", 1, humidityValues);
		
		String windValues[] = new String[2];
		windValues[0] = "Weak";
		windValues[1] = "Strong";
		explanatorySet[3] = new DiscreteAttribute("Wind", 1, windValues);
		
		String playValues[] = new String[2];
		playValues[0] = "Yes";
		playValues[1] = "No";
		explanatorySet[4] = new DiscreteAttribute("PlayTennis", 1, playValues);
	}
	
	public int getNumberOfExamples(){
		return numberOfExamples;
	}
	
	public int getNumberOfExplanatoryAttributes(){
		return explanatorySet.length;
	}
	
	
	
	public Object getAttributeValue(int exampleIndex, int attributeIndex){
		
		return data[exampleIndex][attributeIndex];
	}
	
	public Attribute getAttribute(int index){
		return explanatorySet[index];
	}
	
	public Attribute[] getAttributeSchema() {
		return explanatorySet;
	}
	
	@Override
	public String toString(){ 
		String out = "";
		int i = 0;
		for (; i < explanatorySet.length-1; i++) {
			out+=explanatorySet[i].getName() + ",";
		}
		out+=explanatorySet[i].getName() + "\n";
		
		for (i = 0; i < numberOfExamples; i++) {
			out += (i+1) + ":";
			for (int j = 0; j < explanatorySet.length; j++) {
				 out+= data[i][j] + ",";
			}
			out+="\n";
		}
		return out;
	}


	
	public static void main(String args[]){
		Data trainingSet=new Data();
		System.out.println(trainingSet);
		
		
	}

	public Object computePrototype(ArraySet clusteredData, Attribute attribute) {
		
		String out = computePrototype(clusteredData, (DiscreteAttribute)attribute);
		return out;
	}
	
	
	private String computePrototype(ArraySet idList, DiscreteAttribute attribute) {
		int max = 0;
		String out = "";
		for (int j = 0; j < explanatorySet.length; j++) {
			if(max < attribute.frequency(this, idList, attribute.getName())) {
				max = attribute.frequency(this, idList, attribute.getName());
				out = attribute.getName();
			}
		}
		return out;
	}

	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(explanatorySet.length);
		for (int i = 0; i < explanatorySet.length; i++) {
			tuple.add(new DiscreteItem((DiscreteAttribute) explanatorySet[i], (String)data[index][i]), i);
		}
		return tuple;
	}
	
	int [] sampling(int k) {
		int [] centroidIndexes = new int[k];
		//scegli k centroid differenti in data
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		for (int i = 0; i < k; i++) {
			boolean found = false;
			int c;
			
			do {
				found = false;
				c = rand.nextInt(getNumberOfExamples());
				//verifica se il nuovo centroide non esiste gia in centroidIndexes
				
				for (int j = 0; j < i; j++) {
					if(compare(centroidIndexes[j],c)) {
						found = true;
						break;
					}
				}
				
			} while (found);
			centroidIndexes[i] = c;
		}
		
		return centroidIndexes;
	}

	private boolean compare(int i, int c) {
	
		for (int j = 0; j < getNumberOfExplanatoryAttributes(); j++) {
			if( !(data[c][j] == data[i][j]) )
					return false;
		}
		return true;
	}
	
	

}

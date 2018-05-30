package map.main;

import data.Data;
import data.OutOfRangeSampleSize;
import keyboardinput.Keyboard;
import mining.KmeansMiner;
public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Data data = new Data();
		System.out.println(data);
		int k;
		boolean end = false;
		while(end == false) {
			System.out.println("\ninserire numero di cluster da generare: ");
			k = Keyboard.readInt();
			KmeansMiner kmeans = new KmeansMiner(k);
			int numIter = 0;
			try {
				numIter = kmeans.kmeans(data);
			} catch (OutOfRangeSampleSize e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Numero di Iterazione:"+numIter);
			System.out.println(kmeans.getC().toString(data));
			char c;
			do {
				System.out.println("\nVuoi ripetere l'esecuzione?(y/n) ");
				c = Keyboard.readChar();
				if(c == 'n')
					end = true;
			}while(c != 'n' && c != 'y');
		}
		
	}

}

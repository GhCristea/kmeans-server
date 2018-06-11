package map.main;

import java.sql.SQLException;

import data.*;
import database.DatabaseConnectionException;
import database.EmptySetException;
import database.NoValueException;
import mining.*;
import keyboardinput.*;

public class MainTest {

	/**
	 * @param args
	 * @throws EmptySetException 
	 * @throws SQLException 
	 * @throws DatabaseConnectionException 
	 */
	public static void main(String[] args) throws DatabaseConnectionException, SQLException, EmptySetException {

		Data data = null;
		
			try {
				data = new Data("playtennis");
			} catch (NoValueException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(data);
			int k;
			boolean end = false;
			while (end == false) {
				System.out.println("\ninserire numero di cluster da generare: ");
				k = Keyboard.readInt();
				try {
					KmeansMiner kmeans = new KmeansMiner(k);
					int numIter = kmeans.kmeans(data);
					System.out.println("Numero di Iterazione:" + numIter);
					System.out.println(kmeans.getClusterSet().toString(data));
				} catch (OutOfRangeSampleSize e) {
					e.print();
				}
				char c;
				do {
					System.out.println("\nVuoi ripetere l'esecuzione?(y/n) ");
					c = Keyboard.readChar();
					if (c == 'n')
						end = true;
				} while (c != 'n' && c != 'y');
			}
		

	}

}

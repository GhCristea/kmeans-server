/**
 * 
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import data.Data;
import data.OutOfRangeSampleSize;
import database.DatabaseConnectionException;
import database.EmptySetException;
import database.NoValueException;
import mining.KMeansMiner;

/**
 * @author Cristea Gheorghita
 *
 */
public class ServerOneClient extends Thread {
	Socket socket;
	ObjectInputStream input;
	ObjectOutputStream output;
	KMeansMiner kmeans;

	public ServerOneClient(final Socket sock) {

		try {
			socket = sock;
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	@Override
	public void run() {

		Data data = null;

		while (true) {
			int execute = 0;
			try {
				execute = Integer.parseInt(input.readObject().toString());
				switch (execute) {
				case 0:

					try {
						String tableName = (String) input.readObject();
						data = new Data(tableName);
						output.writeObject("OK");
					} catch (DatabaseConnectionException | EmptySetException | NoValueException | SQLException
							| ClassNotFoundException e) {
						e.printStackTrace();
						try {
							output.writeObject(e.getMessage());
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}
					break;
				case 1:

					try {
						int k = Integer.parseInt(input.readObject().toString());
						kmeans = new KMeansMiner(k);
						int numberOfIter = 0;

						numberOfIter = kmeans.kmeans(data);
						output.writeObject("OK");
						output.writeObject("Number of iterations: " + numberOfIter);
						output.writeObject(kmeans.getClusterSet().toString(data));
						System.out.println("kmeans done");
					} catch (OutOfRangeSampleSize | ClassNotFoundException | IOException e) {
						e.printStackTrace();
						try {
							output.writeObject(e.getMessage());
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}

					break;

				case 2:
					try {
						kmeans.salva((String) input.readObject());
						output.writeObject("OK");
						System.out.println("salva done");
					} catch (IOException | ClassNotFoundException e) {
						try {
							output.writeObject(e.getMessage());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					break;
				case 3:
					try {
						String fileName = (String) input.readObject();
						try {
							data = new Data(fileName);
							output.writeObject("OK");
						} catch (DatabaseConnectionException | EmptySetException | NoValueException e) {
							output.writeObject(e.getMessage());
						}
						int k = Integer.parseInt(input.readObject().toString());
						kmeans = new KMeansMiner(fileName + k + ".dat");
						output.writeObject("OK");

						output.writeObject(kmeans.getClusterSet().toString(data));
					} catch (ClassCastException | ClassNotFoundException | IOException | SQLException e) {

						try {
							output.writeObject(e.getMessage());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					break;

				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				try {
					input.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				interrupt();
				return;
			}
		}

	}

}

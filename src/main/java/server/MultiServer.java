/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  server.ServerOneClient
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    private int PORT;

    public MultiServer(int port) {
        this.PORT = port;
        this.run();
    }

    void run() {
        try {
            @SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(this.PORT);
            do {
                Socket actualClient = serverSocket.accept();
                System.out.println(actualClient);
                try {
                    new server.ServerOneClient(actualClient);
                }
                catch (Exception e) {
                    actualClient.close();
                    e.printStackTrace();
                }
            } while (true);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void main(String[] args) {
        new MultiServer(8080);
    }
}

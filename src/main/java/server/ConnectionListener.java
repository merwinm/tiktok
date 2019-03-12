package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionListener implements Runnable{
    private Server server;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Thread thread;


    ConnectionListener(ServerSocket serverSocket,Server server){
        this.serverSocket = serverSocket;
        this.server = server;
    }

    @Override
    public void run() {
        while (true){
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ClientHandler handler = new ClientHandler(clientSocket,server);
            thread = new Thread(handler);
            thread.start();
            Server.clientList.add(handler);
        }
    }
}

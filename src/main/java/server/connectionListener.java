package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class connectionListener implements Runnable{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Thread thread;


    connectionListener(ServerSocket serverSocket){
    this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true){
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clientHandler handler = new clientHandler(clientSocket);
            thread = new Thread(handler);
            thread.start();
            Server.clientList.add(handler);
        }
    }
}

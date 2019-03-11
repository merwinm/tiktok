package server;

import server.ConnectionListener;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {

    private int port;
    InetAddress IP;
    private ServerSocket serverSocket;
    static ArrayList<ClientHandler> clientList = new ArrayList<ClientHandler>();

   public Server(int port){
        this.port = port;
        try {
            IP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void runServer(){
        new ConnectionListener(serverSocket).run(); // This thread listens to clients who want to connect to the server
        while(true){

        }
    }

    public static ArrayList<ClientHandler> getclientList(){
       return clientList;
    }
}

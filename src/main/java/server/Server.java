package server;

import server.connectionListener;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {

    private int port;
    InetAddress IP;
    private ServerSocket serverSocket;
    static ArrayList<clientHandler> clientList = new ArrayList<clientHandler>();

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
        new connectionListener(serverSocket).run();
        while(true){

        }
    }

    public static ArrayList<clientHandler> getclientList(){
       return clientList;
    }
}

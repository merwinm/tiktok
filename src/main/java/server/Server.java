package server;

import javafx.fxml.FXMLLoader;
import server.GUI.ServerController;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {

    private int port;
    private InetAddress IP;
    private ServerSocket serverSocket;
    public ServerController serverController;
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
        new ConnectionListener(serverSocket,this).run(); // This thread listens to clients who want to connect to the server
        while(true){

        }
    }

    public static ArrayList<ClientHandler> getclientList(){
       return clientList;
    }
}

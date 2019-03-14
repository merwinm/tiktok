package server;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import server.GUI.ServerController;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server implements Runnable {

    private int port;
    private InetAddress IP;
    private ServerSocket serverSocket;
    public static ServerController serverController;
    static ArrayList<ClientHandler> clientList = new ArrayList<ClientHandler>();



   public Server(int port,ServerController serverController){

        this.port = port;
        this.serverController = serverController;
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

    @Override
    public void run() {
        new ConnectionListener(serverSocket,this).run(); // This thread listens to clients who want to connect to the server
        while(true){
        }
    }

    public static ArrayList<ClientHandler> getclientList(){
        return clientList;
    }

    public static ServerController getServerController(){return serverController;}

    public void setPort(int port){
       this.port = port;
    }


}

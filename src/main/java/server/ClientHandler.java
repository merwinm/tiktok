package server;

import packets.chat.PacketchatMessage;
import server.GUI.GameLogic;
import server.GUI.ServerController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import static server.Server.getServerController;
import static server.Server.getclientList;

/*
* this class processes incoming clientpackets, currently only chatmessages, and gets send to other clients
* More functions are coming*/

public class ClientHandler implements Runnable {
    private Server server;
    private ObjectInputStream fromClient;
    private ObjectOutputStream outToClient;
    private Packet received;
    private String username;
    public static ArrayList<ClientHandler>clientList;
    public static ServerController serverController;
    public static GameLogic game;
    public static final Logger logger = Logger.getLogger("ClientHandler Logger: ");

    ClientHandler(Socket clientsocket, Server server){
        this.server = server;
        this.game = new GameLogic();
        clientList = getclientList();
        serverController = getServerController();
        try {
            this.outToClient = new ObjectOutputStream(clientsocket.getOutputStream());
            this.fromClient = new ObjectInputStream(clientsocket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//TODO Error Handling
    public void run() {
        logger.info("Listening to Packets");
        while (true) {
            try {
                received = (Packet) fromClient.readObject();
                if ((received!=null)) {

                    received.parseAtServer(this);

                    if(received instanceof PacketchatMessage){
                        broadcast(received);
                    }
                }
                else{
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Server getServer(){
        return this.server;
    }

    public void sendPacket(Packet packet){
        try {
            outToClient.writeObject(packet);
            outToClient.flush();
            logger.info("Sending Packet back to Clients");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameLogic getGame(){
        return game;
    }

    public void broadcast(Packet packet){
        for (ClientHandler handler : clientList) {
            handler.sendPacket(packet);
        }
        logger.info("Broadcasting packets to all clients");
    }

    public void setUsername(String name){
        this.username = name;
    }

    public String getUsername(){
        return this.username;
    }

    public ServerController sv(){
        return serverController;
    }

}

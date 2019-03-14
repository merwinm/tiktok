package server;

import packets.chat.PacketchatMessage;
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
    static ArrayList<ClientHandler>clientList;
    public static ServerController serverController;
    private Logger logger;

    ClientHandler(Socket clientsocket, Server server){
        this.server = server;
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Packet packet){
        for (ClientHandler handler : clientList) {
            handler.sendPacket(packet);
        }
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

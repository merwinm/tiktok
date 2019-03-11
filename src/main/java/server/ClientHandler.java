package server;

import packets.chat.PacketchatMessage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static server.Server.getclientList;

/*
* this class processes incoming clientpackets, currently only chatmessages, and gets send to other clients
* More functions are coming*/

public class ClientHandler implements Runnable {
    private ObjectInputStream fromClient;
    private ObjectOutputStream outToClient;
    static ArrayList<ClientHandler>clientList;
    private Packet received;

    ClientHandler(Socket clientsocket){
        clientList = getclientList();
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

                    received.parse();

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

    public void broadcast(Packet packet){
        try {
            for (ClientHandler handler : clientList) {
                outToClient.writeObject(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

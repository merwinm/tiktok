package server;

import packets.PacketchatMessage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static server.Server.getclientList;

/*
* this class processes incoming clientpackets, currently only chatmessages, and gets send to other clients
* More functions are coming*/

public class ClientHandler implements Runnable {
    private Socket clientsocket;
    private ObjectInputStream fromClient;
    private ObjectOutputStream outToClient;
    private PacketchatMessage data;
    static ArrayList<ClientHandler>clientList;
    private Packet received;

    ClientHandler(Socket clientsocket){
        clientList = getclientList();
        this.clientsocket = clientsocket;
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
                received = (PacketchatMessage) fromClient.readObject();
                if ((received!=null)) {
                    System.out.println(((PacketchatMessage) received).parse());
                    //for(int i = 0; 0<clientList.size();i++ ){
                    //   clientList.get(i).
                    //}
                    for (ClientHandler handler : clientList) {
                        handler.sendMsgToClients(((PacketchatMessage) received).parse());
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

    public void sendMsgToClients(String message){
        try {
            outToClient.writeObject(new PacketchatMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

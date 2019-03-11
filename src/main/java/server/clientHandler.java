package server;

import packets.chatMessage;
import packets.packet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static server.Server.getclientList;

/*
* this class processes incoming clientpackets, currently only chatmessages, and gets send to other clients
* More functions are coming*/

public class clientHandler implements Runnable {
    private Socket clientsocket;
    private ObjectInputStream fromClient;
    private ObjectOutputStream outToClient;
    private chatMessage data;
    static ArrayList<clientHandler>clientList;
    private packet received;

    clientHandler(Socket clientsocket){
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
                received = (chatMessage) fromClient.readObject();
                if ((received!=null)) {
                    System.out.println(((chatMessage) received).parse());
                    //for(int i = 0; 0<clientList.size();i++ ){
                    //   clientList.get(i).
                    //}
                    for (clientHandler handler : clientList) {
                        handler.sendMsgToClients(((chatMessage) received).parse());
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
            outToClient.writeObject(new chatMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

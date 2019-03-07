package server;

import packets.chatMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static server.Server.getclientList;

public class clientHandler implements Runnable {
    private Socket clientsocket;
    private ObjectInputStream fromClient;
    private ObjectOutputStream outToClient;
    private chatMessage data;
    static ArrayList<clientHandler>clientList;

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

    public void run() {
        while (true) {
            try {
                if ((fromClient.available() != 0)) {
                    data = (chatMessage) fromClient.readObject();
                    System.out.println(data.getMessage());
                    //for(int i = 0; 0<clientList.size();i++ ){
                    //   clientList.get(i).
                    //}
                    for (clientHandler handler : clientList) {
                        handler.sendMsgToClients(data.getMessage());
                    }

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

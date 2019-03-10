package client;

import packets.chatMessage;
import server.clientHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/*
* This Class handles all Server packets
* Notice the Socket comes from the client class therefore there is a Dataoutput/input channel already open
* */
public class serverHandler implements Runnable {
    private Socket clientsocket;
    private ObjectInputStream dis;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private chatMessage data;

    serverHandler(Socket socket){
        this.clientsocket = socket;
        try {
            this.dis = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
while(true){

    try {
        data = (chatMessage) dis.readObject();

        if(data !=null){
            System.out.println(data.getMessage());
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

}
    }
}

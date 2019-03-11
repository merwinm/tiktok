package client;

import packets.PacketchatMessage;
import server.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/*
* This Class handles all Server packets
* Notice the Socket comes from the client class therefore there is a Dataoutput/input channel already open
* */
public class ServerHandler implements Runnable {
    private Socket clientsocket;
    private ObjectInputStream dis;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private PacketchatMessage data;

    ServerHandler(Socket socket){
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
        data = (PacketchatMessage) dis.readObject();

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

package client;

import client.GUI.ClientController;
import packets.chat.PacketchatMessage;
import packets.chat.PacketsetUsername;
import server.Packet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/*
* This class sends currently messages to the server
* A separate serverhandler thread is run to receive incoming server messages*/

public class Client implements Runnable{
    private String host;
    private int port;
    private Socket socket;
    //private DataOutputStream outToServer;
    private ObjectOutputStream outToServer;
    private Scanner scanner;
    private ServerHandler handler;
    private String username;
    static private ClientController clientController;

    public Client(String host, int port, ClientController cl) {
        this.host = host;
        this.port = port;
        this.scanner = new Scanner(System.in);
        clientController = cl;

        try {
            this.socket = new Socket(host, port);
            this.outToServer = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.handler = new ServerHandler(socket,this);
        this.handler.setClientController(clientController);
        Thread thread = new Thread(handler);
        thread.start();

    }


    public static ClientController getClientcontroller(){
        return clientController;
    }


    public void sendPacket(Packet packet){
        try {
            outToServer.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void run(){


        while(true){

            sendPacket(new PacketchatMessage(scanner.nextLine()));
        }
    }

}
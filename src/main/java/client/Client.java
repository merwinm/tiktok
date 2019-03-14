package client;

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

public class Client {
    private String host;
    private int port;
    private Socket socket;
    //private DataOutputStream outToServer;
    private ObjectOutputStream outToServer;
    private Scanner scanner;
    private ServerHandler handler;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.scanner = new Scanner(System.in);

        try {
            this.socket = new Socket(host, port);
            this.outToServer = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.handler = new ServerHandler(socket);
        Thread thread = new Thread(handler);
        thread.start();

    }


    public void sendPacket(Packet packet){
        try {
            outToServer.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

            System.out.println("Enter a Username");
            String username = scanner.nextLine();
            sendPacket(new PacketsetUsername(username));
            sendPacket(new PacketchatMessage(username + " joined"));

        while(true){

            sendPacket(new PacketchatMessage(scanner.nextLine()));
        }
    }

}
package client;

import packets.chatMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
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
    private serverHandler handler;

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

        this.handler = new serverHandler(socket);
        Thread thread = new Thread(handler);
        thread.start();

    }

    public void send(String message){
        try {
            //outToServer.writeUTF(message);
            outToServer.writeObject(new chatMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            send(scanner.nextLine());
        }
    }

}
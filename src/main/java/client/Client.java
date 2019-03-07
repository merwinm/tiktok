package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String host;
    private int port;
    private Socket socket;
    private DataOutputStream outToServer;
    private Scanner scanner;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.scanner = new Scanner(System.in);
        try {
            this.socket = new Socket(host, port);
            this.outToServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message){
        try {
            outToServer.writeUTF(message);
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
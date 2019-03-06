package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private String host;
    private int port;
    private Socket socket;
    private DataOutputStream outToServer;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
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
}
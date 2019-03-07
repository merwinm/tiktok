package client;

import server.clientHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class serverHandler implements Runnable {
    private Socket clientsocket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String data;

    serverHandler(Socket socket){
        this.clientsocket = socket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
while(true){
    try {
        if ((dis.available()!=0)){
            try {
                data = dis.readUTF();
                System.out.println(data);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }
}

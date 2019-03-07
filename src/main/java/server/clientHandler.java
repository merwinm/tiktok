package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static server.Server.getclientList;

public class clientHandler implements Runnable {
    private Socket clientsocket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String data;
    static ArrayList<clientHandler>clientList;

    clientHandler(Socket clientsocket){
        clientList = getclientList();
        this.clientsocket = clientsocket;
        try {
            this.dis = new DataInputStream(clientsocket.getInputStream());
            this.dos = new DataOutputStream(clientsocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        while (true){
            try {
                if ((dis.available()!=0)){
                    try {
                        data = dis.readUTF();
                        System.out.println(data);

                        for(clientHandler handler: clientList){
                            handler.sendMsgToClients(data);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMsgToClients(String message){
        try {
            dos.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

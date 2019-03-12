package client;

import packets.chat.PacketchatMessage;
import server.Packet;

import java.io.*;
import java.net.Socket;

/*
* This Class handles all Server packets
* The Server doesnt do much. Packets contain executable code which is hence executed in the server
* FYI:Notice the Socket comes from the client class therefore there is a Dataoutput/input channel already open
* */
public class ServerHandler implements Runnable,Serializable {

    private ObjectInputStream dis;
    private Packet data;

    ServerHandler(Socket socket){
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
        data = (Packet) dis.readObject();

        if(data !=null){
           data.parseAtClient(this);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

}
    }
}

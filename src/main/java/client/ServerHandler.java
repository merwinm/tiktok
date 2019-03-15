package client;

import client.GUI.ClientController;
import server.Packet;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/*
* This Class handles all Server packets
* The Server doesnt do much. Packets contain executable code which is hence executed in the server
* FYI:Notice the Socket comes from the client class therefore there is a Dataoutput/input channel already open
* */
public class ServerHandler implements Runnable,Serializable {
    private Client client;
    private ObjectInputStream dis;
    private Packet data;
    static private ClientController clientController;
    public static final Logger logger = Logger.getLogger("ServerHandler Logger: ");

    ServerHandler(Socket socket,Client client){
        this.client = client;
        try {
            this.dis = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client getClient(){
        return this.client;
    }

    public void setClientController(ClientController cl){
        clientController = cl;
    }

    public ClientController cl(){
        return clientController;
    }

    @Override
    public void run() {
while(true){

    try {
        data = (Packet) dis.readObject();

        if(data !=null){
           data.parseAtClient(this);
           logger.info("Successfully received packet");
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

}
    }
}

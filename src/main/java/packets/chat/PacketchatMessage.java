package packets.chat;

import client.ServerHandler;
import server.ClientHandler;
import server.Packet;

/* Chat Message class is a packet which holds the chat message, it implemets the interface Dataparse to convert the packet into a chatmessage object.
 *
* */
public class PacketchatMessage extends Packet {
    private String message;

    public PacketchatMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    @Override
    public Object parseAtServer(ClientHandler handler) {
        //setClientHandler(handler);

        System.out.println(this.message);
        return null;
    }

    @Override
    public Object parseAtClient(ServerHandler handler) {
        //setServerHandler(handler);
        System.out.println(this.message);
        return null;
    }
}


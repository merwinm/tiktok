package packets.chat;

import client.ServerHandler;
import server.ClientHandler;
import server.Packet;

/* Chat Message class is a packet which holds the chat message, it implemets the interface Dataparse to convert the packet into a chatmessage object.
 *
* */
public class PacketchatMessage extends Packet {
    private String message;
    private String username;

    public PacketchatMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    @Override
    public Object parseAtServer(ClientHandler handler) {   // This method gets executed when the packet reaches the server
        username = handler.getUsername();
       // handler.getServer().serverController.setText(username + ": " + this.message);
        System.out.println(username + ": " + this.message);
        return null;
    }

    @Override
    public Object parseAtClient(ServerHandler handler) {  // This method gets executed when the packet reaches the client
        System.out.println(username +": "+ this.message);
        return null;
    }
}


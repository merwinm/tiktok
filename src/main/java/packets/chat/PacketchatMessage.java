package packets.chat;

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
    public String parse() { // This function is executed on the server when the object PacketchatMessage is received and called by the server via parse function
        System.out.println(this.message);

        return null;
    }
}


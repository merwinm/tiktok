package packets;

import server.Packet;

/* Chat Message class is a packet which holds the chat message, it implemets the interface dataparse to convert the packet into a chatmessage object
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
    public String parse() {
        return this.message;
    }
}


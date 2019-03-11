package packets;

import java.io.Serializable;
/* Chat Message class is a packet which holds the chat message, it implemets the interface dataparse to convert the packet into a chatmessage object
* */
public class chatMessage extends packet {
    private String message;

    public chatMessage(String message){
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


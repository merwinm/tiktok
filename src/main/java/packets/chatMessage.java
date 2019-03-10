package packets;

import java.io.Serializable;
/* Chat Message class is a packet which holds the chat message, it implemets the interface dataparse to convert the packet into a chatmessage object
* */
public class chatMessage implements Serializable {
    private String message;

    public chatMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}


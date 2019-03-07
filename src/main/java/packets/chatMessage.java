package packets;

import java.io.Serializable;

public class chatMessage implements Serializable {
    private String message;

    public chatMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}

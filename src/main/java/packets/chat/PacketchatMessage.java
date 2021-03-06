package packets.chat;

import client.ServerHandler;
import server.ClientHandler;
import server.Packet;
import java.util.logging.Logger;

/* Chat Message class is a packet which holds the chat message, it implemets the interface Dataparse to convert the packet into a chatmessage object.
 *
* */
public class PacketchatMessage extends Packet {
    private String message;
    private String username;
    public static Logger logger = Logger.getLogger("Packet Chat Logger: ");

    public PacketchatMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    @Override
    public Object parseAtServer(ClientHandler handler) {   // This method gets executed when the packet reaches the server
        username = handler.getUsername();
        System.out.println(username + ": " + this.message);
        handler.sv().setChatLogText(username + ": " + message);
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            handler.sv().setChatLogText(username + ": " + message);
//                        }
//                    });
//                }
//            });

        logger.info("Packet parsing at Server");
        return null;
    }

    @Override
    public Object parseAtClient(ServerHandler handler) {  // This method gets executed when the packet reaches the client
        System.out.println(this.message);
        handler.cl().setchatlog(this.message);
        logger.info("Packet parsing at Client");
        return null;
    }
}


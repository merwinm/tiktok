package server.GUI;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import server.Server;

import java.util.logging.Logger;


public class ServerController {
    final static int DEFAULT_PORT = 6000;

    public Server server;
    public Button start_server;
    public Button stop_server;
    public TextArea appLog;
    public TextArea chatLog;
    public TextField port_nr;
    public static final Logger logger = Logger.getLogger("ServerControllerLogger");
    public ServerController(){
        chatLog = new TextArea();
        appLog = new TextArea();
        chatLog.setEditable(false);
        appLog.setEditable(false);
    }

    public void setChatLogText(String text){
        chatLog.appendText(text+"\n");
    }

    public void setAppLogText(String text){
        appLog.appendText(text+"\n");
    }

    public int getPort() {
        logger.info("Receiving Port");
        if(port_nr.getCharacters().length()==0){
            return DEFAULT_PORT;
        }
        else{
            CharSequence ch = port_nr.getCharacters();
            return Integer.parseInt(ch.toString());
        }
    }

    public void setServer(Server server){
        logger.info("Setting Server");
        this.server =  server;
    }

    public void start_server_action(ActionEvent actionEvent) {
        this.server.setPort(getPort());
        Thread ServerRunner = new Thread(server);
        ServerRunner.start();
        System.out.println(getPort());
        start_server.setDisable(true);
        logger.info("Server running successfully");
    }
}





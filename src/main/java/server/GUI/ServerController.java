package server.GUI;


import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ServerController {
    public Label Terminal;
    public Button start_server;
    public Button stop_server;

    public void setText(String text){
        Terminal.setText(text+"\n");
    }
}

package main;

import client.Client;
import client.GUI.ClientGUI;
import javafx.application.Application;
import server.GUI.ServerController;
import server.Server;
import server.GUI.ServerGUI;

public class main {

    public static void main(String[] args) {

        int port = 6000;
        String portNr = Integer.toString(port);
        String host = "192.168.0.6";

        if (args[0].equals("server")) {
            //Server server = new Server(port);
           Application.launch(ServerGUI.class,host,portNr);
           // server.runServer();

        } else {
           // Client client = new Client(host, port);
           Application.launch(ClientGUI.class,host,portNr);
           //client.run();
        }
    }
}


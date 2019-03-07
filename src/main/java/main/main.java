package main;

import client.Client;
import server.Server;

public class main {

    public static void main(String[] args) {

        int port = 6000;
        String host = "192.168.0.28";

        if(args[0].equals("server")){
            Server server = new Server(port);
            server.runServer();
        }

        else{
            Client client = new Client(host,port);
            client.run();
        }
    }
}
